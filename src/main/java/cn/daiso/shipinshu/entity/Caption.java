package cn.daiso.shipinshu.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "caption")
@Data
public class Caption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="path",nullable = false)
    private String path;

    @Column(name="language",nullable = false)
    private String language;

    // 外键约束
    @ManyToOne // 一个视频对应多个讲座
    @JoinColumn(name = "video_id", referencedColumnName = "id")
    private Video video;
}
