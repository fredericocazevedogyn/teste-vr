package br.com.frederico.vr;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.frederico.vr.application.command.IncluirTransacaoCommand;
import br.com.frederico.vr.application.mapper.CartaoMapper;
import br.com.frederico.vr.application.service.CartaoService;
import br.com.frederico.vr.application.service.TransacaoService;
import br.com.frederico.vr.domain.cartao.Cartao;
import br.com.frederico.vr.domain.cartao.repository.CartaoRepository;
import br.com.frederico.vr.infrastructure.exceptions.CartaoNaoExisteException;
import br.com.frederico.vr.infrastructure.exceptions.SaldoInsuficienteException;
import br.com.frederico.vr.infrastructure.exceptions.SenhaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTeste {

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private CartaoMapper cartaoMapper;

    @InjectMocks
    private TransacaoService transacaoService;

    private Cartao cartao;
    private final String numeroCartao = "1234567890123456";
    private final String senha = "1234";
    private final BigDecimal saldo = new BigDecimal("500.00");

    @BeforeEach
    void setUp() {
        cartao = new Cartao();
        cartao.setNumero(numeroCartao);
        cartao.setSenha(senha);
        cartao.setSaldo(saldo);
    }

    @Test
    void deveRealizarTransacaoComSucesso() {
        IncluirTransacaoCommand command = new IncluirTransacaoCommand(numeroCartao, senha, new BigDecimal("100.00"));

        when(cartaoRepository.findByNumero(numeroCartao)).thenReturn(Optional.of(cartao));
        when(cartaoRepository.realizarTransacao(cartao.getId(), command.getValor())).thenReturn(1);

        assertDoesNotThrow(() -> transacaoService.realizarTransacao(command));
    }

    @Test
    void deveLancarExcecaoQuandoCartaoNaoExiste() {
        IncluirTransacaoCommand command = new IncluirTransacaoCommand(numeroCartao, senha, new BigDecimal("100.00"));

        when(cartaoRepository.findByNumero(numeroCartao)).thenReturn(Optional.empty());

        assertThrows(CartaoNaoExisteException.class, () -> transacaoService.realizarTransacao(command));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaIncorreta() {
        IncluirTransacaoCommand command = new IncluirTransacaoCommand(numeroCartao, "senhaErrada", new BigDecimal("100.00"));

        when(cartaoRepository.findByNumero(numeroCartao)).thenReturn(Optional.of(cartao));

        assertThrows(SenhaInvalidaException.class, () -> transacaoService.realizarTransacao(command));
    }

    @Test
    void deveLancarExcecaoQuandoSaldoInsuficiente() {
        IncluirTransacaoCommand command = new IncluirTransacaoCommand(numeroCartao, senha, new BigDecimal("600.00"));

        when(cartaoRepository.findByNumero(numeroCartao)).thenReturn(Optional.of(cartao));
        when(cartaoRepository.realizarTransacao(cartao.getId(), command.getValor())).thenReturn(0);

        assertThrows(SaldoInsuficienteException.class, () -> transacaoService.realizarTransacao(command));
    }
}
