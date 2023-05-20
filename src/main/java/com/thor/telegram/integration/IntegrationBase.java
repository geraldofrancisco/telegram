package com.thor.telegram.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
public abstract class IntegrationBase {
    @Value("${app.connection.retry.seconds-interval}")
    private Long retrySeconds;

    @Value("${app.connection.retry.quantity}")
    private Long retryQuantity;

    @Value("${app.connection.seconds-timeout}")
    protected Long timeout;

    protected RetryBackoffSpec retry() {
        return Retry.fixedDelay(retryQuantity, Duration.ofSeconds(retrySeconds, ChronoUnit.SECONDS.ordinal()))
                .doBeforeRetry(retrySignal -> log.info("[{}] Retry connection. Attempt: {}. Cause: {}",
                        this.getClass().getName(), retrySignal.totalRetries(), retrySignal.failure().getMessage()));
    }
}
