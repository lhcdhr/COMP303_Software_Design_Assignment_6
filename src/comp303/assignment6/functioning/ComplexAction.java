package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;

import java.util.*;

/**
 * Visitor Design Pattern.
 *
 * Haochen Liu
 *
 */
public class ComplexAction extends Action{

    LinkedList<Action> aActions =new LinkedList<>();

    /**
     * Add an Action to this ComplexAction
     * @param a Action to Add
     */
    public void addAction(Action a){
        aActions.add(a);
    }

    /**
     * Add an Action to the designated index
     * @param index designated index
     * @param a Action to Add
     */
    public void addAction(int index, Action a){
        aActions.add(index,a);
    }

    /**
     * Remove the Action at the designated index
     * @param index index to remove from
     */
    public void removeAction(int index){
        aActions.remove(index);
    }

    /**
     * Get the total number of Actions in this ComplexAction
     * @return number of Actions
     */
    public int getActionsNum(){
        return aActions.size();
    }

    /**
     * Get a unmodifiableList of stored Actions
     * @return the unmodifiableList of Actions
     */
    public List<Action> getActions(){
        return Collections.unmodifiableList(aActions);
    }


    @Override
    void execute(Robot r) throws WrongPreconditionException{
        // execute each action
        for(int i=0;i<aActions.size();i++){
            try{
                aActions.get(i).execute(r);
            }
            // report which action caused WrongPreconditionException
            catch (WrongPreconditionException e){
                throw new WrongPreconditionException("The action at index "+ i +" does not meet the pre-condition!");
            }
        }
    }

    @Override
    public Object accept(APVisitor visitor) {
        return visitor.complexActionVisit(this);
    }

    @Override
    public String toString() {
        String toReturn = "{";
        for(Action a: aActions){
            toReturn += a.toString()+"||";
        }
        toReturn += "} as a complex";
        return toReturn;
    }
}
