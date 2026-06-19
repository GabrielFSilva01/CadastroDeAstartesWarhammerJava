package dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Lançada quando uma Missão não é encontrada pelo ID fornecido.
 * Retorna HTTP 404 automaticamente via @ResponseStatus.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissaoNaoEncontradaException extends RuntimeException {

    public MissaoNaoEncontradaException(Long id) {
        super("Missão não encontrada com o ID: " + id +
              ". Os arquivos do Cogitator não registram esta operação.");
    }

    public MissaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
