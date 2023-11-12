package com.example.miepro.repository;

import com.example.miepro.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, String> {
}

