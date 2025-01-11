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

    // 添加课程
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

    // 删除课程
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLecture(@PathVariable("id") Long id) {
        // 查找课程
        Lecture lecture = lectureRepository.findById(id).orElse(null);
        if (lecture == null) {
            return ResponseEntity.badRequest().body("Lecture not found!");
        }

        // 从spring security获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 查找用户id
        Long userId = userRepository.getUserByUsername(username).get(0).getId();

        // 判断是否是上传者
        if (!userId.equals(lecture.getUploaderId())) {
            return ResponseEntity.badRequest().body("You are not the uploader of this lecture!");
        }

        // 删除课程
        lectureRepository.deleteById(id);
        return ResponseEntity.ok("Lecture deleted successfully!");
    }

    // 查看我上传的课程
    @GetMapping("/my")
    public ResponseEntity<?> findMyLectures() {
        // 从spring security获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 查找用户id
        Long userId = userRepository.getUserByUsername(username).get(0).getId();

        // 查找我上传的课程
        return ResponseEntity.ok(lectureRepository.findByUploaderId(userId));
    }
//    111111111111111
}
