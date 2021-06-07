import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test for CompactAction class
 *
 * Haochen Liu
 *
 */
class CompactActionTest {

    CompactAction compact;
    GrabAction grab;
    @BeforeEach
    void setUp() {
        compact = CompactAction.compact();
        grab = GrabAction.grab();
    }

    @Test
    void execute() {
        try{
            WallE w1 = new WallE();
            WallE w2 = new WallE();
            // p1 is a normal program
            Program p1= new WallEProgram("compactTest1");
            p1.addAction(grab);
            p1.addAction(compact);
            p1.execute(w1);
            // p2 will compact 11 times without empty in a row, which will throw
            // WrongPreconditionException, but the robot will not be damaged(no AssertionError).
            Program p2= new WallEProgram("compactTest2");
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.addAction(grab);
            p2.addAction(compact);
            p2.execute(w2);
        }
        catch(WrongPreconditionException w){
           assertEquals("The gripper must hold an object and the compactor should NOT be full before compacting!",w.getMessage());
        }
        // another situation when compacting without holding an object
        try{
            WallE w3 = new WallE();
            w3.openGripper();
            Program p3 = new WallEProgram("compactTest3");
            p3.addAction(compact);
            p3.execute(w3);
        }
        catch(WrongPreconditionException w){
            assertEquals("The gripper must hold an object and the compactor should NOT be full before compacting!",w.getMessage());
        }
    }

    @Test
    void accept() {
        APVisitor visitorDistance = new VisitorDistance();
        assertEquals(0.0,compact.accept(visitorDistance));
        APVisitor visitorDegree = new VisitorDegree();
        assertEquals(0,compact.accept(visitorDegree));
        APVisitor visitorCompact = new VisitorCompactNum();
        assertEquals(1,compact.accept(visitorCompact));
    }

    @Test
    void testToString() {
        assertEquals("compact",compact.toString());
    }
}