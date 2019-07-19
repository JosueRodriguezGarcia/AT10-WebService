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
public class Config {
    private String webserverdb;
    private String root;
    private String password;
    private String port;
    private String host;

    public String getWebserverdb() {
        return webserverdb;
    }

    public void setWebserverdb(String webserverdb) {
        this.webserverdb = webserverdb;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
