package com.example.naive.service.impl;

import com.example.naive.domain.ArticleCard;
import com.example.naive.domain.Note;
import com.example.naive.repository.NoteRepository;
import com.example.naive.service.HotArticleService;
import com.example.naive.service.NoteService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 29002
 */
@Service
public class HotArticleServiceImpl implements HotArticleService {


    private final RedisTemplate<String, Integer> redisTemplate;
    private final RedisTemplate<String, String> template;
    private final StringRedisTemplate stringRedisTemplate;
    private final NoteRepository noteRepository;
    private final NoteService noteService;
    private final String HOT_ARTICLE_LIST_KEY = "hot_article_list_hash";
    private final String HOT_ARTICLE_KEY = "hot_article_";

    public HotArticleServiceImpl(
            @Qualifier("intRedisTemplate") RedisTemplate<String, Integer> redisTemplate,
            RedisTemplate<String, String> template, StringRedisTemplate stringRedisTemplate, NoteRepository noteRepository, @Lazy NoteService noteService) {
        this.redisTemplate = redisTemplate;
        this.template = template;
        this.stringRedisTemplate = stringRedisTemplate;
        this.noteRepository = noteRepository;
        this.noteService = noteService;
    }

    @Override
    public List<ArticleCard> getHotArticles(List<Integer> ids, int num) {
        // 获取前端 id 列表与热门文章 id 列表的差集
        Set<Integer> frontendIdSet = new HashSet<>(ids);
        Set<Integer> hotArticleIds = Objects.requireNonNull(stringRedisTemplate.keys(HOT_ARTICLE_KEY + "*"))
                .stream()
                .map(key -> Integer.parseInt(key.split("_")[2]))
                .collect(Collectors.toSet());

        hotArticleIds.removeAll(frontendIdSet);

        // 获取按hot降序排列的文章id列表
        List<Integer> hotArticleIdList = new ArrayList<>(hotArticleIds.stream().sorted((o1, o2) -> {
            int hot1 = Optional.ofNullable(redisTemplate.opsForValue().get(HOT_ARTICLE_KEY + o1)).orElse(0);
            int hot2 = Optional.ofNullable(redisTemplate.opsForValue().get(HOT_ARTICLE_KEY + o2)).orElse(0);
            return hot2 - hot1;
        }).limit(num).toList());

        return hotArticleIdList.stream()
                .map(id -> Optional.ofNullable(noteService.getNoteById(id)).orElseGet(() -> {
                    removeKey(String.valueOf(id));
                    return null;
                }))
                .filter(Objects::nonNull)
                .map(noteService::toArticleCard)
                .peek(articleCard -> articleCard.setHot(Optional.ofNullable(redisTemplate.opsForValue().get(HOT_ARTICLE_KEY + articleCard.getId())).orElse(0)))
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleCard> test(List<Integer> ids, int num) {

        Map<String, String> map = new HashMap<>();
        List<Note> notes = noteRepository.findAll();
        notes.forEach(item -> {

        });

        Set<Integer> frontendIdSet = new HashSet<>(ids);
        HashOperations<String, String, String> hashOperations = template.opsForHash();
        Set<Integer> hosArticleId = hashOperations.keys(HOT_ARTICLE_LIST_KEY)
                .stream().toList()
                .stream().map(Integer::parseInt)
                .collect(Collectors.toSet());
        System.out.println(frontendIdSet);
        System.out.println(hosArticleId);
        return null;
    }


    @Override
    public void increment(String articleId, int num) {
        redisTemplate.opsForValue().increment(HOT_ARTICLE_KEY + articleId, num);
    }

    @Override
    public void decrement(String articleId, int num) {
        redisTemplate.opsForValue().decrement(HOT_ARTICLE_KEY + articleId, num);
    }

    @Override
    public void setKeyWithExpire(String articleId, int num, long expire) {
        redisTemplate.opsForValue().set(HOT_ARTICLE_KEY + articleId, num);
        redisTemplate.expire(HOT_ARTICLE_KEY + articleId, expire, TimeUnit.SECONDS);
    }

    @Override
    public void removeKey(String articleId) {
        redisTemplate.delete(HOT_ARTICLE_KEY + articleId);
    }
}
