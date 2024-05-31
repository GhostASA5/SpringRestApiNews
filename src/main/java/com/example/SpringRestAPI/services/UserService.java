package com.example.SpringRestAPI.services;

import com.example.SpringRestAPI.domain.Role;
import com.example.SpringRestAPI.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll(Integer pageNo, Integer pageSize);

    User findById(Long id);

    User findByUsername(String username);

    User createNewAccount(User user, Role role);

    User update(User user);

    void deleteById(Long id);

}
