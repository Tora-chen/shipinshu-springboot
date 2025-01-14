package cn.daiso.shipinshu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.daiso.shipinshu.entity.Video;
import cn.daiso.shipinshu.repository.VideoRepository;

import java.io.File;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos")
public class VideoController {
    @Value("${storage.path}") // 从配置文件中读取存储路径
    private String storagePath;

    private final VideoRepository videoRepository;

    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
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
}
