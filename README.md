# ⚔️ Cogitator Imperialis

> *"Conhecimento é poder. Proteja-o bem."* — Adeptus Mechanicus

Sistema tático de simulação e gerenciamento de missões no universo **Warhammer 40.000**, desenvolvido com Spring Boot, Clean Architecture e PostgreSQL.

---

## 📋 Status do Projeto

| Fase | Descrição | Status |
|------|-----------|--------|
| **Fase 1** | Fundação: Domínio de Missões, OpenAPI, Migração de BD | ✅ Concluída |
| **Fase 2** | Segurança: JWT, RBAC, ROLE_PRIMARCA | 🔄 Em andamento |
| **Fase 3** | Hierarquia Militar: Patentes, Capítulos, Space Marines | ⏳ Planejada |
| **Fase 4** | Motor de Batalha: Strategy + Factory + Observer | ⏳ Planejada |
| **Fase 5** | Integração com IA: Relatórios Narrativos via LLM | ⏳ Planejada |

---

## 🏛️ Arquitetura

O projeto segue **Clean Architecture**, garantindo que o núcleo de regras do jogo seja totalmente agnóstico a frameworks e banco de dados.

```
┌─────────────────────────────────────────────┐
│              ADAPTERS (HTTP, DB)            │  ← Spring MVC, JPA
├─────────────────────────────────────────────┤
│           INFRASTRUCTURE (Config)           │  ← Spring Beans, Security
├─────────────────────────────────────────────┤
│          APPLICATION (Use Cases)            │  ← Lógica de Orquestração
├─────────────────────────────────────────────┤
│              DOMAIN (Coração)               │  ← Zero dependência externa
└─────────────────────────────────────────────┘
```

### Camadas e Responsabilidades

| Camada | Pacote | Responsabilidade |
|--------|--------|-----------------|
| **Domain** | `domain/` | Enums, Value Objects, Exceções de domínio. Zero Spring. |
| **Application** | `application/` | Casos de Uso (interfaces + implementações). Orquestra o domínio. |
| **Infrastructure** | `infrastructure/` | JPA Entities, Repositórios, Config (OpenAPI, Security). |
| **Adapter** | `adapter/` | Controllers REST, DTOs de Request/Response. |

---

## 🛠️ Stack Tecnológica

| Tecnologia | Versão | Uso |
|-----------|--------|-----|
| Java | 21 | Linguagem principal |
| Spring Boot | 4.0.3 | Framework principal |
| Spring Security | (Boot managed) | Autenticação e Autorização (RBAC) |
| Spring Data JPA | (Boot managed) | Persistência |
| PostgreSQL | 16+ | Banco de dados principal |
| Flyway | (Boot managed) | Migrations de banco |
| springdoc-openapi | 2.8.9 | Documentação e teste da API |
| Lombok | (Boot managed) | Redução de boilerplate |
| JWT (fase 2) | A definir | Tokens de autenticação stateless |

---

## 🚀 Como Executar

### Pré-requisitos

- Java 21+
- Maven 3.9+
- PostgreSQL 16+ rodando localmente

### 1. Configurar o Banco de Dados

```sql
CREATE DATABASE cogitator_imperialis;
CREATE USER cogitator WITH ENCRYPTED PASSWORD 'omnissiah';
GRANT ALL PRIVILEGES ON DATABASE cogitator_imperialis TO cogitator;
```

### 2. Configurar Variáveis de Ambiente

Copie o arquivo de exemplo e preencha com suas credenciais:

```bash
# No Windows (PowerShell):
copy src\main\resources\application-local.yml.example src\main\resources\application-local.yml
# Edite o arquivo com suas credenciais do PostgreSQL
```

### 3. Executar a Aplicação

```bash
./mvnw spring-boot:run
```

### 4. Acessar o Swagger UI

Após iniciar, acesse a documentação interativa da API:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🗺️ Endpoints Disponíveis (Fase 1)

### Missões (`/missoes`)

