package com.example.naive.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tags")
public class Tag {
    @Id
    private int id;

    @Column(name = "note_id")
    private int note;

    @Column(name = "tag_id")
    private int tag;
}

