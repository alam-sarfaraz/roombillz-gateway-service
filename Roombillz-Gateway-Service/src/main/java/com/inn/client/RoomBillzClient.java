package com.inn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.inn.dto.AuthResponse;
import com.inn.dto.LoginRequest;

import jakarta.validation.Valid;

@FeignClient(name = "ROOMBILLZ-SERVICE", url = "${roombillz.service.url}", path = "/login", configuration = { FeignConfig.class, FeignRetryConfig.class })
public interface RoomBillzClient {
	
	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest);

}
