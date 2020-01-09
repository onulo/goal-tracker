package com.obit.goaltracker.service.impl;

import com.obit.goaltracker.entity.Client;
import com.obit.goaltracker.jpa.ClientRepository;
import com.obit.goaltracker.service.ClientService;
import java.util.ArrayList;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getOrCreateClient(String clientId) {
        log.info("Getting client with clientId: {}", clientId);
        final Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            log.info("Returning found client: {}", client.get().getClientId());
            return client.get();
        }
        log.info("Client not found. Created new client");
        return clientRepository.save(new Client(clientId, new ArrayList<>()));
    }
}
