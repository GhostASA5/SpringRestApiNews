package com.example.SpringRestAPI.mapper;

import com.example.SpringRestAPI.domain.User;
import com.example.SpringRestAPI.web.dto.user.UserListResponse;
import com.example.SpringRestAPI.web.dto.user.UserRequest;
import com.example.SpringRestAPI.web.dto.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class, CommentMapper.class})
public interface UserMapper {

    User userRequestToUser(UserRequest request);

    @Mapping(source = "userId", target = "id")
    User userRequestToUser(Long userId, UserRequest request);

    UserResponse userToResponse(User user);

    default UserListResponse userListToResponseList(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.setUsers(users.stream().map(this::userToResponse).collect(Collectors.toList()));
        return response;
    }

}
