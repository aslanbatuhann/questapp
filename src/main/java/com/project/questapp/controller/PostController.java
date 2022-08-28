package com.project.questapp.controller;

import com.project.questapp.entity.Post;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Posts")
public class PostController {
    
    private PostService postService;
    
    public PostController(PostService postService){
        this.postService = postService;
    }
    
    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }
    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }
}