package com.btg.leads.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.btg.leads.api.controller.dto.LeadCadastroDto;
import com.btg.leads.api.controller.dto.LeadDetalheDto;
import com.btg.leads.api.domain.service.LeadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    public ResponseEntity<List<LeadDetalheDto>> listar() {
        return ResponseEntity.ok(leadService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadDetalheDto> consultar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(leadService.consultar(id));
    }

    @PostMapping
    public ResponseEntity<LeadDetalheDto> cadastrar(@RequestBody @Valid LeadCadastroDto dto) {
        var leadCriado = leadService.cadastrar(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(leadCriado.id())
                .toUri();

        return ResponseEntity.created(uri).body(leadCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeadDetalheDto> atualizar(@PathVariable("id") Long id,
            @RequestBody LeadCadastroDto dto) {
        return ResponseEntity.ok(leadService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        leadService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
