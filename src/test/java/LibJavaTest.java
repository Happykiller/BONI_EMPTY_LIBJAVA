import com.bonitasoft.libJavaProject.LibJava;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
} 
