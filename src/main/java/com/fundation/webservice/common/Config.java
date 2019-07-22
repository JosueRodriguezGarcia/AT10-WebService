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

/**
 * This class implement the utility .
 *
 * @author Jesus Menacho
 * @version 1.0
 */

/**
 * This method let save variables about the connection to the DB.
 */
public class Config {
    private static String webserverdb;
    private static String root;
    private static String password;
    private static String port;
    private static String host;

    /**
     * This method return the variable
     * @return webserverdb
     */
    public String getWebserverdb() {
        return webserverdb;
    }

    /**
     * This method return the variable
     * @return webserverdb
     */
    public void setWebserverdb(String webserverdb) {
        this.webserverdb = webserverdb;
    }

    /**
     * This method return the variable
     * @return root
     */
    public String getRoot() {
        return root;
    }

    /**
     * This method return the variable
     * @return root
     */
    public void setRoot(String root) {
        this.root = root;
    }

    /**
     * This method return the variable
     * @return password;
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method return the variable
     * @return password;
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method return the variable
     * @return port
     */
    public String getPort() {
        return port;
    }

    /**
     * This method let set the variable
     * @return port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * This method let set the variable
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * This method let set the variable
     * @return host
     */
    public void setHost(String host) {
        this.host = host;
    }
}
