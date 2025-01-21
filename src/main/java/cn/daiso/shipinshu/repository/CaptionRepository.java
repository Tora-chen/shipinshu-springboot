package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.Caption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CaptionRepository extends JpaRepository<Caption, Long> {
    // List<Note> getCaptionById(Long id);
    List<Caption> findAllByVideoId(Long id);
}
