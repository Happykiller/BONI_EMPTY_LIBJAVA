import com.bonitasoft.libJavaProject.LibJava;

/**
 * Created by Fabrice.R on 22/02/2015.
 */
public class Tests {
    public static void main(String [ ] args){
        System.out.println("===== Mes tests ======");
        testSayHelloMessage();
    }

    public static void testSayHelloMessage(){
        String strRetour = "";
        String attendu = "HelloWorld";
        strRetour = LibJava.sayHelloMessage();
        System.out.println("Test sayHelloMessage : " + strRetour.equals(attendu));
    }
}