package com.example.naive.domain;

import com.example.naive.converter.JsonListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private String summary;

    private String cover;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name = "`like`")
    private int like;

    private int favorites;

    @Column(name = "`read`")
    private int read;
    @Column(name = "author_uid")
    private String authorUid;

    @JsonIgnore
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tagList;

    @Transient
    private List<Integer> tags;

    @Transient
    private Integer commentNum;

    @Convert(converter = JsonListConverter.class)
    private List<String> directory;


}
