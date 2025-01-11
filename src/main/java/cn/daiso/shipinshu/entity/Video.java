package cn.daiso.shipinshu.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "video")
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="transcript",nullable = false, columnDefinition = "TEXT")
    private String transcript;

    // 外键约束
    @ManyToOne // 多个视频对应一个讲座
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    private Lecture lecture;
}
