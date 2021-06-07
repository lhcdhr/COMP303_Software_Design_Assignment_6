package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;
/**
 * Visitor Design Pattern
 * Singleton Design Pattern
 *
 * the empty action
 *
 * Haochen Liu
 *
 */
public class EmptyAction extends Action{
    // only one EmptyAction needs to be created
    static final EmptyAction aEmpty = new EmptyAction();
    /**
     * Singleton Design Pattern
     * Private constructor to prevent client from creating duplicates.
     */
    private EmptyAction(){};
    /**
     * Get the only EmptyAction object
     * @return the only EmptyAction
     */
    public static EmptyAction empty(){
        return aEmpty;
    }

    @Override
    public void execute(Robot r) throws WrongPreconditionException {
        // only do empty when the load is greater than 0
        if(r.getCompactorLevel()>0){
            this.checkBattery(r);
            r.emptyCompactor();
            r.updateBatteryLevel();
        }
        // otherwise throw WrongPreconditionException to prevent robot from getting damaged(no AssertionError)
        else{
            throw new WrongPreconditionException("The compactor should have something in order to empty it!");
        }
    }

    @Override
    public Object accept(APVisitor visitor) {
        return visitor.emptyActionVisit(this);
    }

    @Override
    public String toString() {
        return "empty";
    }
}
