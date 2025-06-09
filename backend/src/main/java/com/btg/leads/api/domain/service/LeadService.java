package com.btg.leads.api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.btg.leads.api.controller.dto.LeadCadastroDto;
import com.btg.leads.api.controller.dto.LeadDetalheDto;
import com.btg.leads.api.domain.model.Lead;
import com.btg.leads.api.domain.producer.LeadProducer;
import com.btg.leads.api.domain.repository.LeadRepository;
import com.btg.leads.api.infra.exception.ValidacaoException;

@Service
public class LeadService {

    private final LeadRepository leadRepository;
    private final LeadProducer leadProducer;

    public LeadService(LeadRepository leadRepository, LeadProducer leadProducer) {
        this.leadRepository = leadRepository;
        this.leadProducer = leadProducer;
    }

    public List<LeadDetalheDto> listar() {
        return leadRepository.findAll()
                .stream()
                .map(LeadDetalheDto::new)
                .toList();

    }

    public LeadDetalheDto consultar(Long id) {
        return leadRepository.findById(id)
                .stream()
                .map(LeadDetalheDto::new)
                .findAny()
                .orElseThrow(() -> new ValidacaoException("lead.naoEncontrado"));
    }

    public LeadDetalheDto cadastrar(LeadCadastroDto dto) {
        var lead = new Lead(dto);
        leadRepository.save(lead);
        leadProducer.enviarLeadParaFila(lead);
        return new LeadDetalheDto(lead);
    }

    public LeadDetalheDto atualizar(Long id, LeadCadastroDto dto) {
        var lead = leadRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("lead.naoEncontrado"));
        lead.atualizar(dto);
        leadRepository.save(lead);
        return new LeadDetalheDto(lead);
    }

    public void excluir(Long id) {
        var lead = leadRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("lead.naoEncontrado"));
        leadRepository.delete(lead);

    }
}
