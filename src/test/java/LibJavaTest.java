import com.bonitasoft.libJavaProject.LibJava;

import org.bonitasoft.engine.api.*;
import org.bonitasoft.engine.api.impl.transaction.platform.Logout;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * LibJava Tester.
 *
 * @author <Authors name>
 * @since <pre>nov. 4, 2015</pre>
 * @version 1.0
 */
public class LibJavaTest {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: sayHelloMessage()
     *
     */
    @Test
    public void testSayHelloMessage() throws Exception {
        String receive;
        String waiting = "HelloWorld";
        receive = LibJava.sayHelloMessage();
        Assert.assertEquals(receive,waiting);
    }

    /**
     *
     * Method: getDateTimeStr()
     *
     */
    @Test
    public void testGetDateTimeStr() throws Exception {
        String receive;

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date dateReturn = new java.sql.Date(currentDate.getTime());
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
        String waiting = formater.format(dateReturn);

        receive = LibJava.getDateTimeStr();

        Assert.assertEquals(receive,waiting);
    }

    /**
     *
     * Method: testGetUserMail()
     *
     */
    @Test
    public void testGetUserMail() throws Exception {
        // Setup access type (HTTP on local host)
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("server.url", "http://localhost:8080");
        parameters.put("application.name", "bonita");
        APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, parameters);

        // Authenticate and obtain API session
        LoginAPI loginAPI = TenantAPIAccessor.getLoginAPI();
        APISession session = loginAPI.login("install", "install");

        // Operation
        IdentityAPI myIdentityAPI = TenantAPIAccessor.getIdentityAPI(session);

        String receive;
        String waiting = "walter.bates@acme.com";

        Long id = myIdentityAPI.getUserByUserName("walter.bates").getId();
        receive = LibJava.getUserMail(myIdentityAPI, id);

        Assert.assertEquals(waiting,receive);
    }

    /**
     *
     * Method: testGetUserMail()
     *
     */
    @Test
    public void testGetUserIdInitiator() throws Exception {
        // Setup access type (HTTP on local host)
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("server.url", "http://localhost:8080");
        parameters.put("application.name", "bonita");
        APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, parameters);

        // Authenticate and obtain API session
        LoginAPI loginAPI = TenantAPIAccessor.getLoginAPI();
        APISession session = loginAPI.login("install", "install");

        Long receive;
        Long waiting = 4l;

        Long caseId = 4001l;

        receive = LibJava.getUserIdInitiator(TenantAPIAccessor.getProcessAPI(session), caseId);

        Assert.assertEquals(waiting,receive);
    }
} 
