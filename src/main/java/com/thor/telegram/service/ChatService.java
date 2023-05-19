package com.thor.telegram.service;

import com.thor.telegram.exception.BusinessException;
import com.thor.telegram.model.ChatDomain;
import com.thor.telegram.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.thor.telegram.constant.ResponseConstant.CHAT_EXISTING;
import static com.thor.telegram.constant.ResponseConstant.CHAT_NOT_EXIST;
import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository repository;
    private final BotService botService;
    public Mono<ChatDomain> save(ChatDomain saving) {
        return botService.getByName(saving.getBotName())
                .flatMap(bot -> repository.existsByName(saving.getName())
                        .filter(not(Boolean::booleanValue))
                        .flatMap(exists -> repository.save(saving)
                                .flatMap(chat -> {
                                    chat.setBot(bot);
                                    return Mono.just(chat);
                                })
                        )
                )
                .switchIfEmpty(Mono.error(new BusinessException(CHAT_EXISTING)));
    }

    public Mono<ChatDomain> getByName(String name) {
        return repository.findById(name)
                .flatMap(chat -> botService.getByName(chat.getBotName())
                        .flatMap(bot -> {
                            chat.setBot(bot);
                            return Mono.just(chat);
                        }))
                .switchIfEmpty(Mono.error(new BusinessException(CHAT_NOT_EXIST)));
    }
}
