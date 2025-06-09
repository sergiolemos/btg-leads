package com.btg.leads.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.btg.leads.api.domain.model.Lead;
import com.btg.leads.api.domain.service.LeadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/leads")
@Tag(name = "Leads", description = "Gerenciamento de leads para campanha")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os leads cadastrados", responses = {
            @ApiResponse(responseCode = "200", description = "Leads retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Lead.class)))
    })
    public ResponseEntity<List<LeadDetalheDto>> listar() {
        return ResponseEntity.ok(leadService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar o leads por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lead encontrado"),
            @ApiResponse(responseCode = "404", description = "Lead não encontrado")
    })
    public ResponseEntity<LeadDetalheDto> consultar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(leadService.consultar(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo lead")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Lead cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
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
    @Operation(summary = "Atualizar lead")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lead atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lead não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<LeadDetalheDto> atualizar(@PathVariable("id") Long id,
            @RequestBody LeadCadastroDto dto) {
        return ResponseEntity.ok(leadService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir leads")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Lead excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lead não encontrado"),
    })
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        leadService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
