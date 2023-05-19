package com.thor.telegram.repository;

import com.thor.telegram.model.BotDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BotRepository extends ReactiveCrudRepository<BotDomain,String> {
    Mono<Boolean> existsByName(String name);
    Mono<BotDomain> findByName(String name);
}
