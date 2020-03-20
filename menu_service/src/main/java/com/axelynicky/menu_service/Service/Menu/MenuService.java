package com.axelynicky.menu_service.Service.Menu;

import com.axelynicky.menu_service.Domain.Menu;

import java.util.List;

public interface MenuService {
    Menu addMenu(String menuType, Long restorantId);

    void deleteMenu(Long menuId);

    List<Menu> getMenuesForRestorant(Long restorantId);
}
