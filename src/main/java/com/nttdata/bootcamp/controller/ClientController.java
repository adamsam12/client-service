package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.model.Client;
import com.nttdata.bootcamp.service.ClientService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService service;

    private static final String CLIENT = "client";

    @GetMapping(value = "/getAllClient")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Client> getAllClient() {
        System.out.println("Listar todos los clientes");
        return service.getAllClient();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Client> getByIdClient(@PathVariable String id) {
        System.out.println("Buscar cliente por su Id");
        return service.getByIdClient(id);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = CLIENT, fallbackMethod = "client")
    public Mono<Client> createCustomer(@RequestBody Client client) {
        System.out.println("Cliente creado con Éxito");
        return service.createClient(client);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = CLIENT, fallbackMethod = "client")
    public Mono<Client> updateCustomer(@PathVariable String id, @RequestBody Client client) {
        System.out.println("Cliente actualizado con Éxito");
        return service.updateClient(id, client);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteClient(@PathVariable String id) {
        System.out.println("Cliente Eliminado con Éxito");
        return service.deleteClient(id);
    }
}
