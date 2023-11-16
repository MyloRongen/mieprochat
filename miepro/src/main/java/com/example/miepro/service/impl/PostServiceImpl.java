package com.example.miepro.service.impl;

import com.example.miepro.exception.InvalidPostException;
import com.example.miepro.model.Post;
import com.example.miepro.repository.PostRepository;
import com.example.miepro.service.PostService;
import org.springframework.stereotype.Service;
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
        validatePostData(post);
        postRepository.save(post);

        return "Successfully created an post!";
    }

    @Override
    public String updatePost(Post post) {
        if (post.getId() == null) {
            throw new InvalidPostException("Post ID cannot be null or empty");
        }

        validatePostData(post);
        postRepository.save(post);

        return "Successfully updated an post!";
    }

    @Override
    public String deletePost(String postId) {
        validatePostId(postId);
        postRepository.deleteById(postId);

        return "Successfully deleted an post!";
    }

    @Override
    public Post getPost(String postId) {
        validatePostId(postId);

        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id: " + postId));
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    private void validatePostData(Post post) {
        if (post == null) {
            throw new InvalidPostException("Post cannot be null");
        }

        if (post.getDescription() == null || post.getDescription().isEmpty()) {
            throw new InvalidPostException("Post description cannot be null or empty");
        }

        if (post.getImageUrl() == null || post.getImageUrl().isEmpty()) {
            throw new InvalidPostException("Post imageUrl cannot be null or empty");
        }
    }

    private void validatePostId(String postId) {
        if (postId == null || postId.isEmpty()) {
            throw new InvalidPostException("Post ID cannot be null or empty");
        }
    }
}
