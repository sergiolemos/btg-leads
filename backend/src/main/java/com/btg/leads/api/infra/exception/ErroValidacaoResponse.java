package com.btg.leads.api.infra.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public record ErroValidacaoResponse(
        int status,
        String erro,
        LocalDateTime timestamp,
        List<DadosErroValidacao> erros) {

    public ErroValidacaoResponse(List<DadosErroValidacao> erros) {
        this(HttpStatus.BAD_REQUEST.value(), "Erro de validação", LocalDateTime.now(), erros);
    }
}