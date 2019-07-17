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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class serves as JUnit test class for the Run class from our model entity.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class RunTest {
    @Test
    public void initCommandLine_stringResult() {
        Run run = new Run();
        run.initCommandLine();
        String actual = run.commandLine.get(0) + " " + run.commandLine.get(1);
        assertEquals("cmd.exe /c", actual);
    }
}
