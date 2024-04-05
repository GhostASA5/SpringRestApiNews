package com.example.SpringRestAPI.services.impl;

import com.example.SpringRestAPI.domain.User;
import com.example.SpringRestAPI.exceptions.EntityNotFoundException;
import com.example.SpringRestAPI.repositories.UserRepository;
import com.example.SpringRestAPI.services.UserService;
import com.example.SpringRestAPI.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Пользователь с id {0} не найден", id)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
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
