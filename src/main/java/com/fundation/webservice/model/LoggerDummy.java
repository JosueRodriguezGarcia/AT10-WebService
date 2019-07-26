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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class LoggerDummy {
    private static Log logger = LogFactory.getLog(LoggerDummy.class);

    public static void main(String[] args) {
        logger.debug("Looking for dependencies");
        logger.info("Initiating services");
    }
}
