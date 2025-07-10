package br.com.frederico.vr;

import br.com.frederico.vr.application.command.IncluirCartaoCommand;
import br.com.frederico.vr.application.mapper.CartaoMapper;
import br.com.frederico.vr.application.representation.CartaoRepresentation;
import br.com.frederico.vr.application.representation.CartaoSaldoRepresentation;
import br.com.frederico.vr.application.service.CartaoService;
import br.com.frederico.vr.domain.cartao.Cartao;
import br.com.frederico.vr.domain.cartao.repository.CartaoRepository;
import br.com.frederico.vr.infrastructure.exceptions.CartaoJaExisteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private CartaoMapper cartaoMapper;

    @InjectMocks
    private CartaoService cartaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarCartaoComSucesso() {
        IncluirCartaoCommand command = new IncluirCartaoCommand();
        command.setNumeroCartao("1234567890123456");
        command.setSenha("1234");
        Cartao cartao = new Cartao("1234567890123456", "1234", BigDecimal.valueOf(500));
        CartaoRepresentation representation = new CartaoRepresentation();
        representation.setNumero("1234567890123456");
        representation.setSenha("1234");

        when(cartaoRepository.findByNumero(command.getNumeroCartao())).thenReturn(Optional.empty());
        when(cartaoMapper.toEntity(command)).thenReturn(cartao);
        when(cartaoMapper.toRepresentation(cartao)).thenReturn(representation);

        CartaoRepresentation result = cartaoService.criarCartao(command);

        assertThat(result).isNotNull();
        assertThat(result.getNumero()).isEqualTo("1234567890123456");

        verify(cartaoRepository).add(cartao);
    }

    @Test
    void deveLancarExcecaoQuandoCartaoJaExiste() {
        IncluirCartaoCommand command = new IncluirCartaoCommand();
        command.setNumeroCartao("1234567890123456");
        command.setSenha("1234");
        Cartao cartaoExistente = new Cartao("1234567890123456", "1234", BigDecimal.valueOf(500));

        when(cartaoRepository.findByNumero(command.getNumeroCartao())).thenReturn(Optional.of(cartaoExistente));

        assertThatThrownBy(() -> cartaoService.criarCartao(command))
                .isInstanceOf(CartaoJaExisteException.class)
                .hasMessageContaining("1234567890123456");

        verify(cartaoRepository, never()).add(any());
    }

    @Test
    void deveConsultarSaldoDoCartaoComSucesso() {
        Cartao cartao = new Cartao("1234567890123456", "1234", BigDecimal.valueOf(500));
        CartaoSaldoRepresentation saldo = new CartaoSaldoRepresentation();
        saldo.setSaldo(BigDecimal.valueOf(500));

        when(cartaoRepository.findByNumero("1234567890123456")).thenReturn(Optional.of(cartao));
        when(cartaoMapper.toSaldoRepresentation(cartao)).thenReturn(saldo);

        CartaoSaldoRepresentation result = cartaoService.consultarSaldoCartao("1234567890123456");

        assertThat(result).isNotNull();
        assertThat(result.getSaldo()).isEqualTo(BigDecimal.valueOf(500));
    }

    @Test
    void deveLancarExcecaoQuandoCartaoNaoExiste() {
        String numeroCartao = "1234567890123456";

        when(cartaoRepository.findByNumero(numeroCartao)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cartaoService.consultarSaldoCartao(numeroCartao))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Cartão não encontrado");
    }
}

