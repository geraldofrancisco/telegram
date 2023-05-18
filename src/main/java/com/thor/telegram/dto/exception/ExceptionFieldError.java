package com.thor.telegram.dto.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.thor.telegram.constant.ResponseConstant.MESSAGES_ERROR;
import static com.thor.telegram.constant.ResponseConstant.MESSAGES_FIELD;

@AllArgsConstructor
@Data
public class ExceptionFieldError {

    public ExceptionFieldError(String error) {
        this.error = error;
    }

    @Schema(description = MESSAGES_FIELD)
    private String field;

    @Schema(description = MESSAGES_ERROR)
    private String error;
}
