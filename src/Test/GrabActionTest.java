import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test for GrabAction class
 *
 * Haochen Liu
 *
 */
class GrabActionTest {
    GrabAction grab;

    @BeforeEach
    void setup(){
        grab = GrabAction.grab();
    }
    @Test
    void grabTest() {
        assertEquals(grab,GrabAction.grab());
    }

    @Test
    void executeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try {
            Method extendArm = WallE.class.getDeclaredMethod("extendArm");
            extendArm.setAccessible(true);
            Method openGripper = WallE.class.getDeclaredMethod("openGripper");
            openGripper.setAccessible(true);
            Method execute = GrabAction.class.getDeclaredMethod("execute", Robot.class);
            execute.setAccessible(true);
            Robot r1 = new WallE();
            extendArm.invoke(r1);
            // Now r1 has extended arm and open gripper, the execution would force it
            // to retract arm and close the gripper before actually executing.
            execute.invoke(grab, r1);
            //check finish state
            assertEquals(Robot.ArmState.RETRACTED, r1.getArmState());
            assertEquals(Robot.GripperState.HOLDING_OBJECT,r1.getGripperState());
        }
        catch(AssertionError a){
            fail();
        }
    }

    @Test
    void acceptTest() {
        APVisitor visitorDistance = new VisitorDistance();
        assertEquals(0.0,grab.accept(visitorDistance));
        APVisitor visitorDegree = new VisitorDegree();
        assertEquals(0,grab.accept(visitorDegree));
        APVisitor visitorCompact = new VisitorCompactNum();
        assertEquals(0,grab.accept(visitorCompact));
    }

    @Test
    void testToStringTest() {
        assertEquals("grab",grab.toString());
    }
}