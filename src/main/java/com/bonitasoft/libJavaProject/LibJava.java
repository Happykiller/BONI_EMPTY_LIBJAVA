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

    public static String sayHelloMessage(){
        String retour = "HelloWorld";
        try {
            uilLogger.fine("sayHelloMessage - msg : HelloWorld.");
            return retour;
        }catch (Exception e) {
            uilLogger.fine("sayHelloMessage - Error : "+ e.getMessage());
            return null;
        }
    }
}