package com.example.naive.domain;

import com.example.naive.converter.JsonListConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uid;

    private String nickname;

    private String avatar;

    private String email;

    private String password;

    @Convert(converter = JsonListConverter.class)
    private List<String> role;

    private Long createTime;

    private Long updateTime;

    private boolean enable;
}
