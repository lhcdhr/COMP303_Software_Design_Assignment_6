package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;

/**
 * Visitor Design Pattern
 * Flyweight Design Pattern
 *
 * The turn action.
 * Haochen Liu
 *
 */
public class TurnAction extends Action{

    final Integer aDegree;
    // Since the robot can only turn left or right 90 degree,
    // Only two different TurnAction need to be created.
    static final TurnAction turnLeft = new TurnAction("left");
    static final TurnAction turnRight = new TurnAction("right");

    /**
     * Private constructor to prevent client from creating
     * extra unnecessary objects.
     * The reason I set turn left as turn 270 degree
     * is that I want to limit the cumulative degree
     * to stay within 0 to 360 degree.
     *
     * @param direction left or right
     */
    private TurnAction(String direction) {
        if(direction.equals("left")){
            aDegree =270;
        }
        else if(direction.equals("right")){
            aDegree=90;
        }
        else{
            throw new IllegalArgumentException("Only \"left\" or \"right\" is accepted!");
        }
    }

    /**
     * Get the turn-left action
     * @return TurnAction that turns left
     */
    public static TurnAction turnLeft(){
        return turnLeft;
    }

    /**
     * Get the turn-right action
     * @return TurnAction that turns right
     */
    public static TurnAction turnRight(){
        return turnRight;
    }

    @Override
    void execute(Robot r) {
        // If the arm is not retracted before move,
        // it will be retracted because there is no other
        // ways to do so.
        if(r.getArmState()!= Robot.ArmState.RETRACTED){
            r.retractArm();
        }
        this.checkBattery(r);
        r.turnRobot(aDegree);
        r.updateBatteryLevel();
    }

    @Override
    public Object accept(APVisitor visitor) {
        return visitor.turnActionVisit(this);
    }

    @Override
    public String toString() {
        if(aDegree.equals(90)){
            return "turn right 90 degree";
        }
        else if(aDegree.equals(270)){
            return "turn left 90 degree";
        }
        return "";

    }
}
