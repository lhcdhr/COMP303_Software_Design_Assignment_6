package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;

/**
 * Visitor Design Pattern
 * Singleton Design Pattern
 *
 * the grab action
 *
 * Haochen Liu
 *
 */
public class GrabAction extends Action{
    // only one GrabAction needs to be created
    static final GrabAction aGrab = new GrabAction();

    /**
     * Singleton Design Pattern
     * Private constructor to prevent client from creating duplicates.
     */
    private GrabAction(){};
    /**
     * Singleton Design Pattern
     * Get the only instance of GrabAction.
     * @return the instance of GrabAction
     */
    public static GrabAction grab(){
        return aGrab;
    }
    @Override
    void execute(Robot r) {
        // force the robot to get to the expected state before actually executing
        if(r.getArmState()!=Robot.ArmState.RETRACTED){
            r.retractArm();
        }
        if(r.getGripperState()!=Robot.GripperState.OPEN){
            r.openGripper();
        }
        this.checkBattery(r);
        r.extendArm();
        r.closeGripper();
        r.retractArm();
        r.updateBatteryLevel();
    }

    @Override
    public Object accept(APVisitor visitor) {
        return visitor.grabActionVisit(this);
    }

    @Override
    public String toString() {
        return "grab";
    }

}
