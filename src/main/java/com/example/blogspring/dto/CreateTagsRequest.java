package com.example.blogspring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagsRequest {
    @NotEmpty(message = "Atleast 1 tag name is requried")
    @Size(max = 10, message = "Max 10 tags allowed.")
    private Set<@Size(min = 2, max = 30, message = "Tag names must be between {min} and {max} characters")
            @Pattern(regexp = "^[\\w\\s-]+$", message = "Tag name can only contain letters, numbers, spaces and hypens")
            String> names;

}
