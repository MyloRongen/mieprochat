package com.example.miepro.model;

public class TestPostWithUsername {
    private Post testPost;
    private String username;

    public TestPostWithUsername(Post testPost, String username) {
        this.testPost = testPost;
        this.username = username;
    }

    public Post getTestPost() {
        return testPost;
    }

    public void setTestPost(Post testPost) {
        this.testPost = testPost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
