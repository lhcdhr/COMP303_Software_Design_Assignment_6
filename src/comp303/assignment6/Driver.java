package comp303.assignment6;
import comp303.assignment6.robot.*;
import java.io.*;
import comp303.assignment6.functioning.*;

/**
 * Demonstrate how the design works
 *
 * Haochen Liu
 *
 */
public class Driver {
    public static void main(String[] args) throws WrongPreconditionException, IOException {

        Robot Walle = new WallE();
        System.out.println(Walle.getArmState());
        Program p1 = new WallEProgram("Program1");
        LogSystem ls1 = new LogProgram();
        p1.addLogger(ls1);
        p1.addAction(new MoveAction(100));
        p1.addAction(TurnAction.turnLeft());
        p1.addAction(GrabAction.grab());
        p1.addAction(CompactAction.compact());
        p1.addAction(GrabAction.grab());
        p1.addAction(CompactAction.compact());
        // create a RechargeDecoratorAction with grab decorated
        p1.addAction(new RechargeDecoratorAction(GrabAction.grab()));
        p1.addAction(CompactAction.compact());
        ComplexAction ca1 = new ComplexAction();
        ca1.addAction(GrabAction.grab());
        ca1.addAction(CompactAction.compact());
        p1.addAction(ca1);
        // create a complex action that contains move 50 and turn right
        ComplexAction ca2 = new ComplexAction();
        ca2.addAction(new MoveAction(50));
        ca2.addAction(TurnAction.turnRight());
        p1.addAction(ca2);
        p1.addAction(ca2);
        p1.addAction(ca2);
        APVisitor compact = new VisitorCompactNum();
        APVisitor distance = new VisitorDistance();
        APVisitor degree = new VisitorDegree();
        System.out.println("The calculation of related data before actual execution of the program:");
        System.out.println("The total moved distance of Program p1 is "+degree.programVisit(p1));
        System.out.println("The total turning degree of Program p1 is "+distance.programVisit(p1));
        System.out.println("The number oftotal compacted item of Program p1 is "+compact.programVisit(p1));
        System.out.println("-----------------------------Execute the program-----------------------------");
        p1.execute(Walle);
        System.out.println("-----------------------------Print log to console-----------------------------");
        ls1.printLogToConsole();
        ls1.saveLogToFile("log1");
        System.out.println("-----------------------------Show the log from file-----------------------------");
        File logFile = new File("src\\comp303\\assignment6\\logs\\log1.txt");
        BufferedReader reader = new BufferedReader(new FileReader(logFile));
        String line = reader.readLine();
        while(line != null){
            System.out.println(line);
            line = reader.readLine();
        }
        reader.close();
    }

}
