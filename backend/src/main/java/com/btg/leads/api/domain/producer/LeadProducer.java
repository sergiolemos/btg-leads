package com.btg.leads.api.domain.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.leads.api.domain.model.Lead;
import com.btg.leads.api.infra.config.RabbitMQConfig;

@Service
public class LeadProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public LeadProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarLeadParaFila(Lead lead) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE, lead);
    }
}
