package co.com.bancolombia.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.com.bancolombia.model.nested.Nested;
import co.com.bancolombia.r2dbc.helper.CustomException;
import co.com.bancolombia.usecase.nested.NestedUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/buys")
@RequiredArgsConstructor
public class BuyController {
    private final NestedUseCase nestedUseCase;

    @PostMapping
    public Mono<Nested> createProduct(@RequestBody Nested nested) {
        return nestedUseCase.createNested(nested)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }

    @ExceptionHandler(CustomException.class)
    public Mono<ResponseEntity<String>> handleCustomException(CustomException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String body = "Error: " + ex.getMessage();
        return Mono.just(ResponseEntity.status(status).body(body))
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())));
    }
}
