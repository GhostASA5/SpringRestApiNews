package com.example.SpringRestAPI.repositories;

import com.example.SpringRestAPI.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
