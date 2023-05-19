package com.thor.telegram.mapper;

import com.thor.telegram.dto.chat.ChatRequest;
import com.thor.telegram.dto.chat.ChatResponse;
import com.thor.telegram.model.ChatDomain;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {BotMapper.class})
public interface ChatMapper {
    ChatResponse toResponse(ChatDomain domain);
    ChatDomain toDomain(ChatRequest request);
}
