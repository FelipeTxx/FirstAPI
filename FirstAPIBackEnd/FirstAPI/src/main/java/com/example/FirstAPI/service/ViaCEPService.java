package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.ViaCEPResponseDTO;
import com.example.FirstAPI.config.RestClientConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ViaCEPService {

    final RestClient restClient;

    public ViaCEPService(RestClient restClient) {

        this.restClient = restClient;
    }

    public ViaCEPResponseDTO buscarPorCep(String cep){
        return restClient.get().uri("https://viacep.com.br/ws/{cep}/json/", cep).retrieve().body(ViaCEPResponseDTO.class);
    }

}
