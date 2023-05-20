package com.thor.telegram.configuration;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import static io.netty.handler.ssl.util.InsecureTrustManagerFactory.INSTANCE;

@Configuration
public class WebClientConfiguration {

    @Bean
    @SneakyThrows
    public WebClient getWebClient() {
        SslContext context = SslContextBuilder.forClient()
                .trustManager(INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
