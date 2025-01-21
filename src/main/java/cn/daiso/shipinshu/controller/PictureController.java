package cn.daiso.shipinshu.controller;

import cn.daiso.shipinshu.entity.Video;
import cn.daiso.shipinshu.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/picture")
public class PictureController {
    private final VideoRepository videoRepository;
    @Value("${storage.path}") // 从配置文件中读取存储路径
    private String storagePath;

    public PictureController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    // 返回视频封面地址
    @GetMapping("/videoCover/{video_id}")
    public ResponseEntity<?> getVideoCoverPath(@PathVariable String video_id){
        Optional<Video> videoInfo = videoRepository.findById(Long.valueOf(video_id));
        if (videoInfo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Video video = videoInfo.get();
        String coverPath = storagePath + video.getCoverPath();
        return ResponseEntity.ok(coverPath);
    }


}
