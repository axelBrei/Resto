package com.axelynicky.resto.Repository;

import com.axelynicky.resto.Domain.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatesRepository extends JpaRepository<State, String> {

    Optional<State> findById(String id);
}
