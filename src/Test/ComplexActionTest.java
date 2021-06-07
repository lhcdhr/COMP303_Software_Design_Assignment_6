import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
/**
 * Test for ComplexAction class
 *
 * Haochen Liu
 *
 */
class ComplexActionTest {
    ComplexAction c1 = new ComplexAction();
    ComplexAction c2 = new ComplexAction();
    Robot w1 = new WallE();
    Robot w2 = new WallE();
    @BeforeEach
    void setUp(){
        c1.addAction(new MoveAction(10));
        c1.addAction(TurnAction.turnRight());
        c1.addAction(GrabAction.grab());
        c1.addAction(CompactAction.compact());
        c1.addAction(EmptyAction.empty());
        c2.addAction(EmptyAction.empty());
    }

    @Test
    void addAction() {
        ComplexAction c = new ComplexAction();
        c.addAction(GrabAction.grab());
        c.addAction(1,CompactAction.compact());
        assertEquals(2,c.getActionsNum());
    }

    @Test
    void removeAction() {
        ComplexAction c = new ComplexAction();
        c.addAction(GrabAction.grab());
        c.removeAction(0);
        assertEquals(0,c.getActionsNum());
    }

    @Test
    void getActions() {
        ComplexAction c1 = new ComplexAction();
        c1.addAction(GrabAction.grab());
        c1.addAction(CompactAction.compact());
        c1.addAction(EmptyAction.empty());
        List<Action> list = c1.getActions();
        assertEquals(GrabAction.grab(),list.get(0));
        assertEquals(CompactAction.compact(),list.get(1));
        assertEquals(EmptyAction.empty(),list.get(2));
    }

    @Test
    void execute() {
        // normal run
        try{
            Program p = new WallEProgram("p");
            p.addAction(c1);
            p.execute(w1);
        }
        catch(WrongPreconditionException w){
            fail();
        }
        // situation when precondition does not met
        try{
            Program p = new WallEProgram("p");
            p.addAction(c2);
            p.execute(w2);
        }
        catch(WrongPreconditionException w){
            assertEquals("The action at index 0 does not meet the pre-condition!",w.getMessage());
        }
    }

    @Test
    void accept() {
        APVisitor visitorDistance = new VisitorDistance();
        assertEquals(10.0,c1.accept(visitorDistance));
        APVisitor visitorDegree = new VisitorDegree();
        assertEquals(90,c1.accept(visitorDegree));
        APVisitor visitorCompact = new VisitorCompactNum();
        assertEquals(1,c1.accept(visitorCompact));
    }

    @Test
    void testToString() {
        assertEquals("{move 10.0||turn right 90 degree||grab||compact||empty||} as a complex",c1.toString());
    }
}