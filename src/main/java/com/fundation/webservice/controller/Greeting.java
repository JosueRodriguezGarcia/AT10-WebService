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

/**
 * Implements a greeting type of response to a given request made to the webservice
 *
 * @author Alejandro Sanchez Luizaga
 * @version 1.0
 */
 public class Greeting {
    private String content;
    private String name;

    public Greeting(String content, String name) {
        this.content = content;
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
