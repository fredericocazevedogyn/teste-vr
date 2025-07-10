package br.com.frederico.vr.infrastructure.webservice;

import br.com.frederico.vr.application.command.IncluirCartaoCommand;
import br.com.frederico.vr.application.representation.CartaoRepresentation;
import br.com.frederico.vr.application.representation.CartaoSaldoRepresentation;
import br.com.frederico.vr.application.service.CartaoService;
import br.com.frederico.vr.domain.cartao.repository.CartaoRepository;
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

    @PostMapping
    public ResponseEntity<CartaoRepresentation> criarCartao(@Valid @RequestBody IncluirCartaoCommand command) {
        CartaoRepresentation cartaoRepresentation = cartaoService.criarCartao(command);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartaoRepresentation.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/{numeroCartao}")
    public ResponseEntity<CartaoSaldoRepresentation> obterSaldoCartao(@PathVariable String numeroCartao) {
        return ResponseEntity.ok().body(cartaoService.consultarSaldoCartao(numeroCartao));
    }

}
