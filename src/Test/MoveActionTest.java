import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test for MoveAction class
 *
 * Haochen Liu
 *
 */
class MoveActionTest {
    Robot r;
    MoveAction move;
    @BeforeEach
    void setup(){
        r = new WallE();
        move = new MoveAction(10);
    }

    @Test
    void executeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // check that negative distance is not accepted
        try{
            MoveAction m = new MoveAction(-1);
        }
        catch(IllegalArgumentException i){
            assertEquals("A Robot cannot move reversely!",i.getMessage());
        }
        // check robot state
        try {
            Method extendArm = WallE.class.getDeclaredMethod("extendArm");
            extendArm.setAccessible(true);
            Method execute = MoveAction.class.getDeclaredMethod("execute", Robot.class);
            execute.setAccessible(true);
            // r1 for checking robot that has extracted arm before move
            Robot r1 = new WallE();
            MoveAction move = new MoveAction(50);
            extendArm.invoke(r1);
            execute.invoke(move, r1);
            assertEquals(Robot.ArmState.RETRACTED, r1.getArmState());
            // r2 for checking robot that has retracted arm before move
            Robot r2 = new WallE();
            execute.invoke(move,r2);
            assertEquals(Robot.ArmState.RETRACTED, r1.getArmState());
        }
        catch(AssertionError a){
            fail();
        }
    }

    @Test
    void acceptTest() {
        APVisitor visitorDistance = new VisitorDistance();
        assertEquals(10.0,move.accept(visitorDistance));
        APVisitor visitorDegree = new VisitorDegree();
        assertEquals(0,move.accept(visitorDegree));
        APVisitor visitorCompact = new VisitorCompactNum();
        assertEquals(0,move.accept(visitorCompact));
    }

    @Test
    void testToStringTest() {
        assertEquals("move 10.0",move.toString());
    }
}