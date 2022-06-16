package com.project.blog.controller;

import com.project.blog.domain.Member;
import com.project.blog.domain.Role;
import com.project.blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class MemberController {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/dummy/join")
    public String join(Member member){
        System.out.println(member.getUsername());
        System.out.println(member.getPassword());
        System.out.println(member.getEmail());
        member.setRole(Role.User);
        memberRepository.save(member);
        return "완료";
    }

    @GetMapping("/dummy/user/{id}")
    public Member detail(@PathVariable int id){
        Member member = memberRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저가 없습니다. id : " + id);
            }
        });
        return member;
    }

    @GetMapping("/dummy/user")
    public List<Member> list(){
        return memberRepository.findAll();
    }
    @GetMapping("/dummy/user/page")
    public List<Member> pageList(@PageableDefault(size = 1,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        return memberRepository.findAll(pageable).getContent();
    }
    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            memberRepository.deleteById(id);
        }catch (IllegalArgumentException e){
            return "삭제 실패";
        }
        return "삭제 완료";
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public Member update(@PathVariable int id, @RequestBody Member member){
        System.out.println(member.getUsername());
        System.out.println(member.getPassword());
        System.out.println(member.getEmail());
        Member premember = memberRepository.findById(id).orElseThrow(() -> {
           return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });
        premember.setUsername(member.getUsername());
        premember.setPassword(member.getPassword());
        return member;
    }
}
