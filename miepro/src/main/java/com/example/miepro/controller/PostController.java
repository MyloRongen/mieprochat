package com.example.miepro.controller;

import com.example.miepro.model.Post;
import com.example.miepro.service.ImageService;
import com.example.miepro.service.PostService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PostController {
    private final PostService postService;
    private final ImageService imageService;

    public PostController(PostService postService, ImageService imageService) {
        this.postService = postService;
        this.imageService = imageService;
    }

    @PostMapping(value = "/create/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createPost(@RequestParam("description") String description,
                             @RequestParam("image") MultipartFile image) {

        String imageUrl = imageService.saveImage(image);

        Post post = new Post();
        post.setDescription(description);
        post.setImageUrl(imageUrl);

        return postService.createPost(post);
    }

    @GetMapping("/posts")
    @RolesAllowed({"ADMIN"})
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
