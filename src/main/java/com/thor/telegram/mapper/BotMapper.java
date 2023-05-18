package com.thor.telegram.mapper;

import com.thor.telegram.dto.bot.BotRequest;
import com.thor.telegram.dto.bot.BotResponse;
import com.thor.telegram.model.BotDomain;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface BotMapper {
    BotDomain toDomain(BotRequest dto);
    BotResponse toResponse(BotDomain domain);
}
