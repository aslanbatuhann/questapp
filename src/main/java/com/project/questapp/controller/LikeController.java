package com.project.questapp.controller;

import com.project.questapp.entity.Like;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> userId,
                                  @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId,postId);
    }
    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest request){
        return likeService.createOneLike(request);
    }
    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLike(likeId);
    }
}
