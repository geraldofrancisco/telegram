package com.thor.telegram.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import static java.time.Duration.ofSeconds;
import static java.time.temporal.ChronoUnit.SECONDS;
import static reactor.util.retry.Retry.fixedDelay;

@Slf4j
public abstract class IntegrationBase {
    @Value("${app.connection.retry.seconds-interval}")
    private Long retrySeconds;

    @Value("${app.connection.retry.quantity}")
    private Long retryQuantity;

    @Value("${app.connection.seconds-timeout}")
    protected Long timeout;



    protected RetryBackoffSpec retry() {
        return fixedDelay(retryQuantity, ofSeconds(retrySeconds, SECONDS.ordinal()))
                .doBeforeRetry(retrySignal -> log.info("[{}] Retry connection. Attempt: {}. Cause: {}",
                        this.getClass().getName(), retrySignal.totalRetries(), retrySignal.failure().getMessage()));
    }
}
