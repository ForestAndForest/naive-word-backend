package com.example.naive.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "menus")
public class Menu implements Serializable {
    @Id
    private Long id;

    private String label;

    @Column(name = "parent_id")
    @JsonIgnore
    private Long parent;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String type;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Menu> children;
}
