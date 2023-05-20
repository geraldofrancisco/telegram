package com.thor.telegram.service;

import com.thor.telegram.dto.bot.BotRequest;
import com.thor.telegram.dto.bot.BotResponse;
import com.thor.telegram.exception.BusinessException;
import com.thor.telegram.mapper.BotMapper;
import com.thor.telegram.repository.BotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.thor.telegram.constant.ResponseConstant.BOT_EXISTING;
import static com.thor.telegram.constant.ResponseConstant.BOT_NOT_EXIST;
import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class BotService {

    private final BotRepository repository;
    private final BotMapper mapper;

    public Mono<BotResponse> save(BotRequest saving) {
        return repository.existsByName(saving.getName())
                .filter(not(Boolean::booleanValue))
                .flatMap(exists -> Mono.just(saving)
                        .map(mapper::toDomain)
                        .flatMap(repository::save)
                        .map(mapper::toResponse)
                )
                .switchIfEmpty(Mono.error(new BusinessException(BOT_EXISTING)));
    }

    public Mono<BotResponse> getByName(String name) {
        return repository.findByName(name)
                .map(mapper::toResponse)
                .switchIfEmpty(Mono.error(new BusinessException(BOT_NOT_EXIST)));
    }
}
