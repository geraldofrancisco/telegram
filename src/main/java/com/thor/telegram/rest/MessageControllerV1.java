package com.thor.telegram.rest;

import com.thor.telegram.dto.message.MessageTextRequest;
import com.thor.telegram.mapper.MessageMapper;
import com.thor.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.thor.telegram.enumereble.MessageType.TEXT;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageControllerV1 {
    private final MessageService service;
    private final MessageMapper mapper;

    @PostMapping("/text")
    public Mono<Void> sendMessage(@RequestBody MessageTextRequest request) {
       return Mono.just(request)
               .map(r -> mapper.toDomain(r, TEXT))
               .flatMap(service::sendText);
    }
}
