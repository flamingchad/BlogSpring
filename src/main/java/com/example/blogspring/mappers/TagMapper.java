package com.example.blogspring.mappers;

import com.example.blogspring.dto.TagResponse;
import com.example.blogspring.entities.Post;
import com.example.blogspring.entities.PostStatus;
import com.example.blogspring.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    @Mapping(target = "postCount", source ="posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
        if(posts == null) {
            return 0;
        }
        long count = posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();

        return (int) count;
    }
}
