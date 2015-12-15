package com.bonitasoft.libJavaProject;

import org.bonitasoft.engine.api.APIAccessor;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.bpm.process.ProcessInstance;

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

    /**
     *
     * @param message A message
     */
    private static void message(String message){
        try {
            logger.info(message);
            System.out.println(message);
        }catch (Exception ex) {
            logger.severe("message - Error : " + ex);
        }
    }

    /**
     *
     * @param aThrowable An exception
     */
    private static void traceExeption(Throwable aThrowable){
        String methodeName = Thread.currentThread().getStackTrace()[2].getMethodName();
        message("Error ("+methodeName+") : "+ getStackTrace(aThrowable));
    }

    /**
     *
     * @param aThrowable An exception
     * @return A displayable trace
     */
    private static String getStackTrace(Throwable aThrowable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    /**
     *
     * @return A message
     */
    public static String sayHelloMessage(){
        try {
            return "HelloWorld";
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @return La date et heure au format yyyyMMddHHmmss
     */
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

    /**
     *
     * @param apiAccessor la variable est fournie par bonita (liste de droite dans l'editeur d'expression)
     * @param caseId la variable est fournie par bonita rootProcessInstanceId (liste de droite dans l'editeur d'expression)
     * @return l'id de l'initiateur
     */
    public static Long getUserIdInitiator(APIAccessor apiAccessor, Long caseId){
        try {
            Long userId;

            ProcessInstance myProcessInstance = apiAccessor.getProcessAPI().getProcessInstance(caseId);

            userId = myProcessInstance.getStartedBy();

            return userId;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @param apiAccessor la variable est fournie par bonita (liste de droite dans l'editeur d'expression)
     * @param caseId la variable est fournie par bonita rootProcessInstanceId (liste de droite dans l'editeur d'expression)
     * @return le prénom
     */
    public static String getUserFirstNameInitiator(APIAccessor apiAccessor, Long caseId){
        try {
            Long userId;

            ProcessInstance myProcessInstance = apiAccessor.getProcessAPI().getProcessInstance(caseId);

            userId = myProcessInstance.getStartedBy();

            String firstName = apiAccessor.getIdentityAPI().getUser(userId).getFirstName();

            return firstName;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @param apiAccessor la variable est fournie par bonita (liste de droite dans l'editeur d'expression)
     * @param caseId la variable est fournie par bonita rootProcessInstanceId (liste de droite dans l'editeur d'expression)
     * @return le nom
     */
    public static String getUserLastNameInitiator(APIAccessor apiAccessor, Long caseId){
        try {
            Long userId;

            ProcessInstance myProcessInstance = apiAccessor.getProcessAPI().getProcessInstance(caseId);

            userId = myProcessInstance.getStartedBy();

            String lastName = apiAccessor.getIdentityAPI().getUser(userId).getLastName();

            return lastName;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @param identityAPI IdentityAPI
     * @param userId L'id de l'utilisateur
     * @return l'email pro de l'utilisateur
     */
    public static String getUserMail(IdentityAPI identityAPI, Long userId){
        try {
            String mail = identityAPI.getUserContactData(userId,false).getEmail();

            return mail;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }
}