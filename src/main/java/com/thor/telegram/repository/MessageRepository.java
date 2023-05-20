package com.thor.telegram.repository;

import com.thor.telegram.model.MessageDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ReactiveCrudRepository<MessageDomain,String> {
}
