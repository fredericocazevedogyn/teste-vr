package br.com.frederico.vr.infrastructure.jpa;

import br.com.frederico.vr.domain.cartao.Cartao;
import br.com.frederico.vr.domain.cartao.repository.CartaoRepository;
import br.com.frederico.vr.infrastructure.jpa.springData.SpringDataJPACartaoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JPACartaoRepository implements CartaoRepository {

    private final SpringDataJPACartaoRepository springRepository;

    public JPACartaoRepository(SpringDataJPACartaoRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public void add(Cartao obj) {
        this.springRepository.save(obj);
    }

    @Override
    public void remove(Cartao obj) {
        this.springRepository.delete(obj);
    }

    @Override
    public Cartao get(UUID id) {
        return this.springRepository.findById(id).orElse(null);
    }

    @Override
    public boolean exists(UUID id) {
        return this.springRepository.existsById(id);
    }

    @Override
    public List<Cartao> all() {
        return this.springRepository.findAll();
    }

    @Override
    public Optional<Cartao> findByNumero(String numero) {
        return this.springRepository.findByNumero(numero);
    }

    @Override
    public int realizarTransacao(UUID idCartao, BigDecimal valor) {
        return this.springRepository.realizarTransacao(idCartao, valor);
    }
}
