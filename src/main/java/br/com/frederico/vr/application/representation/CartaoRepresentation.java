package br.com.frederico.vr.application.representation;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CartaoRepresentation {

    private UUID id;
    private String numero;
    private String senha;

}
