package com.example.SpringRestAPI.services.impl;

import com.example.SpringRestAPI.domain.Role;
import com.example.SpringRestAPI.domain.User;
import com.example.SpringRestAPI.exceptions.EntityNotFoundException;
import com.example.SpringRestAPI.repositories.UserRepository;
import com.example.SpringRestAPI.services.UserService;
import com.example.SpringRestAPI.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Пользователь с id {0} не найден", id)));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Пользователь {0} не найден", username)
                ));
    }

    @Override
    public User createNewAccount(User user, Role role) {
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        role.setUser(user);

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        Optional<User> existedUser = userRepository.findById(user.getId());
        if (existedUser.isPresent()){
            BeanUtils.copyNonNullProperties(user, existedUser.get());
            return userRepository.save(existedUser.get());
        }
        throw new EntityNotFoundException(MessageFormat.format("Пользователь с id {0} не найден", user.getId()));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
