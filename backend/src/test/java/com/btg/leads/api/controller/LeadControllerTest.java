package com.btg.leads.api.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.btg.leads.api.controller.dto.LeadCadastroDto;
import com.btg.leads.api.controller.dto.LeadDetalheDto;

@SpringBootTest
public class LeadControllerTest {

    @Autowired
    private LeadController leadController;

    @Test
    void testCadastrar() {
        LeadCadastroDto dto = new LeadCadastroDto(
                "Maximus Lemos",
                "teste@maximus.com.br",
                "(86)994849656");

        ResponseEntity<LeadDetalheDto> response = leadController.cadastrar(dto);
        LeadDetalheDto lead = response.getBody();

        assertNotNull(lead);
        assertNotNull(lead.id());
        assertEquals("Maximus Lemos", lead.nome());
        assertEquals("teste@maximus.com.br", lead.email());
        assertEquals("(86)994849656", lead.telefone());
    }
}
