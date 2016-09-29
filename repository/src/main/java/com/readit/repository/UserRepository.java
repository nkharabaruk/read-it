package com.readit.repository;

import com.readit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nataliia on 29.09.16.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
