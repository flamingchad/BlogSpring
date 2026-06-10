package com.example.blogspring.controllers;

import com.example.blogspring.CreatePostRequest;
import com.example.blogspring.UpdatePostRequest;
import com.example.blogspring.dto.CreatePostRequestDto;
import com.example.blogspring.dto.PostDto;
import com.example.blogspring.dto.UpdatePostRequestDto;
import com.example.blogspring.entities.Post;
import com.example.blogspring.entities.User;
import com.example.blogspring.mappers.PostMapper;
import com.example.blogspring.services.PostService;
import com.example.blogspring.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam(required = false)UUID categoryId, @RequestParam(required = false) UUID tagId ) {
        return ResponseEntity.ok(postService.getPosts(categoryId, tagId).stream().map(postMapper::toDto).toList());
    }

    @GetMapping("/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID id) {
        User loggedInUser = userService.getUserById(id);
        return ResponseEntity.ok(postService.getDraftPosts(loggedInUser)
                .stream()
                .map(postMapper::toDto)
                .toList());
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostRequestDto createPostRequestDto, @RequestAttribute UUID userId) {
        User loggedInUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);

        return ResponseEntity.ok(postMapper.toDto(postService.createPost(loggedInUser, createPostRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable UUID id, @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto) {
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);

        Post updatedPost = postService.updatePost(id, updatePostRequest);

        PostDto updatedPostDto = postMapper.toDto(updatedPost);
        return ResponseEntity.ok(updatedPostDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable UUID id) {
        return ResponseEntity.ok(postMapper.toDto(postService.getPost(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
