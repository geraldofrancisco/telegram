package com.thor.telegram.integration;

import com.thor.telegram.dto.integration.TextIntegrationRequest;
import com.thor.telegram.model.MessageDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MessageIntegration {
    @Value("${app.telegram-api.base}")
    private String base;

    @Value("${app.telegram-api.send-text}")
    private String sendText;

    @Value("${app.telegram-api.send-photo}")
    private String sendPhoto;

    private final WebClient webClient;

    public Mono<Void> sendTextMessage(MessageDomain domain) {
        var url = UriComponentsBuilder.fromUriString(base)
                .path(sendText).buildAndExpand("6068453482:AAFkjxaVc7MUR05cf163pe4gpx72aSLGZxw").toString();

        var request = TextIntegrationRequest.builder()
                .text(domain.getMessage())
                .chat(domain.getChat())
                .build();

        return webClient
                .post()
                .uri(url)
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity()
                .then();
    }
}
