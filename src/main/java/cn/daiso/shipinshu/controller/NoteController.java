package cn.daiso.shipinshu.controller;


import cn.daiso.shipinshu.entity.Caption;
import cn.daiso.shipinshu.entity.Note;
import cn.daiso.shipinshu.repository.CaptionRepository;
import cn.daiso.shipinshu.repository.NoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteRepository noteRepository;
    private final CaptionRepository captionRepository;

    public NoteController(NoteRepository noteRepository,CaptionRepository captionRepository) {
        this.noteRepository = noteRepository;
        this.captionRepository = captionRepository;
    }

    // 根据noteId获取note信息（描述、所有图片路径）
    @GetMapping("/certainNote/{note_id}")
    public ResponseEntity<?> findCertainNote(@PathVariable("note_id") Long noteId) {
        List<Note> note = noteRepository.getNoteById(noteId);
        if (note!=null) {
            return ResponseEntity.ok(note);
        } else {
            // 如果找不到对应的 Note 记录，返回 404 状态码
            return ResponseEntity.notFound().build();
        }
    }

    // 根据videoId获取所有note信息
    @GetMapping("/videoNotes/{video_id}")
    public ResponseEntity<?> findVideoNotes(@PathVariable("video_id") Long videoId){
        List<Note> notes = noteRepository.findAllByVideoId(videoId);
        if (notes!=null) {
            return ResponseEntity.ok(notes);
        } else {
            // 如果找不到对应的 Note 记录，返回 404 状态码
            return ResponseEntity.notFound().build();
        }
    }


    // 提取字幕文件路径(根据视频id)
    @GetMapping("/captions/{video_id}")
    public ResponseEntity<?> findCaptions(@PathVariable("video_id") Long videoId){
        List<Caption> captions = captionRepository.findAllByVideoId(videoId);
        List<String> captionPaths = new ArrayList<>();

        if (captions!= null &&!captions.isEmpty()) {
            for (Caption caption : captions) {
                captionPaths.add(caption.getPath());
            }
            return ResponseEntity.ok(captionPaths);
        } else {
            // 如果找不到对应的 Note 记录，返回 404 状态码
            return ResponseEntity.notFound().build();
        }
    }
}
