package com.example.webfluxProject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/count")
    public Integer getProductCount(){
        return 20;
    }
    @GetMapping(value = "/reactive-count",produces = {"text/event-stream"})
    public Mono<ResponseEntity<Integer>> getProductCountReactive(){
        return Mono.just(ResponseEntity.ok(20));
    }
    @GetMapping("/all")
    public List<Integer> getAll() throws InterruptedException {
        List<Integer> products = new ArrayList<Integer>();
        for (int i = 0 ; i < 20 ; i++){
            products.add(i+1);
            Thread.sleep(500);
        }
        return products;
    }
    @GetMapping(value = "/all-reactive", produces ={"text/event-stream"} )
    public Flux<Integer> getAllReactive() throws InterruptedException {

        return Flux.create(fluxList ->{
            for (int i = 0 ; i < 20 ; i++){
                fluxList.next(i+1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            fluxList.complete();
        });
    }

}
