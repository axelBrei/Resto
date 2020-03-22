package com.axelynicky.menu_service.Controller;

import com.axelynicky.menu_service.Domain.Menu;
import com.axelynicky.menu_service.Repository.MenuRepository;
import com.axelynicky.menu_service.Service.Menu.MenuService;
import com.axelynicky.menu_service.WebModel.WebResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @PreAuthorize("hasRole('ROLE_RESTORAN') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public ResponseEntity addMenu(@RequestParam(value = "type") String type, @RequestParam(value = "restorantId") Long restorantId) {
        return ResponseEntity.ok(
                new WebResponse(
                        "Menu creado",
                        menuService.addMenu(type, restorantId)
                )
        );
    }

    @PreAuthorize("hasRole('ROLE_RESTORAN') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteMenu(@RequestParam(value = "menuId") Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.ok(new WebResponse("Borrado con exito"));
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getMenuesForRestorant(@RequestParam(value = "restorantId", required = false) Long restorantId) {
        return ResponseEntity.ok(
                new WebResponse(menuService.getMenuesForRestorant(restorantId))
        );
    }
}
