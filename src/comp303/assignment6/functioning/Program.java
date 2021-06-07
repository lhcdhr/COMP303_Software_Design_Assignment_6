package comp303.assignment6.functioning;

import comp303.assignment6.robot.*;

import java.util.*;

/**
 * Visitor Design Pattern
 * Observer Design Pattern
 *
 * LogSystem acts as observers,
 * And visitors are used to calculate the
 * total moving distance, turning degree,
 * and number of compacted items.
 *
 * Haochen Liu
 *
 */
public abstract class Program implements VisitorElement{

    LinkedList<Action> aActions = new LinkedList<Action>();
    private final String aName;
    //loggers(observers)
    LinkedList<LogSystem> aLoggers = new LinkedList<>();

    /**
     * Constructor.
     *
     * @param pName name of this program
     */
    public Program(String pName){
        aName = pName;
    }

    /**
     * Add an Action to the Program
     *
     * @param toAdd Action to add
     */
    public void addAction(Action toAdd){
        aActions.add(toAdd);
    }
    /**
     * Add an Action to the designated index
     *
     * @param index designated index
     * @param toAdd Action to Add
     */
    public void addAction(int index, Action toAdd){
        aActions.add(index, toAdd);
    }

    /**
     * Remove the Action at the designated index
     * @param index index to remove from
     */
    public void removeAction(int index){
        aActions.remove(index);
    }

    /**
     * Get the total number of Actions in this Program
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

    /**
     * Execute the Actions in Program in the given order,
     * and update the observers.
     *
     * @param r the robot to execute the program
     * @throws WrongPreconditionException
     */
    public void execute(Robot r)throws WrongPreconditionException{
        for(Action a: aActions){
            a.execute(r);
            for(LogSystem logger: aLoggers){
                logger.generateLog(r,a);
            }
        }
    }

    /**
     * Add an observer(LogSystem)
     * @param logger the observer to add
     */
    public void addLogger(LogSystem logger){
        aLoggers.add(logger);
    }

    /**
     * Get a unmodifiableList of all observers(LogSystems)
     *
     * @return the unmodifiableList that contains all observers
     */
    public List<LogSystem> getLoggers(){
        return Collections.unmodifiableList(aLoggers);
    }

    /**
     * Remove an observer from this Program
     *
     * @param logger the observer to remove
     */
    public void removeLogger(LogSystem logger){
        aLoggers.remove(logger);
    }

    /**
     * The accept method for Visitor Design Pattern.
     * @param aVisitor the APVisitor to visit
     * @return Integer, double depending on different visitors
     */
    @Override
    public abstract Object accept(APVisitor aVisitor);
}
