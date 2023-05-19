package com.thor.telegram.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageControllerV1 {

    @PostMapping("/text")
    public Mono<Void> sendMessage() {
       return Mono.empty();
    }
}
