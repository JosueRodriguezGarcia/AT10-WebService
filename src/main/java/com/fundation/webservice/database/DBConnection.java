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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static DBConnection getInstance() {
        if (dbcon == null) {
            dbcon = new DBConnection();
        }
        return dbcon;
    }

    private static void initConnection() {

        final String WEBSERVER_DB;
        final String USER_NAME;
        final String USER_PASSWORD;
        final String PORT_CONNECTION;
        final String HOST_NAME;

        try {
            WEBSERVER_DB=Util.getInstance().getConfig().getWebserverdb();
            USER_NAME=Util.getInstance().getConfig().getRoot();
            USER_PASSWORD=Util.getInstance().getConfig().getPassword();
            PORT_CONNECTION=Util.getInstance().getConfig().getPort();
            HOST_NAME=Util.getInstance().getConfig().getHost();
            conn = DriverManager.getConnection("jdbc:mysql://"+HOST_NAME+":"+PORT_CONNECTION+"/"+WEBSERVER_DB, USER_NAME, USER_PASSWORD);
            System.out.println("existo");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
