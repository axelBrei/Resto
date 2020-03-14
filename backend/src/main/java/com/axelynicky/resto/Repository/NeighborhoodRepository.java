package com.axelynicky.resto.Repository;

import com.axelynicky.resto.Domain.Neighborhood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, String> {
}
