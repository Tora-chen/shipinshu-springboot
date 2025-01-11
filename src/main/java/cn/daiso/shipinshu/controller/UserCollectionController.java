package cn.daiso.shipinshu.controller;

import cn.daiso.shipinshu.entity.UserCollection;
import cn.daiso.shipinshu.repository.UserCollectionRepository;
import cn.daiso.shipinshu.repository.UserRepository;
import cn.daiso.shipinshu.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user-collection")
public class UserCollectionController {

    private final UserRepository userRepository;
    private final UserCollectionRepository userCollectionRepository;

    public UserCollectionController(UserRepository userRepository, UserCollectionRepository userCollectionRepository) {
        this.userRepository = userRepository;
        this.userCollectionRepository = userCollectionRepository;
    }

    // 收藏课程
    @PostMapping("")
    public ResponseEntity<String> collectLecture(@RequestBody Map<String, String> payload) {
        Long lectureId = Long.valueOf(payload.get("lectureId"));
        Long userId = SecurityUtil.getCurrentUserId(userRepository);

        System.out.println("用户 " + userId + " 收藏了课程 " + lectureId);

        UserCollection userCollection = new UserCollection();
        userCollection.setUserId(userId);
        userCollection.setLectureId(lectureId);

        // 保存收藏记录
        userCollectionRepository.save(userCollection);
        return ResponseEntity.ok("Lecture collected successfully!");
    }

    // 取消收藏
    @DeleteMapping("/{lectureId}")
    public ResponseEntity<String> cancelCollectLecture(@PathVariable("lectureId") Long lectureId) {
        Long userId = SecurityUtil.getCurrentUserId(userRepository);

        // 查找收藏记录
        UserCollection userCollection = userCollectionRepository.findByUserIdAndLectureId(userId, lectureId);
        if (userCollection == null) {
            return ResponseEntity.badRequest().body("Collection not found!");
        }

        // 删除收藏记录
        userCollectionRepository.delete(userCollection);
        return ResponseEntity.ok("Lecture canceled successfully!");
    }
}
