package com.axelynicky.menu_service.Repository;

import com.axelynicky.menu_service.Domain.Eatable;
import com.axelynicky.menu_service.Domain.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EatableRepository extends JpaRepository<Eatable, Long> {
    Optional<List<Eatable>> findAllById(Long id);

    Optional<List<Eatable>> findAllByMenuId(Long id);

    Optional<Eatable> findByIdAndMenuId(Long id, Long menuId);

    void deleteByIdAndMenuId(Long id, Long menuId);
}
