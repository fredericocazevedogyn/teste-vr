package br.com.frederico.vr.application.command;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class IncluirTransacaoCommand {

    @NotBlank
    @Size(min = 16, max = 16, message = "O número do cartão deve ter 16 caracteres")
    @Pattern(regexp = "\\d+", message = "O número do cartão deve conter apenas números. ")
    private String numeroCartao;

    @NotBlank
    @Pattern(regexp = "\\d{4}$", message = "A senha deve conter exatamente 4 dígitos e numéricos.")
    @Column(length = 4, nullable = false)
    private String senhaCartao;

    @NotNull
    @Digits(integer = 5, fraction = 2, message = "O valor deve ter no máximo 5 digitos inteiros e 2 decimais.")
    private BigDecimal valor;

}
