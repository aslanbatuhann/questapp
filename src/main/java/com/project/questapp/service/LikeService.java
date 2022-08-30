package com.project.questapp.service;

import com.project.questapp.entity.Like;
import com.project.questapp.entity.Post;
import com.project.questapp.entity.User;
import com.project.questapp.repository.LikeRepository;
import com.project.questapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;

    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository,UserService userService,PostService postService){
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        }else
            return likeRepository.findAll();
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteOneLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public Like createOneLike(LikeCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }
}
