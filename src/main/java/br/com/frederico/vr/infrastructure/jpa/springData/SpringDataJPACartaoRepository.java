package br.com.frederico.vr.infrastructure.jpa.springData;

import br.com.frederico.vr.domain.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataJPACartaoRepository extends
        PagingAndSortingRepository<Cartao, UUID>,
        JpaSpecificationExecutor<Cartao>,
        JpaRepository<Cartao, UUID> {

    Optional<Cartao> findByNumero(String numero);

    @Modifying
    @Query("""
    UPDATE Cartao c
    SET c.saldo = 
        CASE 
            WHEN :valor >= 0 THEN c.saldo + :valor
            ELSE c.saldo + :valor
        END
    WHERE c.id = :id AND (c.saldo + :valor) >= 0
    """)
    int realizarTransacao(@Param("id") UUID id, @Param("valor") BigDecimal valor);

}
