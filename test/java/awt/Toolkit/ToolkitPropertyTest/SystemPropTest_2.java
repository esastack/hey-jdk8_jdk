/*
  @test %I% %E%
  @bug 6315717
  @summary verifies that system property sun.awt.enableExtraMouseButtons might be set to true by the command line
  @author Andrei Dmitriev : area=awt.mouse
  @run main/othervm -Dsun.awt.enableExtraMouseButtons=true SystemPropTest_2
 */
//1) Verifies that System.getProperty("sun.awt.enableExtraMouseButtons") returns true if set through the command line.
//2) Verifies that Toolkit.areExtraMouseButtonsEnabled() returns true if the proprty is set through the command line.
import java.awt.*;

public class SystemPropTest_2 {

    public static void main(String []s){
        boolean propValue = Boolean.parseBoolean(System.getProperty("sun.awt.enableExtraMouseButtons"));
        System.out.println("System.getProperty = " + propValue);
        if (!propValue){
            throw new RuntimeException("TEST FAILED : System property sun.awt.enableExtraMouseButtons = " + propValue);
        }
        if (!Toolkit.getDefaultToolkit().areExtraMouseButtonsEnabled()){
            throw new RuntimeException("TEST FAILED : Toolkit.areExtraMouseButtonsEnabled() returns false");
        }
        System.out.println("Test passed.");
    }
}
