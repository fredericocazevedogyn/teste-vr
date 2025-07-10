package br.com.frederico.vr.application.mapper;

import br.com.frederico.vr.application.command.IncluirCartaoCommand;
import br.com.frederico.vr.application.representation.CartaoRepresentation;
import br.com.frederico.vr.application.representation.CartaoSaldoRepresentation;
import br.com.frederico.vr.domain.cartao.Cartao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartaoMapper {

    @Mapping(target = "numero", source = "numeroCartao")
    @Mapping(target = "saldo", constant = "500.00")
    Cartao toEntity(IncluirCartaoCommand command);

    @Mapping(target = "numero", source = "numero")
    CartaoRepresentation toRepresentation(Cartao cartao);

    CartaoSaldoRepresentation toSaldoRepresentation(Cartao cartao);

}
