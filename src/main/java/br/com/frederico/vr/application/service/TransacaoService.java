package br.com.frederico.vr.application.service;

import br.com.frederico.vr.application.command.IncluirCartaoCommand;
import br.com.frederico.vr.application.command.IncluirTransacaoCommand;
import br.com.frederico.vr.application.mapper.CartaoMapper;
import br.com.frederico.vr.application.representation.CartaoRepresentation;
import br.com.frederico.vr.application.representation.CartaoSaldoRepresentation;
import br.com.frederico.vr.domain.cartao.Cartao;
import br.com.frederico.vr.domain.cartao.repository.CartaoRepository;
import br.com.frederico.vr.infrastructure.exceptions.CartaoJaExisteException;
import br.com.frederico.vr.infrastructure.exceptions.CartaoNaoExisteException;
import br.com.frederico.vr.infrastructure.exceptions.SaldoInsuficienteException;
import br.com.frederico.vr.infrastructure.exceptions.SenhaInvalidaException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final CartaoRepository cartaoRepository;
    private final CartaoMapper cartaoMapper;

    @Transactional
    public void realizarTransacao(IncluirTransacaoCommand command) {
        Cartao cartao = cartaoRepository.findByNumero(command.getNumeroCartao())
                .orElseThrow(() -> new CartaoNaoExisteException(command.getNumeroCartao()));

        Optional.of(cartao)
                .filter(c -> c.getSenha().equals(command.getSenhaCartao()))
                .orElseThrow(SenhaInvalidaException::new);

        Optional.of(cartaoRepository.realizarTransacao(cartao.getId(), command.getValor()))
                .filter(updated -> updated > 0)
                .map(updated -> cartaoMapper.toRepresentation(cartao))
                .orElseThrow(SaldoInsuficienteException::new);
    }

}
