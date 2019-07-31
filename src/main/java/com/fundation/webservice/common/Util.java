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
 * Class implement the utility .
 *
 * @author Jesus Menacho
 * @version 1.0
 */

/**
 * The util class implements the singleton which allows to read
 * the variables only once which are used in the DB.
 */
public class Util {
    private static Util Utilcreation;
    private static Config config=new Config();

    /**
     * This constructor let me initializer the initUnit.
     */
    private Util() {
        initUtil();
    }

    /**
     * Method let me return the instance Util class
     * @return Util util calss
     */
    public static Util getInstance() {
        if (Utilcreation == null) {
            Utilcreation = new Util();
        }
        return Utilcreation;
    }

    /**
     * Method init the class util.
     */
    private static void initUtil() {
        final String USER_DIR;
        final String WEBSERVER_DB;
        final String USER_NAME;
        final String USER_PASSWORD;
        final String PORT_CONNECTION;
        final String HOST_NAME;
        final String UPLOAD_DIR;
        final String DOWNLOAD_DIR;
        final String DAY_FILE;

        USER_DIR = System.getProperty("user.dir");
        try (InputStream input = new FileInputStream(USER_DIR + "/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            WEBSERVER_DB = properties.getProperty("dir.webserver_db");
            USER_NAME = properties.getProperty("dir.user_name");
            USER_PASSWORD = properties.getProperty("dir.user_password");
            PORT_CONNECTION = properties.getProperty("dir.port_connection");
            HOST_NAME = properties.getProperty("dir.host_name");
            UPLOAD_DIR=properties.getProperty("file.uploadDir");
            DOWNLOAD_DIR=properties.getProperty("file.conversionDir");
            DAY_FILE=properties.getProperty("dir.day_file");
            config.setDay(DAY_FILE);
            config.setWebserverdb(WEBSERVER_DB);
            config.setRoot(USER_NAME);
            config.setPassword(USER_PASSWORD);
            config.setPort(PORT_CONNECTION);
            config.setHost(HOST_NAME);
            config.setDownloadDir(DOWNLOAD_DIR);
            config.setUploaddir(UPLOAD_DIR);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method let me obtain the class Config for access to variables.
     * @return Config the class, constains all variable required in DB connections.
     */
    public Config getConfig() {
        return config;
    }
}
