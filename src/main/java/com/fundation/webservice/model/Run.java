/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.fundation.webservice.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Superclass that inherits to classes that intend to run executable binaries.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class Run {
    protected String USER_DIR;
    protected String TOOLS_DIR;
    protected List<String> commandLine;

    public Run () {
        USER_DIR = System.getProperty("user.dir") + "/";
        try (InputStream input = new FileInputStream(USER_DIR + "config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            TOOLS_DIR = properties.getProperty("dir.tools");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.commandLine = new ArrayList<>();
    }

    /**
     * This method executes any executable binary.
     * The command line is read from commandLine list that has to be prefilled before calling this method.
     * Any output to console is ignored.
     */
    public void run() {
        try {
            Process process = new ProcessBuilder(commandLine).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            process.waitFor();
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("", e);
        }
    }

    /**
     * Safe initialization of the list structure that stores the command line string to be pass to run() method.
     */
    public void initCommandLine() {
        commandLine.clear();
        commandLine.add("cmd.exe");
        commandLine.add("/c");
    }
}
