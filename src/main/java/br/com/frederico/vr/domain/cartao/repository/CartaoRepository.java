package br.com.frederico.vr.domain.cartao.repository;

import br.com.frederico.vr.domain.Repository;
import br.com.frederico.vr.domain.cartao.Cartao;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface CartaoRepository extends Repository<Cartao, UUID> {

    Optional<Cartao> findByNumero(String numero);

    int realizarTransacao(UUID idCartao, BigDecimal valor);

}
