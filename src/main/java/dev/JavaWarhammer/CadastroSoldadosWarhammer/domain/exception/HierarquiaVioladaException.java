package dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Lançada quando uma operação viola a hierarquia de patentes ou
 * o protocolo de cadeia de comando do Capítulo.
 *
 * Retorna HTTP 422 (Unprocessable Entity) — a requisição é válida
 * sintaticamente, mas viola uma regra de negócio do domínio.
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class HierarquiaVioladaException extends RuntimeException {

    public HierarquiaVioladaException(String mensagem) {
        super(mensagem);
    }
}
