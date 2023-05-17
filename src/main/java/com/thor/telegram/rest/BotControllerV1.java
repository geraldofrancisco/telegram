package com.thor.telegram.rest;

import com.thor.telegram.model.BotDomain;
import com.thor.telegram.repository.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bot")
public class BotControllerV1 {

    @Autowired
    private BotRepository repository;

    @PostMapping("/save")
    public Mono<BotDomain> save() {
        var bot = BotDomain.builder()
                .name(UUID.randomUUID().toString())
                .token("xpto")
                .user("user")
                .build();
        return repository.save(bot);
    }
}
