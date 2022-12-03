package com.kenny.forum.service;

import com.kenny.forum.dao.CommentRepository;
import com.kenny.forum.dao.PostRepository;
import com.kenny.forum.model.Comment;
import com.kenny.forum.model.Post;
import com.kenny.forum.model.dto.CommentDto;
import com.kenny.forum.model.dto.PostDto;
import com.kenny.forum.model.dto.FilterDto;
import com.kenny.forum.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> filter(
            Pageable pageable, FilterDto filterDto) {
        return postRepository.findByTypeAndCountryAndStateAndCity(pageable, filterDto.getType(),
                filterDto.getCountry(), filterDto.getState(), filterDto.getCity());
    }

    @Override
    public Post createPost(PostDto postDto) {
        Post post = Post.builder()
                .id(UUID.randomUUID())
                .author(postDto.getAuthor())
                .title(postDto.getTitle())
                .body(postDto.getBody())
                .createdAt(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(UUID uuid, PostDto content) {
        return postRepository.findById(uuid).map(post -> {
                post.setBody(content.getBody());
                post.setTitle(content.getTitle());
                post.setCreatedAt(LocalDateTime.now());
                post.setLikes(content.getLikes());
                post.setDislikes(content.getDislikes());

                return postRepository.save(post);
        }).get();
    }

    @Override
    @Transactional
    public Comment addComment(UUID uuid, CommentDto commentDto) {
        return postRepository.findById(uuid).map(post -> {
            //Comment comment =  commentRepository.save(Converter.ConvertToComment(commentDto));
            Comment comment =  Converter.ConvertToComment(commentDto);
            post.getComments().add(comment);
            postRepository.save(post);
            return comment;
        }).get();
    }

    @Override
    public void deleteComment(UUID postID, UUID commentID) {
        postRepository.findById(postID).map(post -> {
            //Comment comment =  commentRepository.save(Converter.ConvertToComment(commentDto));
            post.getComments().remove(commentRepository.findById(commentID).get());
            return postRepository.save(post);
        });
    }

    @Override
    public void deletePost(UUID uuid) {
        postRepository.deleteById(uuid);
    }
}
