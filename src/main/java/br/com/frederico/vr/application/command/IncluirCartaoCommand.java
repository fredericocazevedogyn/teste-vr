package br.com.frederico.vr.application.command;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class IncluirCartaoCommand {

    private UUID id;
    private String numeroCartao;
    private String senha;

}
