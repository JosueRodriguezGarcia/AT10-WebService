/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.fundation.webservice.model;

import com.fundation.webservice.common.Util;
import com.fundation.webservice.database.Query;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class allows manage queries in the model.
 *
 * @author Jesus Menacho.
 * @version 1.0
 */
public class QueryDriver {
    private Query query;

    /**
     * The construct create a instance of Query.
     */
    public QueryDriver() {
        query = new Query();
    }

    /**
     *It allows to store the information in the database according to
     * the required attributes that are the checksum and the upload path.
     */
    public void saveInfo(String checksum, String path) {
        Calendar c = new GregorianCalendar();
        String date = Integer.toString(c.get(Calendar.DATE));
        String moth = Integer.toString(c.get(Calendar.MONTH));
        String year = Integer.toString(c.get(Calendar.YEAR));
        String saveDate = year + "-" + moth + "-" + date;
        String day_file = Util.getInstance().getConfig().getDay();
        query.insertChecksum(checksum, day_file, saveDate, path);
    }

    /**
     * Checks that the information exists in the database.
     *
     * @param checksum variable the file.
     * @return the value tru or false if info exits.
     */
    public boolean verifyExist(String checksum) {
        return (query.verifyCheckSumExist(checksum));
    }
}
