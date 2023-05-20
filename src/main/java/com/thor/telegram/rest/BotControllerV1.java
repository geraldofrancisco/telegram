package com.thor.telegram.rest;

import com.thor.telegram.dto.bot.BotRequest;
import com.thor.telegram.dto.bot.BotResponse;
import com.thor.telegram.mapper.BotMapper;
import com.thor.telegram.model.BotDomain;
import com.thor.telegram.repository.BotRepository;
import com.thor.telegram.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bot")
@RequiredArgsConstructor
public class BotControllerV1 {

    private final BotService service;
    private final BotMapper mapper;

    @PostMapping("/save")
    public Mono<BotResponse> save(@RequestBody BotRequest request) {

        return Mono.just(request)
                .flatMap(service::save);
    }
}
