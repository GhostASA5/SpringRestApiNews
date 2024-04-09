package com.example.SpringRestAPI.web.controllers;

import com.example.SpringRestAPI.domain.User;
import com.example.SpringRestAPI.mapper.UserMapper;
import com.example.SpringRestAPI.services.UserService;
import com.example.SpringRestAPI.web.dto.user.UserListResponse;
import com.example.SpringRestAPI.web.dto.user.UserRequest;
import com.example.SpringRestAPI.web.dto.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/{pageNumber}/{pageSize}")
    private ResponseEntity<UserListResponse> findAll(@PathVariable Integer pageNumber, @PathVariable Integer pageSize){
        return ResponseEntity.ok(userMapper.userListToResponseList(userService.findAll(pageNumber, pageSize)));
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id)));
    }

    @PostMapping
    private ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request){
        User newUser = userService.save(userMapper.userRequestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    private ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UserRequest request){
        User updatedUser = userService.update(userMapper.userRequestToUser(id, request));
        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
