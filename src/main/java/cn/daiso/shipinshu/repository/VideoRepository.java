package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface VideoRepository extends JpaRepository<Video, Long> {
//    List<Video> getVideoById(Long id);
    List<Video> getVideosByLectureId(Long lectureId);
    Optional<Video> findByPath(String path);
}