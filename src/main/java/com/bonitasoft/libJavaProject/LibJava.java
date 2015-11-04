package com.bonitasoft.libJavaProject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * Created by Fabrice.R on 21/02/2015.
 */
public class LibJava {

    private static final Logger logger = Logger.getLogger("com.bonitasoft.LibJava");
	
	public static void message(String message){
        try {
            logger.info(message);
            System.out.println(message);
        }catch (Exception ex) {
            logger.severe("message - Error : " + ex);
        }
    }

    public static void traceExeption(Throwable aThrowable){
        String methodeName = Thread.currentThread().getStackTrace()[2].getMethodName();
        message("Error ("+methodeName+") : "+ getStackTrace(aThrowable));
    }

    public static String getStackTrace(Throwable aThrowable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static String sayHelloMessage(){
        try {
            return "HelloWorld";
        }catch (Exception e) {
            traceExeption(e);
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
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }
}