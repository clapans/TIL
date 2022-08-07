package com.ssafy.db.repository;

import com.ssafy.db.entity.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
    Script관련 인터페이스 정의
 */

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {

    Optional<Script> findById(Long scriptId);


}
