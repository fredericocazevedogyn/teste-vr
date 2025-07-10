package br.com.frederico.vr.infrastructure.webservice;

import br.com.frederico.vr.application.command.IncluirCartaoCommand;
import br.com.frederico.vr.application.command.IncluirTransacaoCommand;
import br.com.frederico.vr.application.representation.CartaoRepresentation;
import br.com.frederico.vr.application.representation.CartaoSaldoRepresentation;
import br.com.frederico.vr.application.service.CartaoService;
import br.com.frederico.vr.application.service.TransacaoService;
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

    @PostMapping
    public ResponseEntity<Void> realizarTransacao(@Valid @RequestBody IncluirTransacaoCommand command) {
        this.transacaoService.realizarTransacao(command);
        return ResponseEntity.created(URI.create("OK")).build();
    }

}
