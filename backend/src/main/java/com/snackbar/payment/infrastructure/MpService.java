package com.snackbar.payment.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class MpService {

    private final WebClient webClient;

    public MpService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String postMercadoPago(Object requestBody) {
        return webClient.post()
                .uri("/payments/mercadopago")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public void patchBackMercadoPago(String uri, Object requestBody) {
        Mono.delay(Duration.ofSeconds(10))
                .flatMap(delay -> webClient.patch()
                        .uri(uri)
                        .bodyValue(requestBody)
                        .retrieve()
                        .bodyToMono(String.class))
                .subscribe();
    }
}
