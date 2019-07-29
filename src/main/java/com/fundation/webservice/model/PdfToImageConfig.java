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

/**
 * Enumeration class that holds the default key values for the config parameter within the pdf to image conversion
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public enum PdfToImageConfig {
    dpi("150"),
    formatColor("RGB"),
    thumbnail("false");
    private String value;

    PdfToImageConfig(String value) {
        this.value = value;
    }

    /**
     * This is a getter method that returns the string paired to its corresponding enum element.
     *
     * @return the string paired to a enum element
     */
    public String getValue() {
        return value;
    }
}
