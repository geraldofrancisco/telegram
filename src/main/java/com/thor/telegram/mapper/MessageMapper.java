package com.thor.telegram.mapper;

import com.thor.telegram.dto.message.MessageRequest;
import com.thor.telegram.enumereble.MessageType;
import com.thor.telegram.model.MessageDomain;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface MessageMapper {
    MessageDomain toDomain(MessageRequest request, MessageType type);
}
