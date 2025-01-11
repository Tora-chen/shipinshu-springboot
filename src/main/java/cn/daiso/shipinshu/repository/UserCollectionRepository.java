package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCollectionRepository extends JpaRepository<UserCollection, Long> {
    UserCollection findByUserIdAndLectureId(Long userId, Long lectureId);
}
