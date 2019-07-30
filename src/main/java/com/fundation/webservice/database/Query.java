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

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.*;
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
    public void insertChecksum(String checksum,String day ,String dateCreation, String path) {
        String sql = "INSERT INTO FILERECORD(CHECKSUM,DAY,DATECREATION,PATH) VALUES(?,?,?,?)";
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
            System.out.printf(String.valueOf(e));
        }
    }

    /**
     * This method show informations from the table criteria.
     */
    public List getAllData() {
        List<String> dataList = new ArrayList<String>();
        String sql = "SELECT * FROM FILERECORD";
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {

                dataList.add(result.getString("CHECKSUM") + "\t" + result.getInt("DAY") + "\t" + result.getDate("DATECREATION")+"\t" + result.getString("PATH") );
                }
        } catch (SQLException e) {
            e.getMessage();
        }
        return dataList;
    }
    /**
     * This method acording the checksum
     */
    public boolean verifyChecksumExist(String checksum) {
        String sql = "SELECT * FROM FILERECORD";
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
            System.out.println(e);
            e.getMessage();
        }
        return false;
    }

    /**
     * This method acording the checksum
     */
    public String extractPath(String checksum) {
        String sql = "SELECT * FROM FILERECORD";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                if (result.getString("CHECKSUM").equals(checksum)){
                    return result.getString("PATH") ;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.getMessage();
        }
        return "";
    }

    /**
     * This method delete informations acording a id from the table criteria.
     */
    public void deleteByCheckSum(String checksum) {
        String sql = "DELETE FROM FILERECORD WHERE CHECKSUM = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, checksum);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.getMessage();
        }
    }
    public static void main(String hola[]){
        Query query=new Query();
       // query.insertChecksum("asdfa","11","2019-01-01","asdfasdf");
        //query.getAllData();
        System.out.println(query.verifyChecksumExist("asdfad"));
        //query.deleteByCheckSum("asdfa");
    }
}
