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
 * Implements the CriteriaAudio class File and the getter and setter´s methods.
 *
 * @author Limbert Alvaro Vargas Laura
 * @version 1.0
 */
public class CriteriaAudio extends CriteriaConvert {
    private String newFormat;
    private String aCodec;
    private int aBit;
    private int aChannel;
    private int aRate;

    public String getNewFormat() {
        return newFormat;
    }

    public void setNewFormat(String newFormat) {
        this.newFormat = newFormat;
    }

    public String getaCodec() {
        return aCodec;
    }

    public void setaCodec(String aCodec) {
        this.aCodec = aCodec;
    }

    public int getaBit() {
        return aBit;
    }

    public void setaBit(int aBit) {
        this.aBit = aBit;
    }

    public int getaChannel() {
        return aChannel;
    }

    public void setaChannel(int aChannel) {
        this.aChannel = aChannel;
    }

    public int getaRate() {
        return aRate;
    }

    public void setaRate(int aRate) {
        this.aRate = aRate;
    }
}
