package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;
/**
 * Visitor Design Pattern
 * Singleton Design Pattern
 *
 * the compact action
 *
 * Haochen Liu
 *
 */
public class CompactAction extends Action{
    // only one CompactAction needs to be created
    static final CompactAction aCompact=new CompactAction();
    /**
     * Singleton Design Pattern
     * Private constructor to prevent client from creating duplicates.
     */
    private CompactAction(){};

    /**
     * Get the only CompactAction object
     * @return the only CompactAction
     */
    public static CompactAction compact(){
        return aCompact;
    }

    @Override
    public void execute(Robot r) throws WrongPreconditionException {
        // only execute when states are correct
        if(r.getGripperState()==Robot.GripperState.HOLDING_OBJECT
                && r.getCompactorLevel()<10){
            this.checkBattery(r);
            r.compact();
            r.updateBatteryLevel();
        }
        // otherwise throw WrongPreconditionException to prevent robot from getting damaged(no AssertionError)
        else{
            throw new WrongPreconditionException("The gripper must hold an object and the compactor should NOT be full before compacting!");
        }
    }

    @Override
    public Object accept(APVisitor visitor) {
        return visitor.compactActionVisit(this);
    }

    @Override
    public String toString() {
        return "compact";
    }
}
