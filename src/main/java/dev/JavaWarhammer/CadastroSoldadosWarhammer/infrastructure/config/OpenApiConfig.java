package dev.JavaWarhammer.CadastroSoldadosWarhammer.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do springdoc-openapi.
 *
 * Swagger UI disponível em: http://localhost:8080/swagger-ui/index.html
 * OpenAPI JSON em:          http://localhost:8080/v3/api-docs
 *
 * Todos os endpoints protegidos exibem o botão "Authorize" para inserir
 * o token JWT Bearer, permitindo testes diretos na UI.
 */
@Configuration
public class OpenApiConfig {

    private static final String BEARER_AUTH = "Bearer Auth";

    @Bean
    public OpenAPI cogitatorImperialisOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("⚔️ Cogitator Imperialis API")
                .description("""
                    **Sistema Tático de Gestão e Simulação — Warhammer 40.000**
                                        
                    Requer autenticação Bearer JWT para endpoints protegidos.
                    
                    **Roles disponíveis:**
                    - `ROLE_PRIMARCA` — Acesso irrestrito
                    - `ROLE_REPRESENTANTE` — Gestão do próprio Capítulo
                    - `ROLE_OFICIAL` — Operações táticas básicas
                    - `ROLE_SOLDADO` — Consultas
                    
                    Obtenha seu token em `POST /auth/login`.
                    """)
                .version("v1.0.0 — Fase 1")
                .contact(new Contact()
                    .name("Adeptus Mechanicus — Divisão de Cogitadores")
                    .email("omnissiah@imperium.terra"))
                .license(new License()
                    .name("Lex Imperialis v1.0")
                    .url("https://github.com/GabrielFSilva01/CadastroDeAstartesWarhammerJava")))
            .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH))
            .components(new Components()
                .addSecuritySchemes(BEARER_AUTH, new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .description("Token JWT obtido via `POST /auth/login`. " +
                                 "Formato: `Bearer <seu_token_aqui>`")));
    }
}
