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

import java.io.File;

/**
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public interface IConvert2 {
    void convert(File file);

    void convert(CriteriaConvert criteriaConvert);
}
