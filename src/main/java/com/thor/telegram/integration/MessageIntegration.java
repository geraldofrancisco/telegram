package com.thor.telegram.integration;

import com.thor.telegram.model.MessageDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MessageIntegration {
    @Value("app.telegram-api.base")
    private String url;

    private final WebClient webClient;

    public Mono<Void> sendTextMessage(MessageDomain domain) {
        return Mono.empty();
    }
}
