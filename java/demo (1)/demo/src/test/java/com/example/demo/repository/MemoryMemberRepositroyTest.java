package com.example.demo.repository;

import com.example.demo.domain.Member;
import hello.
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositroyTest {
    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

    }
}
