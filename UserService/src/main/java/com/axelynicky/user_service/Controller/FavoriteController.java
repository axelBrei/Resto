package com.axelynicky.user_service.Controller;

import com.axelynicky.user_service.Domain.Favorite;
import com.axelynicky.user_service.Service.Favorite.FavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @RequestMapping(value = "/client/get", method = RequestMethod.GET)
    public ResponseEntity getClientFavorites(@RequestParam("clientId") Long clientId) {
        List<Favorite> favoriteList = favoriteService.getClientFavorites(clientId);
        favoriteList.size();
        return ResponseEntity.ok(favoriteList);
    }
}
