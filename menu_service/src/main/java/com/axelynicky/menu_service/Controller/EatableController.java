package com.axelynicky.menu_service.Controller;

import com.axelynicky.menu_service.Domain.Eatable;
import com.axelynicky.menu_service.Domain.Menu;
import com.axelynicky.menu_service.Repository.EatableRepository;
import com.axelynicky.menu_service.Repository.MenuRepository;
import com.axelynicky.menu_service.Service.Eatable.EatableService;
import com.axelynicky.menu_service.WebModel.UpdateEatableRequest;
import com.axelynicky.menu_service.WebModel.WebResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eatables")
public class EatableController {

    @Autowired
    EatableService eatableService;

    @PreAuthorize("hasRole('ROLE_RESTORAN') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public ResponseEntity addEatableToMenu(@RequestBody Eatable eatable) {
        return ResponseEntity
                .ok(new WebResponse(
                        "Consumible creado con exito"
                        , eatableService.addEatable(eatable)
                ));
    }

    @PreAuthorize("hasRole('ROLE_RESTORAN') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity removeEatableFromMenu(@RequestParam(value = "menuId") Long menuId, @RequestParam(value = "eatableId") Long eatableId) {
        eatableService.removeEatable(menuId, eatableId);
        return ResponseEntity.ok(new WebResponse("Se ha elminiado con exito"));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getEatablesForMenu(@RequestParam(value = "menuId") Long menuId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new WebResponse(eatableService.getEatablesForMenu(menuId))
                );
    }

    @PreAuthorize("hasRole('ROLE_RESTORAN') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateEatableInMenu(@RequestBody UpdateEatableRequest body) {
        eatableService.updateEatable(body);
        return ResponseEntity.ok(new WebResponse("Actualizacion realizada con exito"));
    }

}
