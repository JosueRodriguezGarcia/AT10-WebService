/**
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.fundation.webservice.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class implement the utility .
 *
 * @author Jesus Menacho
 * @version 1.0
 */

/**
 * The methos let me save variable about the connections to db.
 */
public class Util {
    private static Util Utilcreation = new Util();
    private static Config config;

    /**
     * This constructor let me initializer the initUnit.
     */
    private Util() {
        initUtil();
    }

    /**
     * This method let me return the instance Util class
     * @return Util
     */
    public static Util getInstance() {
        if (Utilcreation == null) {
            Utilcreation = new Util();
        }
        return Utilcreation;
    }

    /**
     * This method init the class util.
     */
    private static void initUtil() {
        final String USER_DIR;
        final String WEBSERVER_DB;
        final String USER_NAME;
        final String USER_PASSWORD;
        final String PORT_CONNECTION;
        final String HOST_NAME;

        USER_DIR = System.getProperty("user.dir");
        try (InputStream input = new FileInputStream(USER_DIR + "/config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            WEBSERVER_DB = properties.getProperty("dir.webserver_db");
            USER_NAME = properties.getProperty("dir.user_name");
            USER_PASSWORD = properties.getProperty("dir.user_password");
            PORT_CONNECTION = properties.getProperty("dir.port_connection");
            HOST_NAME=properties.getProperty("dir.host_name");
            config.setWebserverdb(WEBSERVER_DB);
            config.setRoot(USER_NAME);
            config.setPassword(USER_PASSWORD);
            config.setPort(PORT_CONNECTION);
            config.setHost(HOST_NAME);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("existo");
    }

    /**
     * This method let me obtain the class Config for access to variables.
     * @return Config.
     */
    public Config getConfig() {
        return config;
    }
}
