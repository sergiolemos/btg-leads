package com.btg.leads.api.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "leads.queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }

}
