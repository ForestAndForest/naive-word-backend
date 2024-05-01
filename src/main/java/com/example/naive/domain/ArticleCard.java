package com.example.naive.domain;

import lombok.Data;

@Data
public class ArticleCard {
    private int id;
    private String cover;
    private String title;
    private String summary;
    private String authorUid;
    private int like;
    private int read;
    private int hot;
}
