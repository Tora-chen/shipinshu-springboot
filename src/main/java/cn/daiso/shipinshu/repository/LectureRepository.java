package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long > {
        List<Lecture> findByIdIn(List<Long> lectureIds);
        List<Lecture> findByUploaderId(Long userId);
        @Query("SELECT l.id FROM Lecture l")
        List<Long> findAllIds();   // 获取所有课程
}
