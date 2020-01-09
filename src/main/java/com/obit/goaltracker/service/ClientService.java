package com.obit.goaltracker.service;

import com.obit.goaltracker.entity.Client;

public interface ClientService {

    Client getOrCreateClient(String clientId);

}
