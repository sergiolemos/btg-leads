package com.btg.leads.api.infra.exception;

import java.time.LocalDateTime;

public record ErroResponse(
        int status,
        String erro,
        LocalDateTime timestamp,
        String detalhes) {

    public ErroResponse(int status, String erro) {
        this(status, erro, LocalDateTime.now(), null);
    }

    public ErroResponse(int status, String erro, LocalDateTime timestamp) {
        this(status, erro, timestamp, null);
    }
}
