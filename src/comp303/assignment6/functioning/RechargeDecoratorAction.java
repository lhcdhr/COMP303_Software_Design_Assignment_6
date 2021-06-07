package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;

/**
 * Decorator Design Pattern
 * Visitor Design Pattern
 *
 * Haochen Liu
 *
 */
public class RechargeDecoratorAction extends ActionDecorator{


    /**
     * Constructor
     * Fill the field with the action to decorate.
     * @param a action to decorate
     */
    public RechargeDecoratorAction(Action a){
        toDecorate = a;
    }

    /**
     * Recharge then execute.
     *
     * @param r the robot that executes the action
     * @throws WrongPreconditionException
     */
    @Override
    void execute(Robot r) throws WrongPreconditionException{
        r.rechargeBattery();
        toDecorate.execute(r);
    }

    @Override
    public Object accept(APVisitor visitor) {
        return visitor.rechargeDecoratorVisit(this);
    }

    @Override
    public String toString() {
        return "recharge and " +toDecorate.toString();
    }
}
