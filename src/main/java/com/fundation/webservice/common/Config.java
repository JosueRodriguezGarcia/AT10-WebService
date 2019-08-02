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
 * Stores all the information required in the database such as
 * connection data and Upload and Download folders.
 * @author Jesus Menacho
 * @version 1.0
 */
public class Config {
    private String webserverdb;
    private String root;
    private String password;
    private String port;
    private String host;
    private String uploaddir;
    private String downloadDir;
    private String day;

    /**
     * Returns the value of the remaining days or the duration of the file.
     * @return getDay value the day remain.
     */
    public String getDay() {
        return day;
    }

    /**
     * Assigns the value of the remaining days or the duration of the file.
     * @param day value the day remain.
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Returns the value of the rising variable.
     * @return uploaddir the value of the file up.
     */
    public String getUploaddir() {
        return uploaddir;
    }

    /**
     * Assing the value of the rising variable.
     * @param uploaddir take the value of the file up.
     */
    public void setUploaddir(String uploaddir) {
        this.uploaddir = uploaddir;
    }

    /**
     * Returns the value of the downstream variable.
     * @return downloadDir value the download file server.
     */
    public String getDownloadDir() {
        return downloadDir;
    }

    /**
     * Assing the value of the downstream variable.
     * @param downloadDir take the value to download server.
     */
    public void setDownloadDir(String downloadDir) {
        this.downloadDir = downloadDir;
    }

    /**
     * Returns the name of our database.
     * @return webserverdb value the name the DB.
     */
    public String getWebserverdb() {
        return webserverdb;
    }

    /**
     * Returns the name variable of the database.
     * @return webserverdb variable to return name DB.
     */
    public void setWebserverdb(String webserverdb) {
        this.webserverdb = webserverdb;
    }

    /**
     * Rertun variable the user name of the database.
     * @return root value of variable user name.
     */
    public String getRoot() {
        return root;
    }

    /**
     * Return the  variable mentions the user name of the database.
     * @return root set the variable user name.
     */
    public void setRoot(String root) {
        this.root = root;
    }

    /**
     * Return the variable password to data base user.
     * @return password is a variable is use for the DB connections.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Return the variable password to data base user.
     * @param password let setting the variable password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return the variable to the port connections to the database MySql.
     * @return port to use MySql.
     */
    public String getPort() {
        return port;
    }

    /**
     * Variable to the port connections to the database MySql.
     * @param port Variable to the port connections.
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Return variable assigns the host where the files will be hosted.
     * @return host variable host save.
     */
    public String getHost() {
        return host;
    }

    /**
     * Variable assigns the host where the files will be hosted.
     * @param host variable host use.
     */
    public void setHost(String host) {
        this.host = host;
    }
}