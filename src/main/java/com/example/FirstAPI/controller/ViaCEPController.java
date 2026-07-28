package com.example.FirstAPI.controller;

import com.example.FirstAPI.DTO.ViaCEPResponseDTO;
import com.example.FirstAPI.service.ViaCEPService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViaCEPController {
    final ViaCEPService viaCEPService;

    public ViaCEPController(ViaCEPService viaCEPService) {
        this.viaCEPService = viaCEPService;
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<ViaCEPResponseDTO> buscarPorCep(@PathVariable String cep){
        return ResponseEntity.ok(viaCEPService.buscarPorCep(cep));
    }
}
