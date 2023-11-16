package com.example.miepro.controller;

import com.example.miepro.exception.InvalidPostException;
import com.example.miepro.model.Post;
import com.example.miepro.service.ImageService;
import com.example.miepro.service.PostService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
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
    public String createPost(@RequestParam("description") @Valid String description,
                             @RequestParam("image") @Valid MultipartFile image) {
        try {
            String imageUrl = imageService.saveImage(image);

            Post post = new Post();
            post.setDescription(description);
            post.setImageUrl(imageUrl);

            return postService.createPost(post);
        } catch (InvalidPostException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/posts")
    @RolesAllowed({"ADMIN"})
    public List<Post> GetAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/post/{postId}")
    @RolesAllowed({"ADMIN"})
    public Post getPostById(@PathVariable @Valid final String postId){
        return postService.getPost(postId);
    }

    @PutMapping("/post")
    @RolesAllowed({"ADMIN"})
    public String patchPost(@RequestBody @Valid Post post){
        try {
            return postService.updatePost(post);
        } catch (InvalidPostException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/post/{postId}")
    @RolesAllowed({"ADMIN"})
    public String deletePost(@PathVariable @Valid final String postId){
        try {
            Post post = postService.getPost(postId);
            return postService.deletePost(post.getId().toString());
        } catch (InvalidPostException e) {
            return e.getMessage();
        }
    }
}
