package com.thor.telegram.service;

import com.thor.telegram.dto.message.MessageRequest;
import com.thor.telegram.integration.MessageIntegration;
import com.thor.telegram.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.data.util.Pair.of;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;
    private final MessageIntegration messageTextIntegration;
    private final ChatService chatService;

    public Mono<Void> sendText(MessageRequest request) {
        return chatService.getByName(request.getChat())
                .flatMap(chat ->
                    Mono.just(of(request, chat))
                            .flatMap(messageTextIntegration::sendTextMessage)
                            .flatMap(repository::save)
                            .then()
                );
    }
}
