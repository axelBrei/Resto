package com.axelynicky.menu_service.Service.Eatable;

import com.axelynicky.menu_service.Domain.Eatable;
import com.axelynicky.menu_service.Exceptions.NotFoundException;
import com.axelynicky.menu_service.Exceptions.NotModifiedException;
import com.axelynicky.menu_service.Repository.EatableRepository;
import com.axelynicky.menu_service.Repository.MenuRepository;
import com.axelynicky.menu_service.WebModel.UpdateEatableRequest;
import com.axelynicky.menu_service.WebModel.WebResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EatableServiceImpl implements EatableService {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    EatableRepository eatableRepository;

    @Override
    public Eatable addEatable(Eatable eatable) {
        if (!menuRepository.existsById(eatable.getMenuId()))
            throw new NotFoundException("No existe el restoran al que se quiere agregar el consumible");

        return eatableRepository.save(eatable);
    }

    @Override
    public void removeEatable(Long menuId, Long eatableId) throws NotFoundException {
        eatableRepository.deleteByIdAndMenuId(eatableId, menuId);
    }

    @Override
    public List<Eatable> getEatablesForMenu(Long menuId) throws NotFoundException {
        return eatableRepository
                .findAllByMenuId(menuId)
                .orElseThrow(
                        () -> new NotFoundException("No se ha encontrado consumibles")
                );
    }

    @Override
    public void updateEatable(UpdateEatableRequest request) throws NotFoundException, NotModifiedException {
        Eatable eatable = eatableRepository
                .findByIdAndMenuId(request.getEatableId(), request.getMenuId())
                .orElseThrow(() -> new NotFoundException("No se encontro el consumible solicitado"));

        if (eatable.updateNameIfChange(request.getName()) || eatable.updatePriceIfChange(request.getPrice())) {
            eatableRepository.save(eatable);
            return;
        }

        throw new NotModifiedException("No se ha modificado el consumible");
    }
}
