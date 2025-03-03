package cn.daiso.shipinshu.controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import cn.daiso.shipinshu.entity.Lecture;
import cn.daiso.shipinshu.repository.CaptionRepository;
import cn.daiso.shipinshu.repository.LectureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cn.daiso.shipinshu.entity.Video;
import cn.daiso.shipinshu.repository.VideoRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/videos")
public class VideoController {
    private final LectureRepository lectureRepository;
    @Value("${storage.path}") // 从配置文件中读取存储路径
    private String storagePath;

    private final VideoRepository videoRepository;
    private final CaptionRepository captionRepository;

    public VideoController(VideoRepository videoRepository, LectureRepository lectureRepository,CaptionRepository captionRepository) {
        this.videoRepository = videoRepository;
        this.lectureRepository = lectureRepository;
        this.captionRepository = captionRepository;
    }


    @GetMapping("/{video_id}")
    public ResponseEntity<Resource> getVideoStream(@PathVariable String video_id) {
        Optional<Video> video = videoRepository.findById(Long.valueOf(video_id));
        if (video.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        String relativePath = video.get().getPath();
        String absolutePath = storagePath + relativePath;
        try {
            File file = new File(absolutePath);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            Resource videoResource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .contentType(MediaTypeFactory.getMediaType(file.getName()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                    .body(videoResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    这段代码可以完成上传视频并在数据库中记录的功能，但还存在以下问题：
//    1. 没有对上传视频的用户进行身份验证，用户可以给不属于自己的课程上传视频；
//    2. 没有保证操作的原子性，如果视频上传成功但是数据库操作失败，会导致视频无法被访问，也无法被删除；
//
    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file,
                                              @RequestParam("lecture_id")String lectureId,
                                              @RequestParam("title")String title,
                                              @RequestParam(value = "transcript", defaultValue = "") String transcript) {
        try {
            // 检查文件格式是否为mp4, 不是则返回400和提示信息
            if (!Objects.equals(file.getContentType(), "video/mp4")) {
                return ResponseEntity.badRequest().body(
                        "Error: Only mp4 files are allowed to upload"
                );
            }

            // 为了防止文件名重复，使用UUID作为文件名
            String uuidFilename = UUID.randomUUID().toString();
            String fileExtension = ".mp4";
            String relativePath = "/video/" + uuidFilename + fileExtension;
            String absolutePath = storagePath + relativePath;
            File dest = new File(absolutePath);

            // 将文件保存到磁盘中的指定目录
            file.transferTo(dest);

            // 检查课程是否存在
            Long lectureIdLong = Long.valueOf(lectureId);
            Optional<Lecture> lecture = lectureRepository.findById(lectureIdLong);
            if (lecture.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // 创建Video对象并保存
            Video video = new Video();
            video.setTitle(file.getOriginalFilename());
            video.setLecture(lecture.get());
            video.setPath(relativePath);
            video.setTitle(title);
            video.setTranscript(transcript);
            videoRepository.save(video);


            // 发送请求给Flask
            WebClient webClient = WebClient.builder().baseUrl("http://localhost:5000").build();

            // 1. 构造请求体
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("video_path", relativePath);

            // 2. 发送 POST 请求
            Mono<String> responseMono = webClient.post()
                    .uri("/api/process-video")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class);

            // 3. 处理响应
            responseMono.subscribe(response -> System.out.println("Response: " + response));


            return ResponseEntity.ok("Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // 删除视频(待完善——用户可以删除不是自己上传的课程)  删除视频有外键约束，需要先删除caption中的内容
    @DeleteMapping("/{video_id}")
    @Transactional
    public ResponseEntity<String> deleteVideo(@PathVariable String video_id) {
        Optional<Video> videoOptional = videoRepository.findById(Long.valueOf(video_id));
        if (videoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Video video = videoOptional.get();
        String relativePath = video.getPath();
        String absolutePath = storagePath + relativePath;
        File videoFile = new File(absolutePath);

        try {
            // 先删除 caption 表中关联的记录
            captionRepository.deleteByVideoId(video.getId());
            // 删除视频文件
            if (videoFile.exists()) {
                if (!videoFile.delete()) {
                    throw new RuntimeException("Failed to delete video file");
                }
            }

            // 删除数据库记录
            videoRepository.delete(video);

            return ResponseEntity.ok("Video deleted successfully");
        } catch (Exception e) {
            // 回滚数据库操作
            throw new RuntimeException("Failed to delete video: " + e.getMessage());
        }
    }

}
