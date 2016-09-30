package com.readit.repository;

import com.readit.entity.Commentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nataliia on 29.09.16.
 */

@Repository
public interface CommentarRepository extends JpaRepository<Commentar, Long> {
}
