import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import java.lang.reflect.Field;

/**
 * Supplemental tests for abstract class Program.
 *
 * Haochen Liu
 *
 */
class ActionTest {

    @Test
    void checkBattery() throws NoSuchFieldException, IllegalAccessException, WrongPreconditionException {
        Robot r1 = new WallE();
        Field battery = r1.getClass().getDeclaredField("charge");
        battery.setAccessible(true);
        battery.set(r1,1);
        System.out.println(r1.getBatteryCharge());
        Action grab = GrabAction.grab();
        Program p = new WallEProgram("p");
        p.addAction(grab);
        p.execute(r1);
        assertTrue(r1.getBatteryCharge()>=95);
    }
}