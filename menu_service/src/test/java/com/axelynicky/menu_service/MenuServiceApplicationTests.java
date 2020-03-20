package com.axelynicky.menu_service;

import com.axelynicky.menu_service.Domain.Eatable;
import com.axelynicky.menu_service.Domain.Menu;
import com.axelynicky.menu_service.Repository.MenuRepository;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MenuServiceApplicationTests {

    @Mock
    MenuRepository menuRepository;

    public void poblateDB() {
        Menu menu = new Menu("Bebidas", new Long(1));
        List<Eatable> eatables = new ArrayList<>();
        Eatable eatable = new Eatable();
        eatable.setId(new Long(1));
        eatable.setPrice(100.0f);
        eatable.setMenuId(new Long(1));
        eatable.setName("Cocacola");
        eatable.setType("Bebida");
        eatables.add(eatable);

        eatable.setId(new Long(2));
        eatable.setName("Cocacola Light");
        eatables.add(eatable);

        menu.setEatables(eatables);

        Optional<List<Menu>> optionalMenus = Optional.of(Arrays.asList(menu));
        when(menuRepository.findAllByRestorantId(new Long(1))).thenReturn(optionalMenus);
    }

    @Test
    void contextLoads() {
        poblateDB();
        try {
            List<Menu> menus = menuRepository.findAllByRestorantId(new Long(1)).get();
            assertEquals(1, menus.size());
            Menu menu = menus.get(0);
            assertEquals(2, menu.getEatables().size());
        } catch (NoSuchElementException e) {
            fail();
        }
    }

}
