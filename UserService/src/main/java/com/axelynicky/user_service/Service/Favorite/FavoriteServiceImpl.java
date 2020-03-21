package com.axelynicky.user_service.Service.Favorite;

import com.axelynicky.user_service.Domain.Favorite;
import com.axelynicky.user_service.Exceptions.NotFoundExeption;
import com.axelynicky.user_service.Repository.ClientRepository;
import com.axelynicky.user_service.Repository.FavoriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Favorite> getClientFavorites(Long clientId) throws NotFoundExeption {
        Optional<List<Favorite>> databseRes = favoriteRepository.findAllByClientId(clientId);
        return databseRes.get();
    }

    @Override
    public void addClientFavorite(Long clientId) throws NotFoundExeption {

    }

    @Override
    public void removeClientFavorite(Long clientId, Long favoriteId) throws NotFoundExeption {

    }
}
