package com.btg.leads.api.controller.dto;

import java.time.LocalDateTime;

import com.btg.leads.api.domain.model.Lead;

public record LeadDetalheDto(
        Long id,
        String nome,
        String email,
        String telefone,
        LocalDateTime dataCadastro) {

    public LeadDetalheDto(Lead lead) {
        this(lead.getId(), lead.getNome(), lead.getEmail(), lead.getTelefone(), lead.getCreatedAt());
    }
}
