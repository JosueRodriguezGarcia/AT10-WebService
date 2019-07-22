/**
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.fundation.webservice.database;

import com.fundation.webservice.common.Util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Implements insert in a table .
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class DBConnection {
    private static DBConnection dbcon = new DBConnection();
    private static Connection conn;

    private DBConnection() {
        initConnection();
    }

    /**
     * This method let me return the instance DBConnection.
     * @return DBConnection.
     */
    public static DBConnection getInstance() {
        if (dbcon == null) {
            dbcon = new DBConnection();
        }
        return dbcon;
    }

    /**
     *  This method let me initConnection.
     */
    private static void initConnection() {
        String WEBSERVER_DB = "";
        String USER_NAME = "";
        String USER_PASSWORD = "";
        String PORT_CONNECTION = "";
        String HOST_NAME = "";

        try {
            final String USER_DIR;
            USER_DIR = System.getProperty("user.dir");
            try (InputStream input = new FileInputStream(USER_DIR + "/application.properties")) {
                Properties properties = new Properties();
                properties.load(input);
                WEBSERVER_DB = properties.getProperty("dir.webserver_db");
                USER_NAME = properties.getProperty("dir.user_name");
                USER_PASSWORD = properties.getProperty("dir.user_password");
                PORT_CONNECTION = properties.getProperty("dir.port_connection");
                HOST_NAME = properties.getProperty("dir.host_name");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(WEBSERVER_DB);
            conn = DriverManager.getConnection("jdbc:mysql://" + HOST_NAME + ":" + PORT_CONNECTION + "/" + WEBSERVER_DB, USER_NAME, USER_PASSWORD);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /**
     * This method let me return the connections to db.
     * @return conn.
     */
    public Connection getConnection() {
        return conn;
    }
}
