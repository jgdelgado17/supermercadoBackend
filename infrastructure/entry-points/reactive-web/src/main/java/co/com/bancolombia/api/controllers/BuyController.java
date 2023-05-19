package co.com.bancolombia.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.com.bancolombia.model.nested.Nested;
import co.com.bancolombia.r2dbc.helper.CustomException;
import co.com.bancolombia.usecase.buy.BuyUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/buys")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class BuyController {
    private final BuyUseCase buyUseCase;

    @PostMapping
    public Mono<Nested> createBuy(@RequestBody Nested nested) {
        return buyUseCase.createNested(nested)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }

    @GetMapping("/{id}")
    public Mono<Nested> findByIdProduct(@PathVariable("id") Integer id) {
        return buyUseCase.findByIdNested(id)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public Flux<Nested> findAll() {
        return buyUseCase.findAllNested()
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));
    }

    @ExceptionHandler(CustomException.class)
    public Mono<ResponseEntity<String>> handleCustomException(CustomException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String body = "Error: " + ex.getMessage();
        return Mono.just(ResponseEntity.status(status).body(body))
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())));
    }
}
