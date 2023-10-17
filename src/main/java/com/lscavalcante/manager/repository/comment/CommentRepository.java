package com.lscavalcante.manager.repository.comment;

import com.lscavalcante.manager.model.coment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndProjectId(Long commentId, Long projectId);
}
