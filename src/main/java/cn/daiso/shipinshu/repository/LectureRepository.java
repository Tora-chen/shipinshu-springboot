package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long > {
}