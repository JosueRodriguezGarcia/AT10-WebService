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

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Class query allows you to perform methods of querying
 * the database such as data insertion queries, deletion among others..
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Query {
    /**
     * Method inserts information into the Database.
     */
    public void insertChecksum(String checksum,String day ,String dateCreation, String path) {
        String sql = "INSERT INTO FILERECORD(CHECKSUM, DAY, DATECREATION, PATH) VALUES(?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, checksum);
            statement.setInt(2, Integer.parseInt(day));
            statement.setDate(3, Date.valueOf(dateCreation));
            statement.setString(4, path);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /**
     * Method verifies that the one stored in the database.
     */
    public boolean verifyCheckSumExist(String checksum) {
        String sql = "SELECT CHECKSUM FROM FILERECORD";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                if (result.getString("CHECKSUM").equals(checksum)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return false;
    }


    /**
     * Method removes information from database according to checksum field.
     */
    public void deleteByCheckSum(String checksum) {
        String sql = "DELETE FROM FILERECORD WHERE CHECKSUM = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, checksum);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}