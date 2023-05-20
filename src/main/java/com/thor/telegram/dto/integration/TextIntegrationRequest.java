package com.thor.telegram.dto.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextIntegrationRequest {
    @JsonProperty("chat_id")
    private String chat;
    private String text;

    @Builder.Default
    @JsonProperty("parse_mode")
    private String mode = "HTML";
}
