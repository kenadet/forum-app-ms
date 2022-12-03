package com.kenny.forum.service;

import com.kenny.forum.dao.CommentRepository;
import com.kenny.forum.model.Comment;
import com.kenny.forum.model.dto.CommentDto;
import com.kenny.forum.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    @Transactional
    public Comment addComment(UUID uuid, CommentDto commentDto) {

        return commentRepository.findById(uuid).map(existingComment -> {
            Comment comment =  Converter.ConvertToComment(commentDto);
            existingComment.getComments().add(comment);
            commentRepository.save(existingComment);
            return comment;
        }).get();
    }

    @Override
    public void deleteComment(UUID commentID, UUID replyID) {
            commentRepository.findById(commentID).map(comment -> {
                comment.getComments().remove(commentRepository.findById(replyID).get());
                return commentRepository.save(comment);
            });
        }
}

