package cn.daiso.shipinshu.controller;

import cn.daiso.shipinshu.repository.VideoRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/picture")
public class PictureController {
    private final VideoRepository videoRepository;

    public PictureController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }



    // 返回笔记截屏地址


}
