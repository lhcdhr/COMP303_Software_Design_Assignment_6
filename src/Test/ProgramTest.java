import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test for Program class as well as WallEProgram class
 *
 * Haochen Liu
 *
 */
class ProgramTest {
    Program p1 = new WallEProgram("p1");
    Program p2 = new WallEProgram("p2");
    Robot w1 = new WallE();
    Robot w2 = new WallE();
    LogSystem logger1 = new LogProgram();
    LogSystem logger2 = new LogProgram();

    @BeforeEach
    void setUp(){
        p1.addAction(new MoveAction(10));
        p1.addAction(TurnAction.turnRight());
        p1.addAction(GrabAction.grab());
        p1.addAction(CompactAction.compact());
        p1.addAction(EmptyAction.empty());
        p2.addAction(EmptyAction.empty());
        p1.addLogger(logger1);
    }
    @Test
    void addAction() {
        Program p = new WallEProgram("p");
        p.addAction(GrabAction.grab());
        p.addAction(1,CompactAction.compact());
        assertEquals(2,p.getActionsNum());
    }

    @Test
    void removeAction() {
        Program p = new WallEProgram("p");
        p.addAction(GrabAction.grab());
        p.removeAction(0);
        assertEquals(0,p.getActionsNum());
    }

    @Test
    void getActions() {
        List<Action> list=p2.getActions();
        assertEquals(EmptyAction.empty(),list.get(0));
    }

    @Test
    void execute() {
        // normal run
        try{
            p1.execute(w1);
        }
        catch(WrongPreconditionException w){
            fail();
        }
        // check when preconditions does not meet
        try{
            p2.execute(w2);
        }
        // WrongPreconditionException will be thrown to avoid damaging the robot(AssertionError)
        catch(WrongPreconditionException w){
            assertEquals("The compactor should have something in order to empty it!",w.getMessage());
        }
    }
    @Test
    void getActionNum(){
        assertEquals(5,p1.getActionsNum());
    }

    @Test
    void addLogger() {
        Program p = new WallEProgram("p");
        p.addLogger(logger1);
        p.addLogger(logger2);
        List<LogSystem> list = p.getLoggers();
        assertEquals(logger1,list.get(0));
        assertEquals(logger2,list.get(1));
    }

    @Test
    void removeLogger() {
        Program p = new WallEProgram("p");
        p.addLogger(logger1);
        p.removeLogger(logger1);
        assertEquals(0,p.getLoggers().size());
    }

    @Test
    void accept() {
        APVisitor visitorDistance = new VisitorDistance();
        assertEquals(10.0,p1.accept(visitorDistance));
        APVisitor visitorDegree = new VisitorDegree();
        assertEquals(90,p1.accept(visitorDegree));
        APVisitor visitorCompact = new VisitorCompactNum();
        assertEquals(1,p1.accept(visitorCompact));
    }
}