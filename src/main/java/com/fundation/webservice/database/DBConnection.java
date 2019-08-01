/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 *
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
 * Class that allows make the connection with the database.
 * This class use the pattern design singlenton.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class DBConnection {
    private static DBConnection dbcon;
    private static Connection conn;

    private DBConnection() {
        initConnection();
    }

    /**
     * This method let me return the instance DBConnection.
     * @return DBConnection variable save the instance.
     */
    public static DBConnection getInstance() {
        if (dbcon == null) {
            dbcon = new DBConnection();
        }
        return dbcon;
    }

    /**
     *  Method that initializes the connection to the database.
     */
    private static void initConnection() {
        String WEBSERVER_DB = "";
        String USER_NAME = "";
        String USER_PASSWORD = "";
        String PORT_CONNECTION = "";
        String HOST_NAME = "";

        try {
            WEBSERVER_DB = Util.getInstance().getConfig().getWebserverdb();
            USER_NAME = Util.getInstance().getConfig().getRoot();
            USER_PASSWORD = Util.getInstance().getConfig().getPassword();
            PORT_CONNECTION = Util.getInstance().getConfig().getPort();
            HOST_NAME = Util.getInstance().getConfig().getHost();
            conn = DriverManager.getConnection("jdbc:mysql://" + HOST_NAME + ":" + PORT_CONNECTION + "/" + WEBSERVER_DB, USER_NAME, USER_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e);
            e.getMessage();
        }
    }

    /**
     * This method let me return the connections to db.
     * @return conn the variable tha contain the connection.
     */
    public Connection getConnection() {
        return conn;
    }
}
