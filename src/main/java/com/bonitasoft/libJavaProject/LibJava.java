package com.bonitasoft.libJavaProject;

import org.bonitasoft.engine.api.APIAccessor;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.bpm.process.ProcessInstanceCriterion;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Fabrice.R on 21/02/2015.
 */
public class LibJava {

    private static final Logger uilLogger = Logger.getLogger("com.bonitasoft.groovy");
	
	public static void trace(String message){
        try {
            uilLogger.info(message);
            System.out.println(message);
        }catch (Exception ex) {
            uilLogger.severe("trace - Error : " + ex);
        }
    }

    public static String sayHelloMessage(){
        String retour = "HelloWorld";
        try {
            return retour;
        }catch (Exception e) {
            trace("sayHelloMessage - Error : "+ e.getMessage());
            return null;
        }
    }
	
    public static String getDateTimeStr(){
        try {
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date dateReturn = new java.sql.Date(currentDate.getTime());
            SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
            return formater.format(dateReturn);
        }catch (Exception ex) {
            trace("getDateTimeStr - Error : " + ex);
            return null;
        }
    }
}