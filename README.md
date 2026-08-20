# DIO Spring Boot Learning Track

Este projeto foi desenvolvido originalmente pela DIO como parte da trilha de estudos em java e AI.

A partir do projeto-base, realizei uma implementação incremental como parte do desafio do bootcamp, adicionando melhorias e adaptações próprias com o objetivo de aprofundar os conhecimentos em Spring Boot, Spring AI, validações, persistência de dados e testes automatizados.

A documentação original do projeto foi mantida abaixo para preservar as informações e explicações fornecidas durante a trilha. As alterações e funcionalidades adicionadas por mim estão documentadas na seção Evolução do projeto.

This repository contains a DIO Spring Boot learning track organized as incremental modules.

The track starts with architecture foundations and progressively moves through web APIs, data access, security, service integration, and AI-enabled workflows.

<img width="2752" height="1536" alt="unnamed" src="https://github.com/user-attachments/assets/a7bcbe19-4d0c-4395-8696-8c64be22764f" />

## Modules

- [`00-domain-driven-design`](00-domain-driven-design/README.md)
  DDD foundations with a catalog domain and no web layer.
- [`01-spring-web`](01-spring-web/README.md)
  REST API design with Spring Web and API documentation with Spring REST Docs.
- [`02-spring-data`](02-spring-data/README.md)
  Data access in a multi-context application using MySQL, MongoDB, Redis, and PostgreSQL.
- [`03-spring-security`](03-spring-security/README.md)
  Authentication and authorization with Spring Security in a proposal management API.
- [`04-spring-cloud-openfeign`](04-spring-cloud-openfeign/README.md)
  External service integration (KYC/AML) using Spring Cloud OpenFeign and resilience patterns.
- [`05-spring-ai`](05-spring-ai/README.md)
  Final project using Spring AI for speech-to-text, tool calling, and text-to-speech.

## Recommended Study Order

1. [`00-domain-driven-design`](00-domain-driven-design/README.md)
2. [`01-spring-web`](01-spring-web/README.md)
3. [`02-spring-data`](02-spring-data/README.md)
4. [`03-spring-security`](03-spring-security/README.md)
5. [`04-spring-cloud-openfeign`](04-spring-cloud-openfeign/README.md)
6. [`05-spring-ai`](05-spring-ai/README.md)

---

## Shared Architecture Guide

The sections below consolidate architecture topics that are intentionally reused across modules.

### DDD Layered Architecture

Most modules follow the same conceptual split:

```text
domain/          -> business model, invariants, contracts
application/     -> use cases, orchestration, application policies
infrastructure/  -> adapters (HTTP, persistence, external clients, framework glue)
```

Why this matters:

- `domain` stays focused on business language and rules, not framework details.
- `application` coordinates domain behavior for specific user/business actions.
- `infrastructure` can change (database, web transport, external APIs) without forcing core business rewrites.

This separation reduces coupling and supports long-term maintainability.

### Java Class vs Java Record in Domain Modeling

A practical guideline used across the track:

- Use `class` for entities/aggregates that have identity and may evolve behavior over time.
- Use `record` for immutable value objects and DTO-style transport models.

Design trade-offs:

- `class` supports richer lifecycle behavior and controlled mutation.
- `record` reduces boilerplate and makes immutability explicit.

This distinction improves code intent and keeps domain concepts clearer.

### Strong Typed Identifiers

Instead of passing raw primitives (`UUID`, `String`) everywhere, modules wrap identifiers in explicit types such as `BookId`, `TaskId`, `ProposalId`, and `TransactionId`.

Benefits:

- Better compile-time safety (fewer accidental ID mix-ups).
- More expressive signatures (`findById(TaskId id)` communicates intent).
- Cleaner evolution path for ID rules and validation.

### Repository Pattern

The repository contract belongs to the business side, while technology-specific implementations stay in infrastructure.

Pattern used in this repository:

- Domain contract: `XxxRepository` in `domain/`.
- Adapter implementation: JPA/in-memory/etc. in `infrastructure/`.

Architectural impact:

- Business logic depends on abstractions, not persistence frameworks.
- Switching storage technology becomes an adapter change, not a domain rewrite.
- Unit testing use cases becomes simpler with fake/mock repositories.

### Use Cases and Clean Architecture

Each use case models one business capability (for example, create task, list proposals, analyze company risk).

Common flow:

1. Controller/listener receives an external request.
2. It calls one application use case.
3. The use case orchestrates domain objects and repository/gateway contracts.
4. Infrastructure adapters handle persistence or external integrations.

Why this is important:

- Strong single-responsibility boundaries.
- Easier testability and refactoring.
- Better readability of business workflows.

### Docker Compose Support in Development

Several modules include `compose.yml` and Spring Boot Docker Compose support.

Typical local development role:

- Start required infra services (database/cache/message dependencies).
- Keep local setup reproducible for all students.
- Reduce onboarding friction by standardizing environment dependencies.

Note: exact behavior can vary by module configuration and runtime profile.

---

## Quick Start

Choose a module and run its local instructions:

```bash
cd 01-spring-web
./gradlew test
```

For module-specific details, always check each module README from the links above.

## Evolução do projeto

## Sobre o projeto

Este projeto é uma trilha de estudos em Spring Boot. O módulo final é uma API de controle financeiro que registra e consulta transações.

### Como executar?

É necessário ter Java 25, Docker e uma chave da OpenAI configurada:

```bash
export OPENAI_API_KEY="sua_chave_aqui" (pegar chave no site da OpenAI)
cd 05-spring-ai
docker compose up -d
./gradlew bootRun
```

### Melhoria implementada

Foi adicionada validação de transações para:

- impedir valores menores ou iguais a zero
- impedir descrições vazias
- impedir categorias nulas ou inválidas.

### Tecnologias utilizadas

- Java 25;
- Spring Boot;
- Spring AI;
- Spring Data JPA;
- MySQL;
- Docker Compose;
- JUnit.
- Ferramentas de apoio ao desenvolvimento: ChatGPT e GitHub Copilot.

### Como testar?

Para testar as validações do fluxo principal:

```bash
cd 05-spring-ai
./gradlew test --tests "dio.budgeting.domain.TransactionTest"
```

Para executar todos os testes:

```bash
./gradlew test
```

## Aprendizados
Durante o desenvolvimento, aprendi como integrar recursos de inteligência artificial a uma aplicação real utilizando Spring AI, além de compreender melhor o uso de Tool Calling e a integração entre IA, regras de negócio e persistência de dados.
Durante a configuração do ambiente, identifiquei que o Codespace estava utilizando o JDK 11, apesar de o projeto exigir Java 25. Isso exigiu a configuração correta do JDK no ambiente para permitir a execução da aplicação.
Também aprendi a importância de manter uma documentação clara e organizada, especialmente em projetos que possuem diferentes módulos e tecnologias.


