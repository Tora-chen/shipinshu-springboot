package cn.daiso.shipinshu.controller;

import cn.daiso.shipinshu.entity.Lecture;
import cn.daiso.shipinshu.entity.UserCollection;
import cn.daiso.shipinshu.repository.UserCollectionRepository;
import cn.daiso.shipinshu.repository.UserRepository;
import cn.daiso.shipinshu.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cn.daiso.shipinshu.repository.LectureRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-collection")
public class UserCollectionController {

    private final UserRepository userRepository;
    private final UserCollectionRepository userCollectionRepository;
    private final LectureRepository lectureRepository;

    public UserCollectionController(UserRepository userRepository, UserCollectionRepository userCollectionRepository, cn.daiso.shipinshu.repository.LectureRepository lectureRepository) {
        this.userRepository = userRepository;
        this.userCollectionRepository = userCollectionRepository;
        this.lectureRepository = lectureRepository;
    }

    // 收藏课程（bug 点击两遍收藏会在数据库中添加两遍，而不是取消）
    @PostMapping("")
    public ResponseEntity<String> collectLecture(@RequestBody Map<String, String> payload) {
        Long lectureId = Long.valueOf(payload.get("lectureId"));
        Long userId = SecurityUtil.getCurrentUserId(userRepository);

        System.out.println("用户 " + userId + " 试图收藏课程 " + lectureId);

        // 查找是否已经有收藏记录
        UserCollection existingCollection = userCollectionRepository.findByUserIdAndLectureId(userId, lectureId);
    
        if (existingCollection != null) {
            // 如果已经收藏，则返回已经收藏
            return ResponseEntity.ok("Lecture has already collected successfully!");
        } else {
            // 如果没有收藏，则添加收藏记录
            UserCollection userCollection = new UserCollection();
            userCollection.setUserId(userId);
            userCollection.setLectureId(lectureId);

            // 保存收藏记录
            userCollectionRepository.save(userCollection);
            return ResponseEntity.ok("Lecture collected successfully!");
        }
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

    // 查看我的收藏
    @GetMapping("/my")
    public ResponseEntity<List<Lecture>> myCollection() {
        Long userId = SecurityUtil.getCurrentUserId(userRepository);

        // 查找我的收藏
        List<UserCollection> userCollection = userCollectionRepository.findByUserId(userId);
        if (userCollection.isEmpty()) {
            return ResponseEntity.ok(null);
        }

        // userCollection中只有Long类型的lectureId，需要在Lecture表中查找收藏的课程
        List<Long> lectureIds = userCollection.stream()
                .map(UserCollection::getLectureId)
                .collect(Collectors.toList());
        List<Lecture> lectures = lectureRepository.findByIdIn(lectureIds);

        return ResponseEntity.ok(lectures);
    }
}
