package com.example.miepro.controller;

import com.example.miepro.model.Post;
import com.example.miepro.repository.PostRepository;
import com.example.miepro.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
/*@CrossOrigin*/
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create/post")
/*    @CrossOrigin*/
    public String createPost(@RequestBody final Post post){
        return postService.createPost(post);
    }

    @GetMapping("/posts")
    public List<Post> GetAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/post/{postId}")
    public Post getPostById(@PathVariable final String postId){
        return postService.getPost(postId);
    }

    @PutMapping("/post")
    public String patchPost(@RequestBody Post post){
        return postService.updatePost(post);
    }

    @DeleteMapping("/post/{postId}")
    public String deletePost(@PathVariable final String postId){
        return postService.deletePost(postId);
    }
}
