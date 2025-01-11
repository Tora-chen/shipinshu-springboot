package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserCollectionRepository extends JpaRepository<UserCollection, Long> {
    UserCollection findByUserIdAndLectureId(Long userId, Long lectureId);
    List<UserCollection> findByUserId(Long userId);
}
