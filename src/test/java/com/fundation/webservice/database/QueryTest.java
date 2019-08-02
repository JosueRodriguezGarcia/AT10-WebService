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
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class that allows to carry out the tests to the queries the database.
 *
 * @author Jesus Menacho.
 * @version 1.0
 */
public class QueryTest {

    Query query = new Query();
    final Integer CHECKSUM=0;
    final Integer DAYFILE=1;
    final Integer DATE=2;
    final Integer PATH=3;
    @Before
    public void settingVariable() {


        query.deleteByCheckSum("test1");
        Calendar c = new GregorianCalendar();
        String date = "31";
        String moth = "07";
        String year = "2019";
        String saveDate = year + "-" + moth + "-" + date;
        String day_file = Util.getInstance().getConfig().getDay();
        query.insertChecksum("test1", day_file, saveDate, "//testPath/path/file.mp4");
    }

    @Test
    public void insertChecksum_verifyInsertChecksumInDB() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "test1";
        String expected = infContent.get(CHECKSUM);
        assertEquals(expected, actual);
    }

    @Test
    public void insertChecksum_verifyDayRecordInDB() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "1";
        String expected = infContent.get(DAYFILE);
        assertEquals(expected, actual);
    }

    @Test
    public void insertChecksum_verifyDateInDB() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "2019-07-30";
        String expected = infContent.get(DATE);
        assertEquals(expected, actual);
    }

    @Test
    public void insertChecksum_verifyRecordPath() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "//testPath/path/file.mp4";
        String expected = infContent.get(PATH);
        assertEquals(expected, actual);
    }

    @Test
    public void verifyCheckSumExist_searchChecksum() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "test1";
        String expected = infContent.get(CHECKSUM);
        assertEquals(expected, actual);
    }

    @Test (expected = Exception.class)
    public void deleteByCheckSum_deleteCheckSum() {
        List<String> infContent = new ArrayList<String>();
        query.deleteByCheckSum("test1");
        infContent = query.showContent("test1");
        String nullString = infContent.get(CHECKSUM);
    }

    @Test
    public void showContent_verifyChecksumInDB() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "test1";
        String expected = infContent.get(CHECKSUM);
        assertEquals(expected, actual);
    }

    @Test
    public void showContent_verifyDayRecordInDB() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "1";
        String expected = infContent.get(DAYFILE);
        assertEquals(expected, actual);
    }

    @Test
    public void showContent_verifyDateInDB() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "2019-07-30";
        String expected = infContent.get(DATE);
        assertEquals(expected, actual);
    }

    @Test
    public void showContent_verifyRecordPath() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "//testPath/path/file.mp4";
        String expected = infContent.get(PATH);
        assertEquals(expected, actual);
    }
}
