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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements insert in a table .
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Query {
    /**
     * This method inster informations from name and json to Data Base
     */
    public void insertChecksum(String checksum, String date) {
        String sql = "INSERT INTO savedb(checksum,timeDate) VALUES(?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, checksum);
           statement.setString(2, date);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            System.out.printf(String.valueOf(e));
        }
    }

    /**
     * This method show informations from the table criteria.
     */
    public void getAllData() {
        List<String> infsavedb = new ArrayList<String>();
        String sql = "SELECT * FROM savedb";
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {

                System.out.println(result.getInt("id") + "\t" + result.getString("checksum") + "\t" + result.getDate("timeDate") );
            }
        } catch (SQLException e) {
            e.getMessage();
        }
       // return infsavedb;
    }
    /**
     * This method acording the checksum
     */
    public void searchChecksum(String checksum) {
        String sql = "SELECT * FROM savedb where checksum=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, checksum);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {

                System.out.println(result.getInt("id") + "\t" + result.getString("checksum") + "\t" + result.getDate("timeDate") );
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /**
     * This method delete informations acording a id from the table criteria.
     */
    public void deleteByIde(String ID) {
        String sql = "DELETE FROM savedb WHERE id = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    public static void main(String arg []){

        Query  query=new Query();
        query.getAllData();

    }
}
