package com.thor.telegram.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(schema = "telegram", name = "chat")
public class ChatDomain {
    @Id
    @Column("nome")
    private String name;

    @Column("nome_bot")
    private String botName;

    @Transient
    private BotDomain bot;
}
