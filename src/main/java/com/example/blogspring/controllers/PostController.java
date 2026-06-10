package com.example.blogspring.controllers;

import com.example.blogspring.dto.PostDto;
import com.example.blogspring.entities.User;
import com.example.blogspring.mappers.PostMapper;
import com.example.blogspring.services.PostService;
import com.example.blogspring.services.UserService;
import lombok.RequiredArgsConstructor;
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

    }

}
