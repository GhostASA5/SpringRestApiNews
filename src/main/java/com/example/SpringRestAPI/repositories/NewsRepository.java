package com.example.SpringRestAPI.repositories;

import com.example.SpringRestAPI.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {


}
