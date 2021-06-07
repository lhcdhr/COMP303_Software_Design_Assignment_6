import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test for ReleaseAction class
 *
 * Haochen Liu
 *
 */
class ReleaseActionTest {
    ReleaseAction release;
    @BeforeEach
    void setUp() {
        release = ReleaseAction.release();
    }

    @Test
    void executeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method execute = ReleaseAction.class.getDeclaredMethod("execute", Robot.class);
            execute.setAccessible(true);
            Robot r1 = new WallE();
            r1.openGripper();
            r1.extendArm();
            // Now r1 has extended arm and closed gripper, the execution would force it
            // to retract arm and close the gripper before actually executing.
            execute.invoke(release, r1);
            //check finish state
            assertEquals(Robot.ArmState.RETRACTED, r1.getArmState());
            assertEquals(Robot.GripperState.OPEN,r1.getGripperState());
    }

    @Test
    void accept() {
        APVisitor visitorDistance = new VisitorDistance();
        assertEquals(0.0,release.accept(visitorDistance));
        APVisitor visitorDegree = new VisitorDegree();
        assertEquals(0,release.accept(visitorDegree));
        APVisitor visitorCompact = new VisitorCompactNum();
        assertEquals(0,release.accept(visitorCompact));
    }

    @Test
    void testToString() {
        assertEquals("release",release.toString());
    }
}