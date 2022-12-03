package com.kenny.forum.service;

import com.kenny.forum.model.Comment;
import com.kenny.forum.model.Post;
import com.kenny.forum.model.dto.CommentDto;
import com.kenny.forum.model.dto.FilterDto;
import com.kenny.forum.model.dto.PostDto;
import com.kenny.forum.model.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PostService {

    Page<Post> findAll(Pageable pageable);

    Page<Post> filter(Pageable pageable, FilterDto filterDto);
    Post createPost(PostDto postDto);
    Post updatePost(UUID uuid, PostDto post);
    Comment addComment(UUID postID, CommentDto commentDto);
    void deletePost(UUID uuid);
    void  deleteComment(UUID postID, UUID commentID);
}
