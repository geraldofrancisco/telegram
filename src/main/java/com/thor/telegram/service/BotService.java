package com.thor.telegram.service;

import com.thor.telegram.exception.BusinessException;
import com.thor.telegram.model.BotDomain;
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
    public Mono<BotDomain> save(BotDomain saving) {
        return repository.existsByName(saving.getName())
                .filter(not(Boolean::booleanValue))
                .flatMap(exists  -> repository.save(saving))
                .switchIfEmpty(Mono.error(new BusinessException(BOT_EXISTING)));
    }

    public Mono<BotDomain> getByName(String name) {
        return repository.findById(name)
                .switchIfEmpty(Mono.error(new BusinessException(BOT_NOT_EXIST)));
    }
}
