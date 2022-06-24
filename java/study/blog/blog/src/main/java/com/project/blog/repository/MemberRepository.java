package com.project.blog.repository;

import com.project.blog.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    public Optional<Member> findByUsernameAndPassword(String username, String password);
}
