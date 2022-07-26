package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClientRepository extends ReactiveCrudRepository<Client, String> {
}
