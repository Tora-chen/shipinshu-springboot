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

    @Column(name="image_path")
    private String imagePath;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name="timestamp")
    private String timestamp;

    @Column(name="video_id")
    private Long videoId;

}
