package com.example.SpringRestAPI.mapper;

import com.example.SpringRestAPI.domain.Comment;
import com.example.SpringRestAPI.mapper.delegates.CommentMapperDelegate;
import com.example.SpringRestAPI.web.dto.comment.CommentListResponse;
import com.example.SpringRestAPI.web.dto.comment.CommentRequestCreate;
import com.example.SpringRestAPI.web.dto.comment.CommentRequestUpdate;
import com.example.SpringRestAPI.web.dto.comment.CommentResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, NewsMapper.class})
public interface CommentMapper {

    Comment commentRequestToComment(CommentRequestCreate request);

    @Mapping(source = "commentId", target = "id")
    Comment commentRequestUpdateToComment(Long commentId, CommentRequestUpdate request);

    CommentResponse commentToResponse(Comment comment);

    default CommentListResponse commentListToResponseList(List<Comment> comments) {
        CommentListResponse response = new CommentListResponse();
        response.setComments(comments.stream().map(this::commentToResponse).collect(Collectors.toList()));
        return response;
    }
}
