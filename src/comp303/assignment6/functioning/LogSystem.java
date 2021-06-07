package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;

import java.io.IOException;
import java.util.List;

/**
 * Observer Design Pattern.
 * LogSystem is a interface for logs.
 * Work with Program as an observer.
 * User can create another class that implements
 * LogSystem for more purposes.
 *
 * Haochen Liu
 *
 */
public interface LogSystem {
    /**
     * Generate log based on the input
     * @param r robot to generate log from
     * @param a Action to generate log from
     */
    public void generateLog(Robot r, Action a);

    /**
     * Clear the currently stored logs
     */
    public void clearLog();

    /**
     * Get an unmodifiableList of all currently stored logs
     * @return an unmodifiableList of logs.
     */
    public List<String> getLogs();

    /**
     * The print-to-console requirement
     * The currently-stored logs will be
     * printed to console one by one.
     */
    public void printLogToConsole();

    /**
     * Save the log to the designated address(see details in class LogProgram)
     * @param fileName user designated fileName
     * @throws IOException when having difficulties creating and write to the file
     */
    public void saveLogToFile(String fileName) throws IOException;
}
