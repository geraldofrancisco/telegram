package com.thor.telegram.repository;

import com.thor.telegram.model.BotDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends ReactiveCrudRepository<BotDomain,String> {
}
