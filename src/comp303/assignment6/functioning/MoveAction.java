package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;

/**
 * Visitor Design Pattern
 * Move Action
 *
 * Haochen Liu
 *
 */
public class MoveAction extends Action{

    final double aDistance;

    /**
     * Constructor
     * By the assignment description, the robot can only move forward.
     * Then distance cannot be less than 0.
     * @param distance the distance to move forward
     */
    public MoveAction(double distance) {
        if(distance<0){
            throw new IllegalArgumentException("A Robot cannot move reversely!");
        }
        aDistance = distance;
    }

    @Override
    void execute(Robot r) throws WrongPreconditionException{
        // If the arm is not retracted before move,
        // it will be retracted because there is no other
        // ways to do so.
        if(r.getArmState()!= Robot.ArmState.RETRACTED) {
            r.retractArm();
        }
        this.checkBattery(r);
        r.moveRobot(aDistance);
        r.updateBatteryLevel();
    }

    @Override
    public Object accept(APVisitor visitor) {
        return visitor.moveActionVisit(this);
    }

    @Override
    public String toString() {
        return "move "+aDistance;
    }
}
