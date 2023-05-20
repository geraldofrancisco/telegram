package com.thor.telegram.service;

import com.thor.telegram.integration.MessageIntegration;
import com.thor.telegram.model.MessageDomain;
import com.thor.telegram.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;
    private final MessageIntegration messageTextIntegration;
    private final ChatService chatService;

    public Mono<Void> sendText(MessageDomain domain) {
        return chatService.getByName(domain.getChat())
                .flatMap(chat -> {
                    //domain.setChatDomain(chat);
                    return Mono.just(domain)
                            .flatMap(repository::save)
                            .flatMap(messageTextIntegration::sendTextMessage);
                });
    }
}
