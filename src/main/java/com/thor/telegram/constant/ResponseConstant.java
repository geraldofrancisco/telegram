package com.thor.telegram.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResponseConstant {
    public static final String STATUS = "Http request status";
    public static final String TIMESTAMP = "Error date time";
    public static final String MESSAGES = "List of error messages";
    public static final String MESSAGES_FIELD = "Field that showed the error";
    public static final String MESSAGES_ERROR = "Error message";
    //BOT
    public static final String BOT_EXISTING = "Bot já cadastrado no sistema";
    public static final String BOT_NOT_EXIST = "Bot não encontrado no sistema";
    //CHAT
    public static final String CHAT_EXISTING = "Chat já cadastrado no sistema";
    public static final String CHAT_NOT_EXIST = "Chat não encontrado no sistema";
}
