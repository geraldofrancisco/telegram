package com.thor.telegram.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(schema = "telegram", name = "bot")
public class BotDomain {

    @Column("nome")
    private String name;

    @Column("token_acesso")
    private String token;

    @Column("usuario_id")
    private String user;
}
