package com.thor.telegram.model;

import com.thor.telegram.enumereble.MessageType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Data
@Builder
@Table(schema = "telegram", name = "mensagem")
public class MessageDomain {

    @Column
    private String id;

    @Column("chat_id")
    private String chat;

    @Column("mensagem")
    private String message;

    @Column("arquivo_base_64")
    private String fileBase64;

    @Column("tipo")
    private MessageType type;

    @Column("data_hora_criacao")
    @Builder.Default
    private LocalDateTime dateTimeCreation = now();

    @Column("data_hora_envio")
    private LocalDateTime dateTimeSending;
}
