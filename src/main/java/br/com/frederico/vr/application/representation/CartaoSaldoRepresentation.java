package br.com.frederico.vr.application.representation;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CartaoSaldoRepresentation {

    private UUID id;
    private String numero;
    private String saldo;

}
