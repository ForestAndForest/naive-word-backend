package com.example.naive.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author 29002
 */
@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer articleId;
    private String userUid;
    private String content;
    private Long time;
    @Transient
    private Integer like;
    @Column(name = "fa_id")
    private Integer fatherId;
}
