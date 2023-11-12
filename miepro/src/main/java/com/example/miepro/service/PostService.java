package com.example.miepro.service;

import com.example.miepro.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    public String createPost(Post post);
    public String updatePost(Post post);
    public String deletePost(String postId);
    public Post getPost(String cloudVendorId);
    public List<Post> getAllPosts();
}
