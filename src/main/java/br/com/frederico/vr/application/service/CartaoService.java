package br.com.frederico.vr.application.service;

import br.com.frederico.vr.application.command.IncluirCartaoCommand;
import br.com.frederico.vr.application.mapper.CartaoMapper;
import br.com.frederico.vr.application.representation.CartaoRepresentation;
import br.com.frederico.vr.application.representation.CartaoSaldoRepresentation;
import br.com.frederico.vr.domain.cartao.Cartao;
import br.com.frederico.vr.domain.cartao.repository.CartaoRepository;
import br.com.frederico.vr.infrastructure.exceptions.CartaoJaExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final CartaoMapper cartaoMapper;

    @Transactional
    public CartaoRepresentation criarCartao(IncluirCartaoCommand command) {
        cartaoRepository.findByNumero(command.getNumeroCartao())
                .ifPresent(c -> { throw new CartaoJaExisteException(command.getNumeroCartao()); });

        Cartao cartao = cartaoMapper.toEntity(command);
        cartaoRepository.add(cartao);

        return cartaoMapper.toRepresentation(cartao);
    }

    @Transactional(readOnly = true)
    public CartaoSaldoRepresentation consultarSaldoCartao(String numeroCartao) {
        Cartao cartao = cartaoRepository.findByNumero(numeroCartao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
        return cartaoMapper.toSaldoRepresentation(cartao);
    }
}
