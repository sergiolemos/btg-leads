# BTG Leads – Pré-Cadastro de Leads

Projeto desenvolvido como parte do desafio técnico para a vaga de Desenvolvedor Java Sênior no BTG.

## 🧩 Visão Geral

O sistema BTG Leads é uma aplicação full-stack (Spring Boot + React) para **cadastrar leads** de uma campanha nacional, enviando-os para uma fila RabbitMQ e salvando-os no banco de dados PostgreSQL.

### ✅ Funcionalidades:

- Formulário responsivo e validado de cadastro de leads
- Envio de leads para uma fila RabbitMQ
- Persistência dos dados no banco PostgreSQL
- Interface estilo landing page
- Integração via REST API
- Validações, máscaras e feedback de sucesso/erro
- Swagger para documentação da API

---

## 🚀 Como rodar o projeto localmente

É necessário ter **Docker** e **Docker Compose** instalados.

```bash
# Clone o repositório
git clone https://github.com/sergiolemos/btg-leads.git
cd btg-leads

# Suba a aplicação com Docker Compose
docker-compose up --build
