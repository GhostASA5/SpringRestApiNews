package com.example.SpringRestAPI.repositories;

import com.example.SpringRestAPI.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
