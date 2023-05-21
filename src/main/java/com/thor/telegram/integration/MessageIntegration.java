package com.thor.telegram.integration;

import com.thor.telegram.dto.chat.ChatResponse;
import com.thor.telegram.dto.integration.TextIntegrationRequest;
import com.thor.telegram.dto.message.MessageRequest;
import com.thor.telegram.mapper.MessageMapper;
import com.thor.telegram.model.MessageDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Base64;

import static com.thor.telegram.enumereble.MessageType.PHOTO;
import static com.thor.telegram.enumereble.MessageType.TEXT;
import static java.time.Duration.ofSeconds;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageIntegration extends IntegrationBase {
    @Value("${app.telegram-api.base}")
    private String base;

    @Value("${app.telegram-api.send-text}")
    private String sendText;

    @Value("${app.telegram-api.send-photo}")
    private String sendPhoto;

    private final WebClient webClient;
    private final MessageMapper mapper;

    public Mono<MessageDomain> sendTextMessage(Pair<MessageRequest, ChatResponse> pair) {

        var request = TextIntegrationRequest.builder()
                .text(pair.getFirst().getMessage())
                .chat(pair.getFirst().getChat())
                .build();

        var uri = UriComponentsBuilder
                .fromUriString(base)
                .path(sendText).buildAndExpand(pair.getSecond().getBot().getToken())
                .toString();

        return Mono.just(pair.getFirst())
                .map(m -> mapper.toDomain(m, TEXT))
                .flatMap(m ->
                        webClient
                                .post()
                                .uri(uri)
                                .bodyValue(request)
                                .retrieve()
                                .toBodilessEntity()
                                .timeout(ofSeconds(this.timeout))
                                .retryWhen(this.retry())
                                .onErrorResume(e -> {
                                    m.setSent(false);
                                    return Mono.empty();
                                })
                                .thenReturn(m)
                );
    }

    public Mono<MessageDomain> sendPhotoMessage(Pair<MessageRequest, ChatResponse> pair) {

        var uri = UriComponentsBuilder
                .fromUriString(base)
                .path(sendPhoto)
                .queryParam("chat_id", pair.getSecond().getName())
                .buildAndExpand(pair.getSecond().getBot().getToken()).toString();


        return Mono.just(pair.getFirst())
                .map(m -> mapper.toDomain(m, PHOTO))
                .flatMap(m ->
                        webClient
                                .post()
                                .uri(uri)
                                .contentType(MULTIPART_FORM_DATA)
                                .bodyValue(getFile(pair.getFirst().getMessage()))
                                .retrieve()
                                .toBodilessEntity()
                                .timeout(ofSeconds(this.timeout))
                                .retryWhen(this.retry())
                                .onErrorResume(e -> {
                                    m.setSent(false);
                                    return Mono.empty();
                                })
                                .thenReturn(m)

                );
    }

    private MultiValueMap<String, HttpEntity<?>> getFile(String base64) {
        var array = Base64.getDecoder().decode(base64);
        var builder = new MultipartBodyBuilder();
        builder.part("photo", new ByteArrayResource(array)).filename("file");
        return builder.build();
    }

}
