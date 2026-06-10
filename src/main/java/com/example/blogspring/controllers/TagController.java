package com.example.blogspring.controllers;

import com.example.blogspring.dto.CreateTagsRequest;
import com.example.blogspring.dto.TagDto;
import com.example.blogspring.entities.Tag;
import com.example.blogspring.mappers.TagMapper;
import com.example.blogspring.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        return ResponseEntity.ok(tagService.getTags().stream().map(tagMapper::toTagResponse).toList());
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());

        return ResponseEntity.ok(savedTags.stream().map(tagMapper::toTagResponse).toList());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
