package com.axelynicky.restorant_service.Controller;

import com.axelynicky.restorant_service.Service.Restorant.RestorantService;
import com.axelynicky.restorant_service.WebModels.Request.NewRestoranRequest;
import com.axelynicky.restorant_service.WebModels.Response.WebResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RestoranController {

    @Autowired
    RestorantService restorantService;

    @RequestMapping(value = "/getTop10", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTop10Restorant() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new WebResponse("Ok",restorantService.getTop10restorants())
                );
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('RESTORAN')")
    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRestorant(@RequestBody NewRestoranRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new WebResponse("Ok", restorantService.addRestorant(request))
                );
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('RESTORAN')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteRestoran(@RequestParam("restorantId") Long restorantId) {
        restorantService.deleteRestorant(restorantId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new WebResponse("Ok", "Borrado exitoso")
                );
    }
}
