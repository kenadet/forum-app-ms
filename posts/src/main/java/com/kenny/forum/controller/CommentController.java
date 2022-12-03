package com.kenny.forum.controller;

import com.kenny.forum.model.Comment;
import com.kenny.forum.model.dto.CommentDto;
import com.kenny.forum.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @PutMapping(value = "{id}")
    ResponseEntity<Comment> addComment(@PathVariable(value = "id", required = false) String uuid, @RequestBody CommentDto commentDto){
        logger.info("updatePost() method started");
        Comment comment = commentService.addComment(UUID.fromString(uuid), commentDto);
        logger.info("updatePost() method ended");
        return  new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}/comments/{reply_id}")
    ResponseEntity<String>  deleteReply(@PathVariable(value = "id") String commentId, @PathVariable(value = "reply_id") String replyId){
        logger.info("Comment deleteReplyt() method started");
        commentService.deleteComment(UUID.fromString(commentId), UUID.fromString(replyId));
        logger.info("Comment deleteReplyt() method end");
        return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
    }
}
