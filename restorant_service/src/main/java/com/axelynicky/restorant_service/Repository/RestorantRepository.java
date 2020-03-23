package com.axelynicky.restorant_service.Repository;

import com.axelynicky.restorant_service.Domain.Restorant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestorantRepository extends JpaRepository<Restorant, Long> {

//    @Query(value = "SELECT r FROM Restorant r ORDER BY r.name ASC LIMIT 10")
//    Optional<List<Restorant>> findTop10();
}
