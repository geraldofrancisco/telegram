package com.thor.telegram.service;

import com.thor.telegram.dto.chat.ChatRequest;
import com.thor.telegram.dto.chat.ChatResponse;
import com.thor.telegram.exception.BusinessException;
import com.thor.telegram.mapper.ChatMapper;
import com.thor.telegram.model.ChatDomain;
import com.thor.telegram.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.thor.telegram.constant.ResponseConstant.CHAT_EXISTING;
import static com.thor.telegram.constant.ResponseConstant.CHAT_NOT_EXIST;
import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository repository;
    private final ChatMapper mapper;
    private final BotService botService;

    public Mono<ChatResponse> save(ChatRequest saving) {
        return botService.getByName(saving.getBotName())
                .flatMap(bot -> repository.existsByName(saving.getName())
                        .filter(not(Boolean::booleanValue))
                        .flatMap(exists -> Mono.just(saving)
                                .map(mapper::toDomain)
                                .flatMap(repository::save)
                                .map(mapper::toResponse)
                                .flatMap(chat -> {
                                    chat.setBot(bot);
                                    return Mono.just(chat);
                                })
                        )
                )
                .switchIfEmpty(Mono.error(new BusinessException(CHAT_EXISTING)));
    }

    public Mono<ChatResponse> getByName(String name) {
        return repository.findByName(name)
                .flatMap(chatDomain ->
                        botService.getByName(chatDomain.getBotName())
                        .flatMap(bot -> {
                            var chatResponse = mapper.toResponse(chatDomain);
                            chatResponse.setBot(bot);
                            return Mono.just(chatResponse);
                        })
                )
                .switchIfEmpty(Mono.error(new BusinessException(CHAT_NOT_EXIST)));
    }
}
