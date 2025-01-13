package cn.daiso.shipinshu.controller;

import cn.daiso.shipinshu.entity.Lecture;
import cn.daiso.shipinshu.entity.Video;
import cn.daiso.shipinshu.repository.LectureRepository;
import cn.daiso.shipinshu.repository.UserRepository;
import cn.daiso.shipinshu.repository.VideoRepository;
import cn.daiso.shipinshu.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final VideoRepository videoRepository;

    public LectureController(UserRepository userRepository, LectureRepository lectureRepository,VideoRepository videoRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
        this.videoRepository = videoRepository;
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

    // 修改课程
    @PutMapping("/{lectureId}")
    public ResponseEntity<String> updateLecture(@PathVariable("lectureId") Long id, @RequestBody Map<String, String> payload) {
        Long userId = SecurityUtil.getCurrentUserId(userRepository);
        Lecture lecture = lectureRepository.findById(id).orElse(null);
        if (userId != lecture.getUploaderId()) {
            return ResponseEntity.badRequest().body("You are not the uploader of this lecture!");
        }
        // 是上传者，可以进行对课程的修改
        Lecture updateLecture = new Lecture();
        updateLecture.setTitle(payload.get("title"));
        updateLecture.setDescription(payload.get("description"));
        updateLecture.setUploaderId(userId);
        lectureRepository.save(updateLecture);
        return ResponseEntity.ok("Lecture updated successfully!");
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

    // 获取给定课程下所有视频
    @GetMapping("/{lectureId}/videos")
    public ResponseEntity<?> findCertainLectureVideo(@PathVariable("lectureId") Long lectureId){
        // 找到对应的视频id
        List<Video> videos = videoRepository.getVideosByLectureId(lectureId);

        // 将视频信息上传
        return ResponseEntity.ok(videos);
    }
}



