package com.axelynicky.menu_service.Service.Eatable;

import com.axelynicky.menu_service.Domain.Eatable;
import com.axelynicky.menu_service.Exceptions.NotFoundException;
import com.axelynicky.menu_service.Exceptions.NotModifiedException;
import com.axelynicky.menu_service.WebModel.UpdateEatableRequest;

import java.util.List;

public interface EatableService {
    Eatable addEatable(Eatable eatable);

    void removeEatable(Long menuId, Long eatableId) throws NotFoundException;

    List<Eatable> getEatablesForMenu(Long menuId) throws NotFoundException;

    void updateEatable(UpdateEatableRequest request) throws NotFoundException, NotModifiedException;
}
