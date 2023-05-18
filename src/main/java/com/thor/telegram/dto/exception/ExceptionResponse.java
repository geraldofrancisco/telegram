package com.thor.telegram.dto.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static com.thor.telegram.constant.PatternConstant.DATE_TIME_PATTERN;
import static com.thor.telegram.constant.ResponseConstant.*;
import static java.time.LocalDateTime.*;

@Builder
@Data
public class ExceptionResponse {
    @Schema(description = STATUS)
    private Integer status;

    @Schema(description = TIMESTAMP)
    @Builder.Default
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime timestamp = now();

    @Schema(description = MESSAGES)
    private List<ExceptionFieldError> messages;
}
