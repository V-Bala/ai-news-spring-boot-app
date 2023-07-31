package com.yourdomain.freepost;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class PostService {
    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";
    private final PostRepository postRepository;
    private final WebClient webClient;

    public PostService(PostRepository postRepository, WebClient.Builder webClientBuilder) {
        this.postRepository = postRepository;
        this.webClient = webClientBuilder.baseUrl(API_URL).build();
    }

    public Mono<ManipulatedPost> getPost() {
        return webClient.get()
                .retrieve()
                .bodyToMono(Post[].class)
                .doOnNext(posts -> postRepository.saveAll(Arrays.asList(posts)))
                .map(posts -> {
                    Post post = posts[0];
                    ManipulatedPost manipulatedPost = new ManipulatedPost();
                    manipulatedPost.setTitle(post.getTitle());
                    manipulatedPost.setBody(post.getBody());
                    return manipulatedPost;
                });
    }
}

