package com.axelynicky.restorant_service.Repository;

import com.axelynicky.restorant_service.Domain.Waiter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WaiterRepository  extends JpaRepository<Waiter, Long> {
}
