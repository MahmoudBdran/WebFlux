package com.example.webfluxProject.service;

import com.example.webfluxProject.model.PostDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostWebClientService {
    private static String BASE_POST_URL = "https://jsonplaceholder.typicode.com";
    WebClient client = WebClient.create(BASE_POST_URL);

    public Mono<PostDto> get(long id){
        return client
                .get()
                .uri("/posts/"+id)
                .retrieve()
                .bodyToMono(PostDto.class);
    }

    public Flux<PostDto> getAll(){
        return client
                .get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(PostDto.class);
    }
    public Flux<PostDto> findByName(String name){
        return client
                .get()
                .uri(uriBuilder -> uriBuilder.path("/posts")
                        .queryParam("name",name)
                        .build())
                .headers(headers->headers.setBasicAuth("user","userpwd"))
                .retrieve()
                .bodyToFlux(PostDto.class);
    }
    public Mono<PostDto> create(PostDto postDto){
        return client.post()
                .uri("/posts")
                .body(Mono.just(postDto),PostDto.class)
                .retrieve()
                .bodyToMono(PostDto.class);
    }

    public Mono<PostDto> update(PostDto student){
        return client.put()
                .uri("/posts/"+student.getId())
                .body(Mono.just(student),PostDto.class)
                .retrieve()
                .bodyToMono(PostDto.class);
    }
    public Mono<Void> delete(long id){
        return client.delete()
                .uri("/posts/"+id)
                .retrieve()
                .bodyToMono(Void.class);
    }





}
