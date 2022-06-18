package com.example.crudtest.controller;

import com.example.crudtest.domain.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.example.crudtest.repository.ContentRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RestController
public class ContentController {

    private final ContentRepository contentRepository;

    @Autowired
    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("/content")
    public List<Map<String, Object>> list(){
        List<Map<String, Object>> res = new ArrayList<>();
        List<Content> lst = contentRepository.findTop1000ByOrderByUidDesc();
        for (Content content : lst){
            Map<String, Object> map = new HashMap<>();
            map.put("uid",content.getUid());
            map.put("path",content.getPath());
            map.put("password",content.getPassword());
            res.add(map);
        }
        return res;
    }

    @PostMapping("/content")
    public Map<String, String> create(@RequestParam String title,
            @RequestParam String password, @RequestPart("picture") MultipartFile img){
        String path = System.getProperty("user.dir");
        File file = new File(path + "/src/main/resources/static/" + img.getOriginalFilename());
        try {
            img.transferTo(file);
        }catch (IOException e){
            System.out.println("입출력 에러");
        }
        Content content = new Content();
        content.setTitle(title);
        content.setPassword(password);
        content.setPath(img.getOriginalFilename());
        contentRepository.save(content);
        Map<String, String> map = new HashMap<>();
        map.put("path",img.getOriginalFilename());
        return map;
    }

    @Transactional
    @PutMapping("/content/{uid}")
    public Map<String, String> update(@PathVariable int uid,
                          @RequestParam String title,
                          @RequestParam String password,
                          @RequestPart("picture") MultipartFile img){
        Content content = contentRepository.findById(uid).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저가 없습니다.");
            }
        });
        if (password.equals(content.getPassword())){
            content.setTitle(title);
            if (img.isEmpty()){
                String path = System.getProperty("user.dir");
                File file = new File(path + "/src/main/resources/static/" + img.getOriginalFilename());
                try {
                    img.transferTo(file);
                }catch (IOException e){
                    System.out.println("입출력 에러");
                }
                content.setPath(img.getOriginalFilename());
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("path",img.getOriginalFilename());
        return map;
    }

    @DeleteMapping("/content/{uid}")
    public void delete(@PathVariable int uid, @RequestBody Map<String, Object> body){
        Content content = contentRepository.findById(uid).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저가 없습니다.");
            }
        });
        System.out.println(body.get("password"));
        if (body.get("password").equals(content.getPassword())){
            contentRepository.deleteById(uid);
        }
    }
}
