package com.example.SpringRestAPI.web.controllers;

import com.example.SpringRestAPI.aop.AuthorVerification;
import com.example.SpringRestAPI.domain.Role;
import com.example.SpringRestAPI.domain.RoleType;
import com.example.SpringRestAPI.domain.User;
import com.example.SpringRestAPI.mapper.UserMapper;
import com.example.SpringRestAPI.services.UserService;
import com.example.SpringRestAPI.web.dto.user.UserListResponse;
import com.example.SpringRestAPI.web.dto.user.UserRequest;
import com.example.SpringRestAPI.web.dto.user.UserRequestUpdate;
import com.example.SpringRestAPI.web.dto.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/{pageNumber}/{pageSize}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserListResponse> findAll(@PathVariable Integer pageNumber, @PathVariable Integer pageSize){
        return ResponseEntity.ok(userMapper.userListToResponseList(userService.findAll(pageNumber, pageSize)));
    }

    @GetMapping("/{id}")
    @AuthorVerification
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request,
                                                @RequestParam RoleType roleType){
        User newUser = userService.createNewAccount(userMapper.userRequestToUser(request), Role.from(roleType));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    @AuthorVerification
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UserRequestUpdate request){
        User updatedUser = userService.update(userMapper.userRequestToUser(id, request));
        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("{id}")
    @AuthorVerification
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
