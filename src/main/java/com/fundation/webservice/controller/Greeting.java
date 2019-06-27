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
 * Implements the class to be created from a JSON data received by the webservice
 *
 * @author Alejandro Sánchez Luizaga
 * @version 0.1.0
 */
 public class Greeting {

    private int id;
    private String content;
    private String name;

    public Greeting(int id, String content, String name) {
        this.id = id;
        this.content = content;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
