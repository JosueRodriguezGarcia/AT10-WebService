package com.fundation.webservice.model;

import com.fundation.webservice.common.Util;
import com.fundation.webservice.database.Query;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class QueryDriver {
    private Query query;

    public QueryDriver() {
        query=new Query();
    }
    public void saveInfo(String checksum,String path){
        Calendar c = new GregorianCalendar();
        String date = Integer.toString(c.get(Calendar.DATE));
        String moth = Integer.toString(c.get(Calendar.MONTH));
        String year = Integer.toString(c.get(Calendar.YEAR));
        String saveDate=year+"-"+moth+"-"+date;
        String day_file= Util.getInstance().getConfig().getDay();
//        String path =Util.getInstance().getConfig().getUploaddir();
        query.insertChecksum(checksum,day_file,saveDate,path);
    }
    public boolean verifiyExist(String checksum){
        return (query.verifyChecksumExist(checksum));
    }
    public String extractPath(String checksum){
        return (query.extractPath(checksum));
    }
}