package cn.daiso.shipinshu.controller;

import cn.daiso.shipinshu.entity.Lecture;
import cn.daiso.shipinshu.repository.LectureRepository;
import cn.daiso.shipinshu.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    public LectureController(UserRepository userRepository, LectureRepository lectureRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
    }

    @PostMapping("")
    public ResponseEntity<String> addLecture(@RequestBody Map<String, String> payload) {
        String title = payload.get("title");
        String description = payload.get("description");

        // 从spring security获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

         // 查找用户id
        Long id = userRepository.getUserByUsername(username).get(0).getId();

        // 创建Lecture对象
        Lecture lecture = new Lecture();
        lecture.setTitle(title);
        lecture.setDescription(description);
        lecture.setUploaderId(id);

        // 保存Lecture对象
        lectureRepository.save(lecture);
        return ResponseEntity.ok("Lecture added successfully!");
    }
}
