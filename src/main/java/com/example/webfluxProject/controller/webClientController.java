package com.example.webfluxProject.controller;

import com.example.webfluxProject.model.PostDto;
import com.example.webfluxProject.service.PostWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class webClientController {
    @Autowired
    private PostWebClientService service;
    @GetMapping("students/{id}")
    public Mono<PostDto> getStudentById(@PathVariable long id){
        return service.get(id);
    }

    @GetMapping("students")
    public Flux<PostDto> getAllStudents(){
        return service.getAll();
    }

    @GetMapping("students/by-name/{name}")
    public Flux<PostDto> getStudentsByName(@PathVariable String name){
        return service.findByName(name);
    }




}
