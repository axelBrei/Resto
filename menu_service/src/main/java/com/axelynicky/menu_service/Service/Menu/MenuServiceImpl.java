package com.axelynicky.menu_service.Service.Menu;

import com.axelynicky.menu_service.Domain.Menu;
import com.axelynicky.menu_service.Exceptions.NotFoundException;
import com.axelynicky.menu_service.Repository.MenuRepository;
import com.axelynicky.menu_service.WebModel.WebResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menu addMenu(String menuType, Long restorantId) {
        Menu menu = new Menu(menuType, restorantId);
        menu = menuRepository.save(menu);
        return menu;
    }

    @Override
    public void deleteMenu(Long menuId) throws NotFoundException {
        if (menuRepository.existsById(menuId)) {
            menuRepository.deleteById(menuId);
        } else
            throw new NotFoundException("No existe el menu que se quiere eliminar");
    }

    @Override
    public List<Menu> getMenuesForRestorant(Long restorantId) throws NotFoundException {
        Optional<List<Menu>> databaseResponse = menuRepository.findAllByRestorantId(restorantId);
        return databaseResponse.orElseThrow(() -> new NotFoundException("No existen menues asociados al restoran"));
    }
}
