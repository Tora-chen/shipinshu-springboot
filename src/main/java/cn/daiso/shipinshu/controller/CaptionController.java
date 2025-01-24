package cn.daiso.shipinshu.controller;

import cn.daiso.shipinshu.entity.Caption;
import cn.daiso.shipinshu.entity.Video;
import cn.daiso.shipinshu.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/captions")
public class CaptionController {
    private final VideoRepository videoRepository;
    private final CaptionRepository captionRepository;

    public CaptionController(VideoRepository videoRepository, CaptionRepository captionRepository) {
        this.videoRepository = videoRepository;
        this.captionRepository = captionRepository;
    }

    @PostMapping("")
    public ResponseEntity<String> addCaption(@RequestBody Map<String, String> payload) {
        // 数据库中查询视频
        String video_path = payload.get("video_path");
        Optional<Video> video = videoRepository.findByPath(video_path);
        if (video.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: Video not found");
        }


        // 创建Caption对象
        Caption caption = new Caption();
        caption.setPath(payload.get("caption_path"));
        caption.setVideo(video.get());
        caption.setLanguage("zh-CN");

        // 保存Caption对象
        captionRepository.save(caption);
        return ResponseEntity.ok("Caption added successfully!");
    }
}
