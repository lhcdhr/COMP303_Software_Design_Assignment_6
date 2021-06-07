import org.junit.jupiter.api.Test;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test for DecoratorAction class
 *
 * Haochen Liu
 *
 */
class DecoratorActionTest {
    GrabAction grab = GrabAction.grab();
    ActionDecorator dec = new RechargeDecoratorAction(grab);
    @Test
    void execute() throws WrongPreconditionException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Robot r1 = new WallE();
        Method execute = dec.getClass().getDeclaredMethod("execute", Robot.class);
        execute.setAccessible(true);
        execute.invoke(dec,r1);
    }

    @Test
    void accept() {
        APVisitor visitorDistance = new VisitorDistance();
        assertEquals(0.0,dec.accept(visitorDistance));
        APVisitor visitorDegree = new VisitorDegree();
        assertEquals(0,dec.accept(visitorDegree));
        APVisitor visitorCompact = new VisitorCompactNum();
        assertEquals(0,dec.accept(visitorCompact));
    }

    @Test
    void testToString() {
        assertEquals("recharge and grab",dec.toString());
    }
}