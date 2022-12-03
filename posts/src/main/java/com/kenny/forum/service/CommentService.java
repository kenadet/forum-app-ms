package com.kenny.forum.service;

import com.kenny.forum.model.Comment;
import com.kenny.forum.model.dto.CommentDto;

import java.util.UUID;

public interface CommentService {

    Comment addComment(UUID commentID, CommentDto commentDto);
    public void deleteComment(UUID commentID, UUID replyID);
}
