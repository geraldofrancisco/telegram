package com.thor.telegram.integration;

import com.thor.telegram.dto.chat.ChatResponse;
import com.thor.telegram.dto.integration.TextIntegrationRequest;
import com.thor.telegram.dto.message.MessageTextRequest;
import com.thor.telegram.exception.IntegrationException;
import com.thor.telegram.model.MessageDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static java.time.Duration.ofSeconds;

@Component
@RequiredArgsConstructor
public class MessageIntegration extends IntegrationBase{
    @Value("${app.telegram-api.base}")
    private String base;

    @Value("${app.telegram-api.send-text}")
    private String sendText;

    @Value("${app.telegram-api.send-photo}")
    private String sendPhoto;

    private final WebClient webClient;

    public Mono<Void> sendTextMessage(Pair<MessageTextRequest, ChatResponse> pair) {
        var url = UriComponentsBuilder.fromUriString(base)
                .path(sendText).buildAndExpand(pair.getSecond().getBot().getToken()).toString();

        var request = TextIntegrationRequest.builder()
                .text(pair.getFirst().getMessage())
                .chat(pair.getFirst().getChat())
                .build();

        return webClient
                .post()
                .uri(url)
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity()
                .timeout(ofSeconds(this.timeout))
                .retryWhen(this.retry())
                .onErrorResume(e -> Mono.error(IntegrationException::new))
                .then();
    }
}
