package com.example.miepro.model;

public class TestPostWithUsername {
    private TestPost testPost;
    private String username;

    public TestPostWithUsername(TestPost testPost, String username) {
        this.testPost = testPost;
        this.username = username;
    }

    public TestPost getTestPost() {
        return testPost;
    }

    public void setTestPost(TestPost testPost) {
        this.testPost = testPost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
