# BTG Leads – Pré-Cadastro de Leads

Projeto desenvolvido como parte do desafio técnico para a vaga de Desenvolvedor Java Sênior no BTG.

## 🧩 Visão Geral

O sistema BTG Leads é uma aplicação full-stack (Spring Boot + React) para **cadastrar leads** de uma campanha nacional, enviando-os para uma fila RabbitMQ e salvando-os no banco de dados PostgreSQL.

### ✅ Funcionalidades:
---
- [x] Formulário responsivo e validado de cadastro de leads
- [x] Envio de leads para uma fila RabbitMQ
- [x] Persistência dos dados no banco PostgreSQL
- [x] Interface estilo landing page
- [x] Integração via REST API
- [x] Validações e feedback de sucesso/erro
- [x] Swagger para documentação da API

### ✅ Arquitetura
---
- **Frontend**: React + Vite + Tailwind CSS
- **Backend**: Spring Boot (Java 11) + JPA + RabbitMQ
- **Banco de Dados**: PostgreSQL
- **Mensageria**: RabbitMQ
- **Ambiente**: Docker + Docker Compose


---

### 6. ⚙️ **Estrutura das pastas**
Ajuda a entender o projeto rapidamente:

```md
    btg-leads/
    ├── backend/
    ├── frontend/
    ├── docker-compose.yml
    └── README.md

 ```   
## 🚀 Como rodar o projeto localmente

É necessário ter **Docker** e **Docker Compose** instalados.

```bash
# Clone o repositório
git clone https://github.com/sergiolemos/btg-leads.git
cd btg-leads

# Suba a aplicação com Docker Compose
docker-compose up --build


## 👤 Autor

Desenvolvido por **Sérgio Lemos** para o desafio técnico do BTG Pactual.

- 📧 E-mail: sergio@email.com  
- 📱 Telefone: (86) 9 9999-9999  
- 🔗 GitHub: [github.com/sergiolemos](https://github.com/sergiolemos)  
- 💼 LinkedIn: [linkedin.com/in/sergiolemos](https://linkedin.com/in/sergiolemos)
