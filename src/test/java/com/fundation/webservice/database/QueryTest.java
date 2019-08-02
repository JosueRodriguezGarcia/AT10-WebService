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

import java.util.*;

import static org.junit.Assert.*;

/**
 * Class that allows to carry out the tests to the queries the database.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class QueryTest {

    Query query = new Query();

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
        String expected = infContent.get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void insertChecksum_verifyDayRecordInDB() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "1";
        String expected = infContent.get(1);
        assertEquals(expected, actual);
    }

    @Test
    public void insertChecksum_verifyDateInDB() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "2019-07-30";
        String expected = infContent.get(2);
        assertEquals(expected, actual);
    }

    @Test
    public void insertChecksum_verifyRecordPath() {
        List<String> infContent = new ArrayList<String>();
        infContent = query.showContent("test1");
        String actual = "//testPath/path/file.mp4";
        String expected = infContent.get(3);
        assertEquals(expected, actual);
    }

}
