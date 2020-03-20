package com.axelynicky.menu_service.Repository;

import com.axelynicky.menu_service.Domain.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<List<Menu>> findAllByRestorantId(Long restorantId);
}
