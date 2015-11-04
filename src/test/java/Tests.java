import com.bonitasoft.libJavaProject.LibJava;

/**
 * Created by Fabrice.R on 22/02/2015.
 */
public class Tests {
    public static void main(String [ ] args){
        LibJava.message("===== Mes tests ======");
        testSayHelloMessage();
    }

    public static void testSayHelloMessage(){
        String strRetour = "";
        String attendu = "HelloWorld";
        strRetour = LibJava.sayHelloMessage();
        LibJava.message("Test sayHelloMessage : " + strRetour.equals(attendu));
    }
}