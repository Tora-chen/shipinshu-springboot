package cn.daiso.shipinshu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lecture")
@Data
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description",nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name="uploader_id", nullable = false)
    private  Long uploaderId;

    @Column(name="cover_path", nullable = false)
    private  String coverPath;
}
