import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test for EmptyAction class
 *
 * Haochen Liu
 *
 */
class EmptyActionTest {

    CompactAction compact;
    GrabAction grab;
    EmptyAction empty;

    @BeforeEach
    void setUp() {
        compact = CompactAction.compact();
        grab = GrabAction.grab();
        empty = EmptyAction.empty();
    }

    @Test
    void execute() {
        // a normal, correct execution
        try {
            Robot w1 = new WallE();
            Program p1 = new WallEProgram("empty1");
            p1.addAction(grab);
            p1.addAction(compact);
            p1.addAction(empty);
            p1.execute(w1);
        }
        catch(WrongPreconditionException w){
            fail();
        }
        // when there is noting to empty,
        // WrongPreconditionException will be thrown but robot
        // will not be damaged(no AssertionError).
        try{
            Robot w2 = new WallE();
            Program p2 = new WallEProgram("empty2");
            p2.addAction(empty);
            p2.execute(w2);
        }
        catch(WrongPreconditionException w){
            assertEquals("The compactor should have something in order to empty it!",w.getMessage());
        }

    }

    @Test
    void accept() {
        APVisitor visitorDistance = new VisitorDistance();
        assertEquals(0.0,empty.accept(visitorDistance));
        APVisitor visitorDegree = new VisitorDegree();
        assertEquals(0,empty.accept(visitorDegree));
        APVisitor visitorCompact = new VisitorCompactNum();
        assertEquals(0,empty.accept(visitorCompact));
    }

    @Test
    void testToString() {
        assertEquals("empty",empty.toString());
    }
}