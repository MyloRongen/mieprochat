package com.example.miepro.service.impl;

import com.example.miepro.model.Post;
import com.example.miepro.repository.PostRepository;
import com.example.miepro.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public String createPost(Post post) {
        LocalDateTime now = LocalDateTime.now();
        post.setCreatedAt(now);
        post.setUpdatedAt(now);

        postRepository.save(post);
        return "Successfully created an post!";
    }

    @Override
    public String updatePost(Post post) {
        // More logic

        postRepository.save(post);
        return "Successfully updated an post!";
    }

    @Override
    public String deletePost(String postId) {
        postRepository.deleteById(postId);
        return "Successfully deleted an post!";
    }

    @Override
    public Post getPost(String postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id: " + postId));
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
}
