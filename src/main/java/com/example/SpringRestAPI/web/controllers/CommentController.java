package com.example.SpringRestAPI.web.controllers;

import com.example.SpringRestAPI.domain.Comment;
import com.example.SpringRestAPI.mapper.CommentMapper;
import com.example.SpringRestAPI.services.CommentService;
import com.example.SpringRestAPI.web.dto.comment.CommentListResponse;
import com.example.SpringRestAPI.web.dto.comment.CommentRequestCreate;
import com.example.SpringRestAPI.web.dto.comment.CommentRequestUpdate;
import com.example.SpringRestAPI.web.dto.comment.CommentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping
    private ResponseEntity<CommentListResponse> findAll(){
        return ResponseEntity.ok(commentMapper.commentListToResponseList(commentService.findAll()));
    }

    @GetMapping("/{id}")
    private ResponseEntity<CommentResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(commentMapper.commentToResponse(commentService.findById(id)));
    }

    @PostMapping()
    private ResponseEntity<CommentResponse> create(@RequestBody @Valid CommentRequestCreate request){
        Comment comment = commentService.save(commentMapper.commentRequestToComment(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentToResponse(comment));
    }

    @PutMapping("/{id}")
    private ResponseEntity<CommentResponse> update(@PathVariable Long id,
                                                   @RequestBody @Valid CommentRequestUpdate request,
                                                   @AuthenticationPrincipal UserDetails userDetails){
        Comment comment = commentService.update(commentMapper.commentRequestUpdateToComment(id, request), userDetails.getUsername());
        return ResponseEntity.ok(commentMapper.commentToResponse(comment));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id,
                                        @AuthenticationPrincipal UserDetails userDetails){
        commentService.deleteById(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
