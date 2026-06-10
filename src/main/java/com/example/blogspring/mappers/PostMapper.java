package com.example.blogspring.mappers;

import com.example.blogspring.CreatePostRequest;
import com.example.blogspring.UpdatePostRequest;
import com.example.blogspring.dto.CreatePostRequestDto;
import com.example.blogspring.dto.PostDto;
import com.example.blogspring.dto.UpdatePostRequestDto;
import com.example.blogspring.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);

}
