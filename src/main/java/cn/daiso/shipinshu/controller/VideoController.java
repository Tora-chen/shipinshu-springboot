package cn.daiso.shipinshu.controller;

import cn.daiso.shipinshu.entity.Lecture;
import cn.daiso.shipinshu.repository.LectureRepository;
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

@RestController
@RequestMapping("/api/videos")
public class VideoController {
    private final LectureRepository lectureRepository;
    @Value("${storage.path}") // 从配置文件中读取存储路径
    private String storagePath;

    private final VideoRepository videoRepository;

    public VideoController(VideoRepository videoRepository, LectureRepository lectureRepository) {
        this.videoRepository = videoRepository;
        this.lectureRepository = lectureRepository;
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
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file, @RequestParam("lecture_id")String lectureId) {
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
            video.setTranscript("暂无字幕");
            videoRepository.save(video);

            return ResponseEntity.ok("Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
