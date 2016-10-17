package org.bonita.lib.projet;

import org.bonitasoft.engine.api.APIAccessor;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserSearchDescriptor;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Fabrice.R on 21/02/2015.
 */
public class LibJava {

    private static final Logger logger = Logger.getLogger("org.bonita.lib.projet");

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
     * @param processAPI la variable est fournie par bonita depuis APIAcessor (liste de droite dans l'editeur d'expression)
     * @param caseId la variable est fournie par bonita rootProcessInstanceId (liste de droite dans l'editeur d'expression)
     * @return l'id de l'initiateur
     */
    public static Long getUserIdInitiator(ProcessAPI processAPI, Long caseId){
        try {
            Long userId;

            ProcessInstance myProcessInstance = processAPI.getProcessInstance(caseId);

            userId = myProcessInstance.getStartedBy();

            return userId;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @param processAPI la variable est fournie par bonita depuis APIAcessor (liste de droite dans l'editeur d'expression)
     * @param identityAPI la variable est fournie par bonita depuis APIAcessor (liste de droite dans l'editeur d'expression)
     * @param caseId la variable est fournie par bonita rootProcessInstanceId (liste de droite dans l'editeur d'expression)
     * @return le prénom
     */
    public static String getUserFirstNameInitiator(ProcessAPI processAPI, IdentityAPI identityAPI, Long caseId){
        try {
            Long userId;

            ProcessInstance myProcessInstance = processAPI.getProcessInstance(caseId);

            userId = myProcessInstance.getStartedBy();

            String firstName = identityAPI.getUser(userId).getFirstName();

            return firstName;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @param processAPI la variable est fournie par bonita depuis APIAcessor (liste de droite dans l'editeur d'expression)
     * @param identityAPI la variable est fournie par bonita depuis APIAcessor (liste de droite dans l'editeur d'expression)
     * @param caseId la variable est fournie par bonita rootProcessInstanceId (liste de droite dans l'editeur d'expression)
     * @return le nom
     */
    public static String getUserLastNameInitiator(ProcessAPI processAPI, IdentityAPI identityAPI, Long caseId){
        try {
            Long userId;

            ProcessInstance myProcessInstance = processAPI.getProcessInstance(caseId);

            userId = myProcessInstance.getStartedBy();

            String lastName = identityAPI.getUser(userId).getLastName();

            return lastName;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @param identityAPI la variable est fournie par bonita depuis APIAcessor (liste de droite dans l'editeur d'expression)
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

    /**
     *
     * @param aPIAccessor la variable est fournie par bonita (liste de droite dans l'editeur d'expression)
     * @param userId L'id de l'utilisateur
     * @return l'email pro de l'utilisateur
     */
    public static String getUserMail(APIAccessor aPIAccessor, Long userId){
        try {
            String mail = aPIAccessor.getIdentityAPI().getUserContactData(userId,false).getEmail();

            return mail;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @param indentityAPI
     * @param mail
     * @return
     */
    public static List<Long> getUsersByMail(IdentityAPI indentityAPI, String mail){
        try {
            List<Long> response = new ArrayList<Long>();

            SearchOptions so = new SearchOptionsBuilder(0, 10000).filter(UserSearchDescriptor.ENABLED, true).done();
            List<User> users = indentityAPI.searchUsers(so).getResult();

            for (int i = 0; i < users.size(); i++) {
                User currentUser = users.get(i);
                String mailCurrent = indentityAPI.getUserContactData(currentUser.getId(), false).getEmail();
                if(mailCurrent != null && mailCurrent.equals(mail)){
                    response.add(currentUser.getId());
                }
            }

            return response;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }
}