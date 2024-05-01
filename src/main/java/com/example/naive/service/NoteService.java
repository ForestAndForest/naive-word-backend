package com.example.naive.service;

import com.example.naive.domain.ArticleCard;
import com.example.naive.domain.Comment;
import com.example.naive.domain.Note;

import java.util.List;

/**
 * @author 29002
 */
public interface NoteService {
    Note getNoteById(int id);

    Object publishNote(Note note);

    List<Comment> getCommentsByArticleId(int id, int page, int size);

    Object addComment(Comment comment);

    Object addLike(int articleId,String uid, int id);

    Object delLike(int articleId,String uid, int id);

    Object getArticleCommentIdsByArticleId(int articleId, String uid);

    Object updateLikedCommentIds(String articleId,String uid,List<Integer> ids);

    default ArticleCard toArticleCard(Note note) {
        ArticleCard articleCard = new ArticleCard();
        articleCard.setId(note.getId());
        articleCard.setTitle(note.getTitle());
        articleCard.setSummary(note.getSummary());
        articleCard.setAuthorUid(note.getAuthorUid());
        articleCard.setLike(note.getLike());
        articleCard.setRead(note.getRead());
        articleCard.setCover(note.getCover());
        return articleCard;
    }
}
