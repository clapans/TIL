package com.example.crudtest.repository;

import com.example.crudtest.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Integer> {
    public List<Content> findTop1000ByOrderByUidDesc();
}
