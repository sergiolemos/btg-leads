package com.btg.leads.api.controller.dto;

import com.btg.leads.api.domain.model.Lead;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LeadCadastroDto(
        @NotBlank(message = "{nome.obrigatorio}") String nome,
        @NotBlank(message = "email.obrigatorio") @Email(message = "email.invalido") String email,
        @NotBlank(message = "telefone.obrigatorio") String telefone) {

    public LeadCadastroDto(Lead lead) {
        this(lead.getNome(), lead.getEmail(), lead.getTelefone());
    }
}
