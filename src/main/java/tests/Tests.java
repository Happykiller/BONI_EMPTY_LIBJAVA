package tests;

import com.bonitasoft.libJavaProject.LibJava;
import com.google.gson.Gson;

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