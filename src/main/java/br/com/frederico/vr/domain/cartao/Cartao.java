package br.com.frederico.vr.domain.cartao;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cartao")
public class Cartao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @EqualsAndHashCode.Include
    @Column(length = 16, nullable = false, unique = true)
    @Size(min = 16, max = 16, message = "O número do cartão deve ter 16 caracteres")
    @Pattern(regexp = "\\d+", message = "O número do cartão deve conter apenas números. ")
    private String numero;

    @NotNull
    @Digits(integer = 5, fraction = 2, message = "O valor deve ter no máximo 5 digitos inteiros e 2 decimais.")
    private BigDecimal saldo;

    @Pattern(regexp = "\\d{4}$", message = "A senha deve conter exatamente 4 dígitos e numéricos.")
    @Column(length = 4, nullable = false)
    private String senha;

}
