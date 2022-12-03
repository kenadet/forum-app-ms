package com.kenny.forum.controller;

import com.kenny.forum.model.Comment;
import com.kenny.forum.model.Post;
import com.kenny.forum.model.dto.CommentDto;
import com.kenny.forum.model.dto.PostDto;
import com.kenny.forum.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/posts")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;

    @GetMapping
    ResponseEntity<Page<Post>> findAll(@PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable,
                                       @RequestHeader(name="kennz-correlation-id", required = false) String correlationId){
        logger.info("findAll() method started");
        Page<Post> pagedPosts = postService.findAll(pageable);
        logger.info("findAll() method ended");
        return new ResponseEntity<Page<Post>>(pagedPosts, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Post>  createPost(@RequestBody PostDto postDto){
        logger.info("createPost() method started");
        Post post = postService.createPost(postDto);
        logger.info("createPost() method ended");
        return  new ResponseEntity<Post>(post, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    ResponseEntity<Post>  updatePost(@PathVariable(value = "id", required = false) String uuid, @RequestBody PostDto postDto){
        logger.info("updatePost() method started");
        Post post = postService.updatePost(UUID.fromString(uuid), postDto);
        logger.info("updatePost() method ended");
        return  new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    ResponseEntity<String>  deletePost(@PathVariable(value = "id") String uuid){
        logger.info("deletePost() method started");
        postService.deletePost(UUID.fromString(uuid));
        logger.info("deletePost() method end");
        return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/comments")
    public  ResponseEntity<Comment> addComment(@PathVariable(value = "id") String uuid, CommentDto commentDto){
        logger.info("Post addComment() method started");
        Comment comment = postService.addComment(UUID.fromString(uuid), commentDto);
        logger.info("Post addCommen() method ended");
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}/comments/{reply_id}")
    ResponseEntity<String>  deleteReply(@PathVariable(value = "id") String postId, @PathVariable(value = "reply_id") String replyId){
        logger.info("Post deleteReply() method started");
        postService.deleteComment(UUID.fromString(postId), UUID.fromString(replyId));
        logger.info("Post deleteReply() method end");
        return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
    }
}
