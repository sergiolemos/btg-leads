package com.btg.leads.api.infra.exception;

public class ValidacaoException extends RuntimeException {

    private final String mensagemChave;

    public ValidacaoException(String mensagemChave) {
        super(mensagemChave);
        this.mensagemChave = mensagemChave;
    }

    public String getMensagemChave() {
        return mensagemChave;
    }

}
