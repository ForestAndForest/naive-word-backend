package com.example.naive.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;


@Getter
public enum TagEnum {
    FRONT(1, "前端"),
    BACK(2, "后端"),
    VUE(3, "Vue"),
    VUE3(4, "Vue3"),
    HTML(5, "Html"),
    CSS(6, "Css"),
    JAVASCRIPT(7, "JavaScript"),
    JAVA(8, "Java"),
    PYTHON(9, "Python"),
    JQUERY(10, "Jquery"),
    MYSQL(11, "Mysql"),
    DOCKER(12, "Docker"),
    TYPESCRIPT(13, "TypeScript"),
    SPRINGBOOT(14, "SpringBoot"),
    NODE(15, "Node");

    private final int id;
    private final String name;

    TagEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
