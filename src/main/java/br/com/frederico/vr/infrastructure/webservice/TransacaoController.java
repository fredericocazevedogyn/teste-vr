package br.com.frederico.vr.infrastructure.webservice;

import br.com.frederico.vr.application.command.IncluirCartaoCommand;
import br.com.frederico.vr.application.command.IncluirTransacaoCommand;
import br.com.frederico.vr.application.representation.CartaoRepresentation;
import br.com.frederico.vr.application.representation.CartaoSaldoRepresentation;
import br.com.frederico.vr.application.service.CartaoService;
import br.com.frederico.vr.application.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
@Tag(name = "Transação", description = "Transações com os cartões")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Operation(summary = "Realizar uma transação",
            description = "Realiza uma transação com o numero do cartão, senha e o valor, retornando HTTP 201 quando criado com sucesso!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Cartão não encontrado, senha incorreta e/ou saldo insuficiente", content = @Content),
    })
    @PostMapping
    public ResponseEntity<Void> realizarTransacao(@Valid @RequestBody IncluirTransacaoCommand command) {
        this.transacaoService.realizarTransacao(command);
        return ResponseEntity.created(URI.create("OK")).build();
    }

}
