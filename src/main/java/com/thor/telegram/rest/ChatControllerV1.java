package com.thor.telegram.rest;

import com.thor.telegram.dto.chat.ChatRequest;
import com.thor.telegram.dto.chat.ChatResponse;
import com.thor.telegram.mapper.ChatMapper;
import com.thor.telegram.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatControllerV1 {
    private final ChatService service;
    private final ChatMapper mapper;

    @PostMapping("/save")
    public Mono<ChatResponse> save(@RequestBody ChatRequest request) {

        return Mono.just(request)
                .map(mapper::toDomain)
                .flatMap(service::save)
                .map(mapper::toResponse);
    }
}
