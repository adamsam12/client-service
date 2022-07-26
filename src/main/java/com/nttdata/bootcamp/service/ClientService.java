package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> getAllClient();
    Mono<Client> getByIdClient(String id);
    Mono<Client> createClient(Client customer);
    Mono<Client> updateClient(String id, Client customer);
    Mono<Void> deleteClient(String id);

}
