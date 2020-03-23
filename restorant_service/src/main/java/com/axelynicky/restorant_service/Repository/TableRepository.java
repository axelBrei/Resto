package com.axelynicky.restorant_service.Repository;

import com.axelynicky.restorant_service.Domain.Table;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
}
