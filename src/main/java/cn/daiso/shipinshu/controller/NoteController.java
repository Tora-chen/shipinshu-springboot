package cn.daiso.shipinshu.controller;


import cn.daiso.shipinshu.entity.Note;
import cn.daiso.shipinshu.repository.NoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // 获取note信息（文字、图片路径）
    @GetMapping("/{id}")
    public ResponseEntity<?> findMyNotes(@PathVariable("id") Long noteId) {
        List<Note> note = noteRepository.getNoteById(noteId);
        if (note!=null) {
            return ResponseEntity.ok(note);
        } else {
            // 如果找不到对应的 Note 记录，返回 404 状态码
            return ResponseEntity.notFound().build();
        }
    }

}
