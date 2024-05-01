package com.example.naive.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.naive.domain.Comment;
import com.example.naive.domain.Note;
import com.example.naive.domain.Tag;
import com.example.naive.repository.CommentRepository;
import com.example.naive.repository.NoteRepository;
import com.example.naive.service.HotArticleService;
import com.example.naive.service.NoteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 29002
 */
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final HotArticleService hotArticleService;
    private final CommentRepository commentRepository;
    private final RedisTemplate<String, String> template;
    private final Long DAY_TIMESTAMP = 24 * 60 * 60 * 1000L;
    private final String ARTICLE_COMMENT_NUM_PREFIX = "article_comment_num_";
    private final String ARTICLE_COMMENT_IDS_KEY = "article_comment_ids";
    private final String USER_ARTICLE_LIKE_COMMENT_PREFIX = "user_article_like_comment_";
    private final String USER_ARTICLE_LIKE_PREFIX = "user_article_like_";
    private final String COMMENT_LIKE_HASH_KEY = "comment_like_hash";

    /**
     * 1.将文章id和对应的所有评论id存储在redis    将用户id和(文章id和对应文章已点赞评论id)存储在redis
     * 2.获取评论的同时获取上述文章所有评论id和用户在该文章已点赞的评论id
     * 3.处理评论(设置被回复评论)的同时处理是否已点赞 使用set
     */

    public NoteServiceImpl(NoteRepository noteRepository, HotArticleService hotArticleService, CommentRepository commentRepository, RedisTemplate<String, String> template) {
        this.noteRepository = noteRepository;
        this.hotArticleService = hotArticleService;
        this.commentRepository = commentRepository;
        this.template = template;
    }

    @Override
    public Note getNoteById(int id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        return noteOptional.map(note -> {
            List<Integer> tagIds = note.getTagList().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toList());
            note.setTags(tagIds);
            return note;
        }).orElse(null);
    }

    @Override
    public Object publishNote(Note note) {
        Note savedNote = noteRepository.save(note);
        System.out.println(savedNote);
        hotArticleService.setKeyWithExpire(String.valueOf(note.getId()), 1, DAY_TIMESTAMP);
        return null;
    }

    @Override
    public List<Comment> getCommentsByArticleId(int id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Comment> comments = commentRepository.findAllByArticleId(id, pageable).getContent();

        HashOperations<String, String, String> hashOperations = template.opsForHash();
        Map<String, String> likeCounts = hashOperations.entries(COMMENT_LIKE_HASH_KEY);

        for (Comment comment : comments) {
            Integer id1 = comment.getId();
            String like = Optional.ofNullable(likeCounts.get(String.valueOf(id1))).orElse("0");
            comment.setLike(Integer.valueOf(like));
        }
        return comments;
    }

    @Override
    public Object addComment(Comment comment) {
        if (comment.getUserUid() == null || comment.getArticleId() == null || comment.getContent() == null) {
            throw new IllegalArgumentException("评论数据不完整");
        }
        comment.setId(null); // 确保新增评论的 ID 为空，以便数据库生成新的 ID
        Comment savedComment = commentRepository.save(comment);
        String commentIdKey = String.valueOf(savedComment.getId());
        template.opsForHash().increment(COMMENT_LIKE_HASH_KEY, commentIdKey, 0L); // 初始化点赞数为 0
        updateCommentsMapping(ARTICLE_COMMENT_IDS_KEY, String.valueOf(savedComment.getArticleId()), savedComment.getId(), true);
        return null;
    }


    @Override
    public Object addLike(int articleId, String uid, int id) {
        String hashKey = String.valueOf(id);
        template.opsForHash().increment(COMMENT_LIKE_HASH_KEY, hashKey, 1L);
        updateCommentsMapping(USER_ARTICLE_LIKE_COMMENT_PREFIX + uid, String.valueOf(articleId), id, true);
        return null;
    }

    @Override
    public Object delLike(int articleId, String uid, int id) {
        String hashKey = String.valueOf(id);
        template.opsForHash().increment(COMMENT_LIKE_HASH_KEY, hashKey, -1L);
        updateCommentsMapping(USER_ARTICLE_LIKE_COMMENT_PREFIX + uid, String.valueOf(articleId), id, false);
        return null;
    }


    @Override
    public Object getArticleCommentIdsByArticleId(int articleId, String uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("articleCommentIds", updateCommentsMapping(ARTICLE_COMMENT_IDS_KEY, String.valueOf(articleId), 0, true));
        map.put("userArticleLikeCommentIds", updateCommentsMapping(USER_ARTICLE_LIKE_COMMENT_PREFIX + uid, String.valueOf(articleId), 0, true));
        return map;
    }

    @Override
    public Object updateLikedCommentIds(String articleId, String uid, List<Integer> ids) {
        HashOperations<String, String, String> hashOperations = template.opsForHash();
        String key = USER_ARTICLE_LIKE_COMMENT_PREFIX + uid;
        Map<String, String> map = hashOperations.entries(key);
        map.put(articleId, JSON.toJSONString(ids));
        hashOperations.putAll(key, map);
        return null;
    }

    /**
     * 更新评论关联关系 commentId为0时获取评论列表
     */
    private List<Integer> updateCommentsMapping(String key, String articleId, int commentId, boolean isAdd) {
        HashOperations<String, String, String> hashOperations = template.opsForHash();
        Map<String, String> map = hashOperations.entries(key);
        List<Integer> list = map.containsKey(articleId)
                ? JSON.parseArray(map.get(articleId), Integer.class)
                : new ArrayList<>();

        if (commentId == 0) {
            return list;
        }

        if (isAdd) {
            if (list.contains(commentId)) {
                return list;
            }
            list.add(commentId);
        } else {
            if (!list.contains(commentId)) {
                return list;
            }
            list = list.stream().filter(id -> id != commentId).toList();
        }

        map.put(articleId, JSON.toJSONString(list));
        hashOperations.putAll(key, map);
        return list;
    }


}
