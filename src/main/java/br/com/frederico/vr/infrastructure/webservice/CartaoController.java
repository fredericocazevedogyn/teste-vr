package br.com.frederico.vr.infrastructure.webservice;

import br.com.frederico.vr.application.command.IncluirCartaoCommand;
import br.com.frederico.vr.application.representation.CartaoRepresentation;
import br.com.frederico.vr.application.representation.CartaoSaldoRepresentation;
import br.com.frederico.vr.application.service.CartaoService;
import br.com.frederico.vr.domain.cartao.repository.CartaoRepository;
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
import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
@Tag(name = "Cartão", description = "Operações com cartão")
public class CartaoController {

    private final CartaoService cartaoService;

    @Operation(summary = "Criar novo cartão",
               description = "Cria um cartão com número e senha, retornando HTTP 201 quando criado com sucesso!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartão criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Cartão já existe", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CartaoRepresentation> criarCartao(@Valid @RequestBody IncluirCartaoCommand command) {
        CartaoRepresentation cartaoRepresentation = cartaoService.criarCartao(command);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartaoRepresentation.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Consultar saldo do cartão",
            description = "Consulta o saldo do cartão, retornando HTTP 200 quando o numero do cartão for encontrado" +
                    "e 404 quando o cartão não for encontrado!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cartão encontrado e o saldo é"),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado", content = @Content)
    })
    @GetMapping(path = "/{numeroCartao}")
    public ResponseEntity<CartaoSaldoRepresentation> obterSaldoCartao(@PathVariable String numeroCartao) {
        return ResponseEntity.ok().body(cartaoService.consultarSaldoCartao(numeroCartao));
    }

}
