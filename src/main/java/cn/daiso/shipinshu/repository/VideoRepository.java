package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VideoRepository extends JpaRepository<Video, Long> {
//    List<Video> getVideoById(Long id);
    List<Video> getVideosByLectureId(Long lectureId);
}