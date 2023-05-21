package com.thor.telegram.rest;

import com.thor.telegram.dto.message.MessageRequest;
import com.thor.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageControllerV1 {
    private final MessageService service;

    @PostMapping("/text")
    public Mono<Void> sendMessage(@RequestBody MessageRequest request) {
       return Mono.just(request)
               .flatMap(service::sendText);
    }
}
