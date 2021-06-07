package comp303.assignment6.functioning;
import comp303.assignment6.robot.*;

/**
 * Visitor design pattern
 * The abstraction of all actions, including 6 basic actions,
 * 1 decorator action, and complex action.
 * Haochen Liu
 *
 */
public abstract class Action implements VisitorElement{

    /**
     * When the battery level is not greater than 5,
     * recharge the battery.
     * @param r the robot to check
     */
    public void checkBattery(Robot r){
        if(r.getBatteryCharge()<=5){
            r.rechargeBattery();
        }
    }

    /**
     * Abstract method for executing action.
     * Each action will implement this method based
     * on their own requirements.
     *
     * @param r the robot that executes the action
     * @throws WrongPreconditionException exception when the pre-condition of Robot r is not met
     */
    abstract void execute(Robot r) throws WrongPreconditionException;

    /**
     * The accept method for visitor design pattern.
     * @param visitor the APVisitor to visit
     * @return Integer, double depending on different visitors
     */
    public abstract Object accept(APVisitor visitor);

    /**
     * The toString method for each action.
     * They are implemented differently based on their content.
     * @return the string of this Action object
     */
    @Override
    public abstract String toString();
}
