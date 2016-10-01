package com.readit.repository;

import com.readit.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nataliia on 29.09.16.
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
