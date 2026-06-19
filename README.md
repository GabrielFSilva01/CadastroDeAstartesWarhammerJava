# ⚔️ Cogitator Imperialis

> *"Conhecimento é poder. Proteja-o bem."* — Adeptus Mechanicus

Sistema tático de simulação e gerenciamento de missões no universo **Warhammer 40.000**, desenvolvido com Spring Boot, Clean Architecture e PostgreSQL.

---

## 📋 Status do Projeto

| Fase | Descrição | Status |
|------|-----------|--------|
| **Fase 1** | Fundação: Domínio de Missões, OpenAPI, Migração de BD | ✅ Concluída |
| **Fase 1.5** | Infraestrutura: Docker, Perfis de Ambiente, Segurança de Credenciais | ✅ Concluída |
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
| **PostgreSQL 16-alpine** | Docker | Banco de dados containerizado |
| **Docker Compose** | Latest | Orquestração do ambiente de dev |
| Flyway | (Boot managed) | Migrations de banco versionadas |
| springdoc-openapi | 2.8.9 | Documentação e teste da API |
| Lombok | (Boot managed) | Redução de boilerplate |
| JWT (fase 2) | A definir | Tokens de autenticação stateless |

---

## 🚀 Como Executar

### Pré-requisitos

- Java 21+
- Maven 3.9+
- **Docker Desktop** (PostgreSQL roda em container — não instale localmente)

### 1. Configurar as Credenciais

```powershell
# Copia o template de variáveis de ambiente
copy .env.example .env

# Edite o .env com suas credenciais (nunca commite este arquivo!)
notepad .env
```

### 2. Subir o Banco de Dados (Docker)

```powershell
# Sobe o PostgreSQL em background
docker compose up -d

# Verifica se o container está saudável
docker compose ps

# Acompanha os logs do banco (opcional)
docker compose logs -f postgres
```

> O banco estará disponível em `localhost:5433` (porta não-padrão para evitar conflitos).

### 3. Executar a Aplicação com o Perfil de Dev

```powershell
# O perfil é lido automaticamente do .env (SPRING_PROFILES_ACTIVE=Primarca_Ferreira)
.\mvnw spring-boot:run
```

Ou explicitamente:

```powershell
.\mvnw spring-boot:run "-Dspring-boot.run.profiles=Primarca_Ferreira"
```

### 4. Acessar o Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

### 5. Parar o ambiente

```powershell
# Para o container (dados preservados no volume)
docker compose down

# Para E APAGA todos os dados (resetar o banco)
docker compose down -v
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

## 🐳 Infraestrutura Docker

O PostgreSQL roda **exclusivamente em container**, nunca diretamente no host.

```
┌─────────────────────────────────────────────────────┐
│  Host (Windows)                                     │
│                                                     │
│  Spring Boot :8080 ──► localhost:5433               │
│                               │                     │
│         ┌─────────────────────▼─────────────────┐  │
│         │  Docker Network: cogitator_net         │  │
│         │                                       │  │
│         │  PostgreSQL 16-alpine :5432           │  │
│         │  Volume: cogitator_pgdata (persistido) │  │
│         └───────────────────────────────────────┘  │
└─────────────────────────────────────────────────────┘
```

### Perfis de Ambiente

| Perfil | Arquivo | Uso |
|--------|---------|-----|
| `Primarca_Ferreira` | `application-Primarca_Ferreira.yml` | Dev local do Ferreira — Docker na porta 5433 |
| `prod` *(futuro)* | `application-prod.yml` | Produção — credenciais via secrets do servidor |
| `test` *(futuro)* | `application-test.yml` | Testes CI — H2 em memória |

### Segurança de Credenciais

Nenhuma senha existe em código ou no repositório:

```
.env.example  → ✅ commitado (template sem credenciais reais)
.env          → ❌ ignorado pelo Git (credenciais reais ficam aqui)
application-Primarca_Ferreira.yml → ✅ commitado (lê vars de ${ENV_VAR})
```

---

## 🔒 Segurança RBAC (Fase 2 — Em Breve)

O sistema usará **RBAC** (Role-Based Access Control) com JWT stateless.

| Role | Descrição |
|------|-----------|
| `ROLE_PRIMARCA` | **Acesso irrestrito.** Cria/dissolve Capítulos, destitui Representantes |
| `ROLE_REPRESENTANTE` | Gerencia membros e missões do próprio Capítulo |
| `ROLE_OFICIAL` | Leitura e operações táticas básicas |
| `ROLE_SOLDADO` | Consultas básicas |

---

## 📄 Changelog

### [Fase 1.5 — Docker, Perfis e Segurança de Infraestrutura] — 2026-06-19

#### ✅ Adicionado
- `docker-compose.yml` — PostgreSQL 16-alpine containerizado com healthcheck, rede isolada e limite de recursos
- `.env.example` — template de variáveis de ambiente (seguro para commitar)
- `docker/init/01_create_extensions.sql` — extensões PostgreSQL pré-Flyway (`pgcrypto`, `unaccent`)
- `application-Primarca_Ferreira.yml` — perfil de dev local com HikariCP configurado e datasource no Docker
- `application.yml` refatorado como base limpa (sem credenciais, sem config de ambiente)

#### 🔧 Corrigido (CR-21 a CR-24)
- CR-21: Credenciais com default em texto plano removidas do `application.yml` base
- CR-22: Separação de perfis implementada (`application-{profile}.yml`)
- CR-23: `.gitignore` atualizado para proteger `.env`, `application-local*` e dados do Docker
- CR-24: `show-sql: true` movido exclusivamente para o perfil de dev

#### 🗑️ Removido
- `.env` da raiz do projeto (listado no `.gitignore` — nunca vai ao repositório)

### [Fase 1 — Poda e Use Cases] — 2026-06-19

#### ✅ Adicionado
- `CriarMissaoUseCaseImpl` — implementação do use case de criação de missão
- `BuscarMissaoUseCaseImpl` — implementação com paginação e filtros JPQL
- `V3__remove_dead_columns_from_capitulos.sql` — migration que remove contadores corruptos do schema

#### 🗑️ Removido (Dead Code — Code Review Pós-Commit)
- `controller/CapituloController.java` e `SoldadoController.java` (CRUD sem DTO, sem Use Case)
- `service/CapituloService.java` e `SoldadoService.java` (serviços anêmicos sem abstração)
- `repository/CapituloRepository.java` e `SoldadoRepository.java` (queries sobre dados corruptos)
- `model/Acessorio.java` (entidade sem use case, sem controller — dead code completo)

#### 🔧 Refatorado
- `model/Capitulo.java` — campos `quantidadeMissoes` e `numeroSoldados` removidos (dados deriváveis)
- `infrastructure/persistence/entity/MissaoEntity.java` — construtor `protected` → `public`

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
