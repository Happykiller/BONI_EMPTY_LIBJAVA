import com.happykiller.model.Personne;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.bonita.lib.projet.LibJava;

import org.bonitasoft.engine.api.APIAccessor;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.identity.ContactData;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserSearchDescriptor;
import org.bonitasoft.engine.identity.impl.ContactDataBuilder;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public void testGetProperty() throws Exception {
        String receive;
        String waiting = "true";

        //-Dproperties.path=C:\DATAS\myProperties.properties
        //debug=true

        Properties props = System.getProperties();
        props.setProperty("properties.path", "myProperties.properties");

        receive = LibJava.getProperty("debug");
        Assert.assertEquals(receive,waiting);
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

    @Test
    public void testCompareTwoObject() throws Exception {
        User per1 = createUser("walter.bates", 4l);
        User per2 = createUser("walter.bates", 4l);

        Assert.assertTrue(EqualsBuilder.reflectionEquals(per1,per2));
    }

    @Test
    public void testGetUserMail() throws Exception {
        APIAccessor apiAccessor = Mockito.mock(APIAccessor.class);
        IdentityAPI identityApi = Mockito.mock(IdentityAPI.class);
        Mockito.when(apiAccessor.getIdentityAPI()).thenReturn(identityApi);
        ContactData contactData = new ContactDataBuilder().setEmail("walter.bates@acme.com").done();
        Mockito.when(identityApi.getUserContactData(42l,false)).thenReturn(contactData);

        String receive;
        String waiting = "walter.bates@acme.com";

        receive = LibJava.getUserMail(apiAccessor, 42l);

        Assert.assertEquals(waiting,receive);
    }

    @Test
    public void testGetUsersByMail() throws Exception {
        //MOCK
        IdentityAPI identityApi = Mockito.mock(IdentityAPI.class);
        SearchOptions so = new SearchOptionsBuilder(0, 10000).filter(UserSearchDescriptor.ENABLED, true).done();
        SearchResult<User> liste = new SearchResult<User>() {
            public long getCount() {
                return 1;
            }

            public List<User> getResult() {
                List<User> users = new ArrayList<User>();
                users.add(createUser("walter.bates", 4l));
                return users;
            }
        };
        Mockito.when(identityApi.searchUsers(so)).thenReturn(liste);

        ContactData contactData = new ContactDataBuilder().setEmail("walter.bates@acme.com").done();
        Mockito.when(identityApi.getUserContactData(4l,false)).thenReturn(contactData);

        //TEST
        List<Long> receive;
        List<Long> waiting = new ArrayList<Long>();
        waiting.add(4l);

        String mail = "walter.bates@acme.com";

        receive = LibJava.getUsersByMail(identityApi, mail);

        Assert.assertEquals(waiting,receive);
    }

    private User createUser(String name, Long id){
        User myUser = Mockito.mock(User.class);
        Mockito.when(myUser.getId()).thenReturn(id);
        Mockito.when(myUser.getUserName()).thenReturn(name);
        return myUser;
    }


    @Test
    public void testGetAllNameFromBDM() throws Exception {
        Personne moi = new Personne();
        moi.setNom("Rosito");
        moi.setPrenom("Fabrice");

        String receive;
        String waiting = "Rosito Fabrice";

        receive = LibJava.getAllNameFromBDM(moi);

        Assert.assertEquals(waiting,receive);
    }
}