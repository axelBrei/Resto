package com.axelynicky.user_service.Service.Favorite;

import com.axelynicky.user_service.Domain.Favorite;
import com.axelynicky.user_service.Exceptions.NotFoundExeption;

import java.util.List;

public interface FavoriteService {

    List<Favorite> getClientFavorites(Long clientId) throws NotFoundExeption;
    void addClientFavorite(Long clientId) throws NotFoundExeption;
    void removeClientFavorite(Long clientId, Long favoriteId) throws NotFoundExeption;
}
