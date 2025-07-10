package br.com.frederico.vr.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CartaoJaExisteException extends RuntimeException {
    public CartaoJaExisteException(String numeroCartao) {
        super("Cartão com número " + numeroCartao + " já existe.");
    }
}