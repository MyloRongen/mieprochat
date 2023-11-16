package com.example.miepro.service;

import com.example.miepro.model.Post;
import java.util.List;

public interface PostService {
    String createPost(Post post);
    String updatePost(Post post);
    String deletePost(String postId);
    Post getPost(String cloudVendorId);
    List<Post> getAllPosts();
}
