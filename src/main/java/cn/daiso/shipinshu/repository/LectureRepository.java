package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long > {
        List<Lecture> findByIdIn(List<Long> lectureIds);
        List<Lecture> findByUploaderId(Long userId);
}
