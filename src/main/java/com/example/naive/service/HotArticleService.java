package com.example.naive.service;

import com.example.naive.domain.ArticleCard;

import java.util.List;

public interface HotArticleService {
    void increment(String articleId, int num);
    void decrement(String articleId, int num);
    void setKeyWithExpire(String articleId, int num, long expire);

    void removeKey(String articleId);
    List<ArticleCard> getHotArticles(List<Integer> ids,int num);

    Object test(List<Integer> ids, int num);

}
