package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> getNoteById(Long id);
    List<Note> findAllByVideoId(Long id);
}
