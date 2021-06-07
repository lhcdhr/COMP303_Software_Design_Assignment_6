import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test for TurnAction class
 *
 * Haochen Liu
 *
 */
class TurnActionTest {

    TurnAction l;
    TurnAction r;

    @BeforeEach
    void setup(){
        l = TurnAction.turnLeft();
        r = TurnAction.turnRight();
    }

    @Test
    void turnLeftTest() throws NoSuchFieldException, IllegalAccessException {
        Field degree = l.getClass().getDeclaredField("aDegree");
        degree.setAccessible(true);
        assertEquals(270,degree.get(l));
    }

    @Test
    void turnRightTest() throws NoSuchFieldException, IllegalAccessException {
        Field degree = r.getClass().getDeclaredField("aDegree");
        degree.setAccessible(true);
        assertEquals(90,degree.get(r));
    }

    @Test
    void executeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        try {
            Method extendArm = WallE.class.getDeclaredMethod("extendArm");
            extendArm.setAccessible(true);
            Method execute = TurnAction.class.getDeclaredMethod("execute", Robot.class);
            execute.setAccessible(true);
            // r1 for checking robot that has extracted arm before move
            Robot r1 = new WallE();
            extendArm.invoke(r1);
            execute.invoke(r, r1);
            assertEquals(Robot.ArmState.RETRACTED, r1.getArmState());
            // r2 for checking robot that has retracted arm before move
            Robot r2 = new WallE();
            execute.invoke(l,r2);
            assertEquals(Robot.ArmState.RETRACTED, r1.getArmState());
        }
        catch(AssertionError a){
            fail();
        }

    }

    @Test
    void acceptTest() {
        APVisitor visitorDistance = new VisitorDistance();
        assertEquals(0.0,l.accept(visitorDistance));
        assertEquals(0.0,r.accept(visitorDistance));
        APVisitor visitorDegree = new VisitorDegree();
        assertEquals(270,l.accept(visitorDegree));
        assertEquals(90,r.accept(visitorDegree));
        APVisitor visitorCompact = new VisitorCompactNum();
        assertEquals(0,l.accept(visitorCompact));
        assertEquals(0,r.accept(visitorCompact));
    }

    @Test
    void testToStringTest() {
        assertEquals("turn left 90 degree",l.toString());
        assertEquals("turn right 90 degree",r.toString());
    }
}