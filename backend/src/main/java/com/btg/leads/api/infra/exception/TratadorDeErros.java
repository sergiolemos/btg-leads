package com.btg.leads.api.infra.exception;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

        @Autowired
        private MessageSource messageSource;

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> erro400(MethodArgumentNotValidException ex) {
                List<DadosErroValidacao> erros = ex.getFieldErrors().stream()
                                .map(erro -> new DadosErroValidacao(
                                                erro.getField(),
                                                messageSource.getMessage(erro, LocaleContextHolder.getLocale())))
                                .toList();

                return ResponseEntity.badRequest().body(new ErroValidacaoResponse(erros));
        }

        @ExceptionHandler(ValidacaoException.class)
        public ResponseEntity<?> erroNegocio(ValidacaoException ex) {
                String mensagemTraduzida = messageSource.getMessage(
                                ex.getMensagemChave(), null, ex.getMensagemChave(), LocaleContextHolder.getLocale());

                return ResponseEntity.badRequest()
                                .body(new ErroResponse(HttpStatus.BAD_REQUEST.value(), mensagemTraduzida));
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<?> erro404() {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ErroResponse(HttpStatus.NOT_FOUND.value(), "Recurso não encontrado"));
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<?> erroJson() {
                return ResponseEntity.badRequest()
                                .body(new ErroResponse(HttpStatus.BAD_REQUEST.value(), "Formato de JSON inválido"));
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<?> erroAcessoNegado() {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                .body(new ErroResponse(HttpStatus.FORBIDDEN.value(), "Acesso negado"));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> erro500(Exception ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                "Erro interno do servidor", LocalDateTime.now(), ex.getMessage()));
        }
}