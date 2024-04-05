package com.example.SpringRestAPI.services;

import com.example.SpringRestAPI.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

}
