package com.thor.telegram.dto.chat;

import com.thor.telegram.dto.bot.BotResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChatResponse {
    private String name;
    private BotResponse bot;
}
