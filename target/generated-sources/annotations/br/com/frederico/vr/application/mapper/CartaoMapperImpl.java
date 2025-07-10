package br.com.frederico.vr.application.mapper;

import br.com.frederico.vr.application.command.IncluirCartaoCommand;
import br.com.frederico.vr.application.representation.CartaoRepresentation;
import br.com.frederico.vr.application.representation.CartaoSaldoRepresentation;
import br.com.frederico.vr.domain.cartao.Cartao;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-09T22:52:20-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class CartaoMapperImpl implements CartaoMapper {

    @Override
    public Cartao toEntity(IncluirCartaoCommand command) {
        if ( command == null ) {
            return null;
        }

        Cartao cartao = new Cartao();

        cartao.setNumero( command.getNumeroCartao() );
        cartao.setId( command.getId() );
        cartao.setSenha( command.getSenha() );

        cartao.setSaldo( new BigDecimal( "500.00" ) );

        return cartao;
    }

    @Override
    public CartaoRepresentation toRepresentation(Cartao cartao) {
        if ( cartao == null ) {
            return null;
        }

        CartaoRepresentation.CartaoRepresentationBuilder cartaoRepresentation = CartaoRepresentation.builder();

        cartaoRepresentation.numero( cartao.getNumero() );
        cartaoRepresentation.id( cartao.getId() );
        cartaoRepresentation.senha( cartao.getSenha() );

        return cartaoRepresentation.build();
    }

    @Override
    public CartaoSaldoRepresentation toSaldoRepresentation(Cartao cartao) {
        if ( cartao == null ) {
            return null;
        }

        CartaoSaldoRepresentation.CartaoSaldoRepresentationBuilder cartaoSaldoRepresentation = CartaoSaldoRepresentation.builder();

        cartaoSaldoRepresentation.id( cartao.getId() );
        cartaoSaldoRepresentation.numero( cartao.getNumero() );
        if ( cartao.getSaldo() != null ) {
            cartaoSaldoRepresentation.saldo( cartao.getSaldo().toString() );
        }

        return cartaoSaldoRepresentation.build();
    }
}
