package com.example.naive.controller;

import com.example.naive.annotation.MyResponseBody;
import com.example.naive.domain.Comment;
import com.example.naive.domain.Note;
import com.example.naive.domain.vo.HotNoteRequest;
import com.example.naive.exception.UserNotLoginException;
import com.example.naive.service.HotArticleService;
import com.example.naive.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/note")
@MyResponseBody
public class NoteController {

    private final NoteService noteService;
    private final HotArticleService hotArticleService;

    public NoteController(NoteService noteService, HotArticleService hotArticleService) {
        this.noteService = noteService;
        this.hotArticleService = hotArticleService;
    }

    @GetMapping("/getNoteById")
    public Object getNote(@RequestParam(value = "id") int id) {
        return noteService.getNoteById(id);
    }

    @PostMapping("/publish/{id}")
    public Object publish(@PathVariable String id, @RequestBody Note note) {
        if (id.equals("1")) {
            throw new UserNotLoginException();
        }
        return noteService.publishNote(note);
    }

    @PostMapping("/getHotNote")
    public Object getHotNote(@RequestBody HotNoteRequest hotNoteRequest) {
        return hotArticleService.getHotArticles(hotNoteRequest.getIds(), hotNoteRequest.getNum());
    }

    @GetMapping("/hotIncrement")
    public Object hotIncrement(@RequestParam String articleId) {
        hotArticleService.increment(articleId, 1);
        return null;
    }

    @GetMapping("/getCommentsByArticleId")
    public Object getCommentsByArticleId(@RequestParam int articleId, @RequestParam int page, @RequestParam int size) {
        return noteService.getCommentsByArticleId(articleId, page, size);
    }

    @PostMapping("/addComment")
    public Object addComment(@RequestBody Comment comment) {
        return noteService.addComment(comment);
    }

    @GetMapping("/getArticleCommentIdsByArticleId")
    public Object getArticleCommentIdsByArticleId(@RequestParam int articleId, @RequestParam String uid) {
        return noteService.getArticleCommentIdsByArticleId(articleId, uid);
    }

    @PostMapping("/updateLikedCommentIds")
    public Object updateLikedCommentIds(@RequestParam String articleId, @RequestParam String uid, @RequestBody List<Integer> ids) {
        return noteService.updateLikedCommentIds(articleId, uid, ids);
    }

    @GetMapping("/like")
    public Object like(@RequestParam String uid, @RequestParam int id, @RequestParam int articleId, @RequestParam boolean isAdd) {
        if (isAdd) {
            return noteService.addLike(articleId, uid, id);
        } else {
            return noteService.delLike(articleId, uid, id);
        }
    }

    @GetMapping("/note/test")
    public Object test(@RequestBody HotNoteRequest hotNoteRequest){
        return hotArticleService.test(hotNoteRequest.getIds(),hotNoteRequest.getNum());
    }

}
