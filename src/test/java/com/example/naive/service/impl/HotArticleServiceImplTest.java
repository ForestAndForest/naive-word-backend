package com.example.naive.service.impl;

import com.example.naive.service.HotArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HotArticleServiceImplTest {

    @Autowired
    private HotArticleService hotArticleService;

    @Test
    void getHotArticles() {
        System.out.println(hotArticleService.getHotArticles(List.of(1,2),2));;
    }
}
