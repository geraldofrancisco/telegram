package com.thor.telegram.repository;

import com.thor.telegram.model.ChatDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ChatRepository extends ReactiveCrudRepository<ChatDomain,String> {
    Mono<Boolean> existsByName(String name);
    Mono<ChatDomain> findByName(String name);
}
