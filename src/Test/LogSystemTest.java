import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import comp303.assignment6.robot.*;
import comp303.assignment6.functioning.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.List;
/**
 * Test for LogSystem interface, as well as class LogProgram
 *
 * Haochen Liu
 *
 */
class LogSystemTest {
    Program p = new WallEProgram("p");
    LogSystem l = new LogProgram();
    Robot r = new WallE();
    @BeforeEach
    void setUp() throws WrongPreconditionException {
        p.addLogger(l);
        p.addAction(new MoveAction(10));
        p.addAction(TurnAction.turnRight());
        p.addAction(GrabAction.grab());
        p.addAction(CompactAction.compact());
        p.addAction(EmptyAction.empty());
        p.execute(r);
    }

    @Test
    void printLogToConsole(){
        l.printLogToConsole();
        List<String> logs = l.getLogs();
        assertEquals(5,logs.size());
    }

    @Test
    void saveLogToFile() throws IOException {
        l.saveLogToFile("testLog");
        File logFile = new File("src\\comp303\\assignment6\\logs\\testLog.txt");
        //check whether the file is successfully created
        assertTrue(logFile.exists());
    }
    @Test
    void clearLog(){
        l.clearLog();
        assertEquals(0,l.getLogs().size());
    }
}