| Método | Endpoint | Role Mínima | Descrição |
|--------|----------|-------------|-----------|
| `POST` | `/missoes` | `ROLE_REPRESENTANTE` | Criar nova missão |
| `GET` | `/missoes` | `ROLE_SOLDADO` | Listar missões (com filtros) |
| `GET` | `/missoes/{id}` | `ROLE_SOLDADO` | Buscar missão por ID |
| `PUT` | `/missoes/{id}` | `ROLE_REPRESENTANTE` | Atualizar missão |
| `DELETE`| `/missoes/{id}` | `ROLE_PRIMARCA` | Cancelar/remover missão |

---

## 🧬 Modelo de Domínio

### Hierarquia de Patentes (do menor para o maior)

```
SOLDADO → CABO → SARGENTO → 2º TENENTE → 1º TENENTE
→ CAPITÃO → LÍDER DE CAPÍTULO → COMANDANTE DE TROPAS
→ REPRESENTANTE DO CAPÍTULO  ← responde ao PRIMARCA
                                    ↑
                              ROLE_PRIMARCA (acesso irrestrito)
```

### Classificação de Sigilo de Missões

| Nível | Nome | Acesso Mínimo |
|-------|------|---------------|
| 1 | VERDE | Qualquer soldado autenticado |
| 2 | AMARELO | Oficiais (Sargento+) |
| 3 | VERMELHO | Alto Comando (Capitão+) |
| 4 | NEGRO | Exclusivo do Primarca |

---

## 📁 Estrutura de Pacotes

```
src/main/java/dev/JavaWarhammer/CadastroSoldadosWarhammer/
├── domain/
│   ├── enums/          ← Enums de domínio (Patente, NivelSigilo, etc.)
│   ├── vo/             ← Value Objects (@Embeddable — ex: SinalSOS)
│   └── exception/      ← Exceções customizadas de domínio
├── application/
│   └── usecase/        ← Interfaces e implementações de Casos de Uso
├── infrastructure/
│   ├── persistence/    ← @Entity JPA + Repositories
│   ├── security/       ← Config JWT + Spring Security (Fase 2)
│   └── config/         ← OpenAPI, Beans globais
└── adapter/
    └── in/web/         ← @RestController + DTOs (record)
```

---

## 🔒 Segurança (Fase 2 — Em Breve)

O sistema usará **RBAC** (Role-Based Access Control) com JWT stateless.

| Role | Descrição |
|------|-----------|
| `ROLE_PRIMARCA` | **Acesso irrestrito.** Cria/dissolve Capítulos, destitui Representantes |
| `ROLE_REPRESENTANTE` | Gerencia membros e missões do próprio Capítulo |
| `ROLE_OFICIAL` | Leitura e operações táticas básicas |
| `ROLE_SOLDADO` | Consultas básicas |

---

## 📄 Changelog

### [Fase 1] — 2026-06-19

#### ✅ Adicionado
- Enums de domínio: `NivelSigilo`, `TipoInimigo`, `TipoTerreno`, `StatusCivis`, `StatusMissao`
- Value Object `SinalSOS` (`@Embeddable`) com factory methods
- Entidade `MissaoEntity` com todos os atributos táticos
- Repositório `MissaoRepository` com queries por filtro
- Casos de Uso: `CriarMissaoUseCase`, `BuscarMissaoUseCase` (interfaces)
- Controller `MissaoController` com documentação Swagger completa
- DTOs como Java `record`: `CriarMissaoRequest`, `MissaoResponse`
- Configuração `OpenApiConfig` com autenticação Bearer JWT
- Dependências: PostgreSQL, Flyway, springdoc-openapi, validation, Spring Security
- Migrations Flyway: `V1__create_initial_schema.sql`, `V2__create_tb_missoes.sql`

#### 🗑️ Removido
- `Missoes.java` (substituída pela `MissaoEntity` expandida)
- `MissoesService.java`, `MissoesController.java`, `MissoesRepository.java`

#### 🔧 Corrigido
- `@Table(name = "Capitulos_table ")` → `"tb_capitulos"` (bug de espaço)
- `StatusMissao` movido para arquivo próprio em `domain/enums/`
- Import morto `java.nio.channels.FileChannel` removido
- `@Repository` redundante removido do `MissoesRepository`
- H2 movido para escopo `test`

---

## 🤝 Contribuindo

Este projeto segue o protocolo de **Code Review Contínuo**:

> Nenhuma fase avança sem aprovação do Code Review da fase anterior.

---

*For the Emperor. For the Code.* ⚔️
