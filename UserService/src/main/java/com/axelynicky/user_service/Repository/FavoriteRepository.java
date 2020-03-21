package com.axelynicky.user_service.Repository;

import com.axelynicky.user_service.Domain.Favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<List<Favorite>> findAllById(Long id);

    Optional<List<Favorite>> findAllByClientId(Long clientId);

}
