package com.example.miepro.controller;

import com.example.miepro.exception.InvalidPostException;
import com.example.miepro.model.*;
import com.example.miepro.service.ImageService;
import com.example.miepro.service.PostService;
import com.example.miepro.service.TestService;
import com.example.miepro.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PostController {
    private final PostService postService;
    private final ImageService imageService;
    private final UserService userService;
    private final TestService testService;

    public PostController(PostService postService, ImageService imageService, UserService userService, TestService testService) {
        this.postService = postService;
        this.imageService = imageService;
        this.userService = userService;
        this.testService = testService;
    }

    @PostMapping(value = "/create/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<String> createPost(@RequestParam("description") @Valid String description,
                             @RequestParam("image") @Valid MultipartFile image,
                             Principal principal) {
        try {
            String imageUrl = imageService.saveImageAndReturnUrl(image);
            User currentUser = userService.findUserByUsername(principal.getName());
            Post post = createPostModel(description, imageUrl, currentUser);
            String result = postService.createPost(post);

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (InvalidPostException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create post: " + e.getMessage());
        }
    }

    /*@GetMapping("/posts")
    @RolesAllowed({"ADMIN"})
    public List<PostResponse> GetAllPosts(){
        List<Post> posts = postService.getAllPosts();
        List<PostResponse> postResponses = new ArrayList<>();

        for (Post post : posts) {
            PostResponse postResponse = new PostResponse();
            postResponse.setId(post.getId());
            postResponse.setCreatedAt(post.getCreatedAt());
            postResponse.setDescription(post.getDescription());
            postResponse.setImageUrl(post.getImageUrl());
            postResponse.setUpdatedAt(post.getUpdatedAt());

            User user = new User();
            user.setId(post.getUser.id().getId());
            user.setEmail(post.getUser().getEmail());
            user.setUsername(post.getUser().getUsername());

            postResponse.setUser(user);

            postResponses.add(postResponse);
        }

        return postResponses;
    }*/

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

    @GetMapping("/tests")
    public List<TestPostWithUsername> GetAllTestPosts(){
        return testService.getAllTestPosts();
    }

    private Post createPostModel(String description, String imageUrl, User user) {
        Post post = new Post();
        Post.setDescription(description);
        Post.setImageUrl(imageUrl);
        Post.setUserId(user);
        return post;
    }
}
