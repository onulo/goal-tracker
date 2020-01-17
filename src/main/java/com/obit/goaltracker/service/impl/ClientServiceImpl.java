package com.obit.goaltracker.service.impl;

import com.obit.goaltracker.entity.Client;
import com.obit.goaltracker.jpa.ClientRepository;
import com.obit.goaltracker.service.ClientService;
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
        return clientRepository.findById(clientId).orElseGet(() -> {
            log.info("Client not found. Creating new client");
            return clientRepository.save(new Client(clientId));
        });
    }
}
