package com.example.project1.controller;

import com.example.project1.models.PostCodes;
import com.example.project1.service.PostCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/postcodes")
public class PostCodesController {

    @Autowired
    private PostCodesService postCodesService;


    // Создание нового почтового кода
    @PostMapping
    public ResponseEntity<PostCodes> createPostCodes(@RequestBody PostCodes postCodes) {
        PostCodes newPostCodes = postCodesService.createPostCodes(postCodes);
        return new ResponseEntity<>(newPostCodes, HttpStatus.CREATED);
    }

    // Получение списка всех почтовых кодов
    @GetMapping
    public ResponseEntity<List<PostCodes>> getAllPostCodes() {
        List<PostCodes> postCodes = postCodesService.getAllPostCodes();
        return new ResponseEntity<>(postCodes, HttpStatus.OK);
    }

    // Получение почтового кода по ID
    @GetMapping("/{id}")
    public ResponseEntity<PostCodes> getPostCodesById(@PathVariable Long id) {
        return postCodesService.getPostCodesById(id)
                .map(postCodes -> new ResponseEntity<>(postCodes, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Обновление информации о почтовом коде
    @PutMapping("/{id}")
    public ResponseEntity<PostCodes> updatePostCodes(@PathVariable Long id, @RequestBody PostCodes newPostCodes) {
        PostCodes updatedPostCodes = postCodesService.updatePostCodes(id, newPostCodes);
        return new ResponseEntity<>(updatedPostCodes, HttpStatus.OK);
    }

    // Удаление почтового кода
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostCodes(@PathVariable Long id) {
        postCodesService.deletePostCodes(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
