package com.axelynicky.user_service.Repository;

import com.axelynicky.user_service.Domain.Client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByNameAndPassword(String name, String pass);
}
