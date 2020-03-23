package com.axelynicky.restorant_service.Service.Restorant;

import com.axelynicky.restorant_service.Domain.Restorant;
import com.axelynicky.restorant_service.WebModels.Request.NewRestoranRequest;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

public interface RestorantService {

    @Transactional
    Restorant addRestorant(NewRestoranRequest request);

    @Transactional
    void deleteRestorant(Long restorantId);

    List<Restorant> getTop10restorants();

    List<Restorant> getRestorantsByNeighborhood(String hoodName);
}
