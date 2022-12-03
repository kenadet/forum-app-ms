package com.kenny.forum.utils;

import com.kenny.forum.model.Comment;
import com.kenny.forum.model.dto.CommentDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Converter {

    public static Comment ConvertToComment(CommentDto commentDto){
        return Comment.builder()
                .id(UUID.randomUUID())
                .author(commentDto.getAuthor())
                .title(commentDto.getTitle())
                .body(commentDto.getBody())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
