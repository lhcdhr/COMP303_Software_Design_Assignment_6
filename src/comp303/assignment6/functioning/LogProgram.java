package comp303.assignment6.functioning;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import comp303.assignment6.robot.*;
import java.io.*;

/**
 * Observer Design Pattern
 *
 * Concrete class that implements LogSystem interface.
 * See detailed explanation of each methods in the interface.
 * Haochen Liu
 *
 */
public class LogProgram implements LogSystem {

    private LinkedList<String> aLogs = new LinkedList<>();

    @Override
    public void generateLog(Robot r, Action a) {
        String logLine = a.toString()+" action performed, battery level is "+r.getBatteryCharge();
        aLogs.add(logLine);
    }

    @Override
    public void clearLog(){
        aLogs.clear();
    }

    @Override
    public List<String> getLogs(){
        return Collections.unmodifiableList(aLogs);
    }

    @Override
    public void printLogToConsole(){
        for(String logLine:aLogs){
            System.out.println(logLine);
        }
    }
    @Override
    public void saveLogToFile(String fileName) throws IOException {
        // log file are placed under directory src\comp303\assignment6\logs\
        File logFile = new File("src\\comp303\\assignment6\\logs\\"+fileName+".txt");
        // remove if the file already exists
        if(logFile.exists()){
            logFile.delete();
        }
        //create a new file
        logFile.createNewFile();
        logFile.setReadable(true);
        logFile.setWritable(true);
        FileWriter writer = new FileWriter(logFile);
        // write to the new file line by line
        for(String logLine:aLogs){
            writer.write(logLine+"\n");
        }
        writer.close();
    }
}
