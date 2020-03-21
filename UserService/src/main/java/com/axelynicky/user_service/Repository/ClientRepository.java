package com.axelynicky.user_service.Repository;

import com.axelynicky.user_service.Domain.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByNameAndPassword(String name, String pass);

    @Query(value = "SELECT * FROM insert_new_user(:#{#client.name}, :#{#client.password}, :#{#client.mail}, :#{#client.lastName})", nativeQuery = true)
    Client registerNewClient(@Param("client") Client client);
}
