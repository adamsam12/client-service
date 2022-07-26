package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.Client;
import com.nttdata.bootcamp.repository.ClientRepository;
import com.nttdata.bootcamp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Flux<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> getByIdClient(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Mono<Client> createClient(Client client) {
        String typeClient = client.getTypeClient();
        switch (typeClient){
            case "Personal":
                client.setTypeProfile("VIP");
                break;
            case "Empresa":
                client.setTypeProfile("PYME");
                break;
        }
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> updateClient(String id, Client client) {
        String typeClient = client.getTypeClient();
        switch (typeClient){
            case "Personal":
                client.setTypeProfile("VIP");
                break;
            case "Empresa":
                client.setTypeProfile("PYME");
                break;
        }
        return clientRepository.findById(id).flatMap(newClient -> {
            newClient.setFullName(client.getFullName());
            newClient.setNumberIdentity(client.getNumberIdentity());
            newClient.setTypeClient(client.getTypeClient());
            newClient.setTypeProfile(client.getTypeProfile());
            newClient.setPhoneNumber(client.getPhoneNumber());
            newClient.setEmailClient(client.getEmailClient());
            return clientRepository.save(newClient);
        }).switchIfEmpty(Mono.empty());
    }

    public Mono<Void> deleteClient(String id) {
        return clientRepository.findById(id).flatMap(client -> clientRepository.deleteById(client.getId()));
    }
}
