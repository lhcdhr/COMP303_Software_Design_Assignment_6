package comp303.assignment6.functioning;

/**
 * The exception for robot violating the preconditions of
 * actions. This is to prevent AssertionError(robot being damaged).
 *
 * Haochen Liu
 *
 */
public class WrongPreconditionException extends Exception{
    WrongPreconditionException(String s){
        super(s);
    }
}
