package com.example.miepro.repository;

import com.example.miepro.model.Post;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PostRepository extends CassandraRepository<Post, String> {
}

