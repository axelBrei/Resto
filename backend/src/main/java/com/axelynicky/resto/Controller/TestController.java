package com.axelynicky.resto.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hola")
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getString() {
        return "Hola como??";
    }
}
