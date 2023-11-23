package com.example.miepro.service.impl;

import com.example.miepro.model.TestPost;
import com.example.miepro.model.TestPostWithUsername;
import com.example.miepro.model.User;
import com.example.miepro.repository.CassandraTestRepository;
import com.example.miepro.repository.UserRepository;
import com.example.miepro.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestImpl implements TestService {
    private final CassandraTestRepository cassandraTestRepository;
    private final UserRepository userRepository;

    @Autowired
    public TestImpl(CassandraTestRepository cassandraTestRepository, UserRepository userRepository) {
        this.cassandraTestRepository = cassandraTestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TestPostWithUsername> getAllTestPosts() {
        List<TestPost> posts = cassandraTestRepository.findAll();

        return posts.stream()
                .map(post -> {
                    String username = getUsernameById(post.getUserId());
                    return new TestPostWithUsername(post, username);
                })
                .collect(Collectors.toList());
    }

    private String getUsernameById(Long userId) {
        return userRepository.findById(userId)
                .map(User::getUsername)
                .orElse("Unknown User");
    }
}
