package com.example.naive.core;

import com.example.naive.service.HotArticleService;
import com.example.naive.service.NoteService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledTasks {

    private final NoteService noteService;
    private final HotArticleService hotArticleService;
    private final RedisTemplate<Object, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public ScheduledTasks(NoteService noteService, HotArticleService hotArticleService, RedisTemplate<Object, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.noteService = noteService;
        this.hotArticleService = hotArticleService;
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Scheduled(fixedRate = 10 * 1000)
    public void updateHotArticles() {
        // 更新热门文章
//        List<ArticleCard> hotArticles = hotArticleService.getHotArticles(List.of(1, 2), 2);
    }

    // 凌晨执行
    @Scheduled(cron = "0 0 0 * * ?")
    public void dailyTask() {
        // 更新热门文章
        updateHotArticles();
    }

}
