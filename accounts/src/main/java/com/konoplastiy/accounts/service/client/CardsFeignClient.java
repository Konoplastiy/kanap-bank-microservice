package com.konoplastiy.accounts.service.client;

import com.konoplastiy.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/v1/cards",consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("kanap-correlation-id")
                                                     String correlationId, @RequestParam String mobileNumber);

}
