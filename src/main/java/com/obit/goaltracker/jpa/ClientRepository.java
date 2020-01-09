package com.obit.goaltracker.jpa;

import com.obit.goaltracker.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, String> {
}
