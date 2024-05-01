package com.example.naive.repository;

import com.example.naive.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author 29002
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<List<Comment>> getCommentsByArticleId(int id);
    Page<Comment> findAllByArticleId(int id, Pageable pageable);
}
