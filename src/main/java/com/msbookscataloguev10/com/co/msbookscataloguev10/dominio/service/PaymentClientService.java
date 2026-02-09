package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service;

import org.springframework.web.reactive.function.client.WebClient;

public class PaymentClientService {

    private final WebClient webClient;
    public PaymentClientService(WebClient.Builder builder) {
        this.webClient= builder.baseUrl("lb://payment-service").build();
    }

    public Integer cuantosFacturados(Long idLibro){
        return webClient.get()
                .uri("/productos/facturados/{id}",idLibro)
                .retrieve().bodyToMono(Integer.class).block();
    }

}
