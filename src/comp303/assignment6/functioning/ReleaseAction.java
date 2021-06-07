package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;

/**
 * Visitor Design Pattern
 * Singleton Design Pattern
 *
 * the release action
 *
 * Haochen Liu
 *
 */
public class ReleaseAction extends Action{
    // only one ReleaseAction needs to be created
    static final ReleaseAction aRelease = new ReleaseAction();
    /**
     * Singleton Design Pattern
     * Private constructor to prevent client from creating duplicates.
     */
    private ReleaseAction(){};

    /**
     * Singleton Design Pattern
     * Get the only instance of ReleaseAction.
     * @return the instance of ReleaseAction
     */
    public static ReleaseAction release(){
        return aRelease;
    }

    @Override
    void execute(Robot r) {
        // force the robot to get to the expected state before actually executing
        if(r.getGripperState()==Robot.GripperState.OPEN){
            r.closeGripper();
        }
        if(r.getArmState()!=Robot.ArmState.RETRACTED){
            r.retractArm();
        }
        this.checkBattery(r);
        r.openGripper();
        r.updateBatteryLevel();
    }

    @Override
    public Object accept(APVisitor visitor) {
        return visitor.releaseActionVisit(this);
    }

    @Override
    public String toString() {
        return "release";
    }

}
