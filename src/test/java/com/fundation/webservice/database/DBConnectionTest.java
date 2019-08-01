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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that allows to carry out the connection test with the database.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class DBConnectionTest {
    @Test
    public void getConnection_verifyNotNullConnection() {
        assertNotNull(DBConnection.getInstance().getConnection());
    }

    @Test
    public void getInstance_verifyNotNullInstance() {
        assertNotNull(DBConnection.getInstance());
    }
}