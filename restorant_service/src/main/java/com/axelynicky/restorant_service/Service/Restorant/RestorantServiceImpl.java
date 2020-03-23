package com.axelynicky.restorant_service.Service.Restorant;

import com.axelynicky.restorant_service.Domain.Location;
import com.axelynicky.restorant_service.Domain.Restorant;
import com.axelynicky.restorant_service.Exceptions.NotFoundException;
import com.axelynicky.restorant_service.Repository.RestorantRepository;
import com.axelynicky.restorant_service.WebModels.Request.NewRestoranRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Component
public class RestorantServiceImpl implements RestorantService {

    @Autowired
    RestorantRepository restorantRepository;
    @Autowired
    EntityManager entityManager;

    @Transactional
    @Override
    public Restorant addRestorant(NewRestoranRequest request) {
        Restorant restorant = new Restorant(request.getRestorantName());
        restorant.setScore(0.0f);
        restorant = restorantRepository.save(restorant);
        final Long restorantId = restorant.getId();
        List<Location> locations = request.getLocations().stream().map(loc -> {
           loc.setRestorantId(restorantId);
           return loc;
        }).collect(Collectors.toList());
        try{
            //TODO fetch to locations microservice
        }catch (Exception e){
            e.printStackTrace();
        }
        return restorant;
    }

    @Transactional
    @Override
    public void deleteRestorant(Long restorantId) {
        // TODO: delete all locations for this restorant
        restorantRepository.deleteById(restorantId);
    }

    @Override
    public List<Restorant> getTop10restorants() {
        List<Restorant> databaseResponse = restorantRepository.findAll();
        if(databaseResponse.isEmpty()){
            throw new NotFoundException("No se han encontrado restoranes");
        }
        return databaseResponse;
    }

    @Override
    public List<Restorant> getRestorantsByNeighborhood(String hoodName) {
        return null;
    }
}
