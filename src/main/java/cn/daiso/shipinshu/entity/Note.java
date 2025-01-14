package cn.daiso.shipinshu.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "note")
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="img_path")
    private String imgPath;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name="video_id")
    private Long videoId;
}
