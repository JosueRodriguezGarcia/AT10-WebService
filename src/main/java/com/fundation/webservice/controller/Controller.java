/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.fundation.webservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements the REST controller. All HTTP requests will be handled by this controller.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
@RestController
public class Controller {
    private final String HELLO = "Hello";

    @RequestMapping("/greet")
    public String greeting() {
        Greeting greeting = new Greeting(1, HELLO, "Chango");
        return greeting.getContent();
    }
    
    @RequestMapping("/convert")
    public Greeting conversion(@RequestParam(value = "content", defaultValue = "") String content, @RequestParam(value = "name", defaultValue = "") String name) {
        return new Greeting(1, content, name);
    }

    @PostMapping("/body")
    Greeting newGreeting(@RequestBody Greeting greeting) {
        return new Greeting(3*greeting.getId(),greeting.getContent() + "RES", greeting.getName() + "RES");
    }
}
