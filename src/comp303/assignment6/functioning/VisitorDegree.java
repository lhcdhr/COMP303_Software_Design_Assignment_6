package comp303.assignment6.functioning;
/**
 * Visitor Design Pattern
 *
 * To show the turning degree for each Action/Program
 * Detailed explanation can be found in the APVisitor interface.
 *
 * Haochen Liu
 *
 */
public class VisitorDegree implements APVisitor {
    @Override
    public Integer moveActionVisit(MoveAction pMove) {
        return 0;
    }

    @Override
    public Integer turnActionVisit(TurnAction pTurn) {
        // For the 6 basic actions, only TurnAction will make robot turn.
        // So for the others, 0 will be returned.
        return pTurn.aDegree;
    }

    @Override
    public Integer grabActionVisit(GrabAction pGrab) {
        return 0;
    }

    @Override
    public Integer releaseActionVisit(ReleaseAction pRelease) {
        return 0;
    }

    @Override
    public Integer compactActionVisit(CompactAction pCompact) {
        return 0;
    }

    @Override
    public Integer emptyActionVisit(EmptyAction pEmpty) {
        return 0;
    }


    @Override
    public Integer rechargeDecoratorVisit(RechargeDecoratorAction pRecharge) {
        // Should actually visit the Action being decorated.
        return (Integer) pRecharge.toDecorate.accept(this);
    }

    @Override
    public Integer complexActionVisit(ComplexAction pComplex) {
        // Visit each action stored and cumulate the result.
        Integer currentDegree = 0;
        for(Action a:pComplex.aActions){
            Integer tempDegree = (Integer) a.accept(this);
            currentDegree += tempDegree;
        }
        return currentDegree % 360;
    }

    @Override
    public Integer programVisit(Program pProgram) {
        // Visit each action stored and cumulate the result.
        Integer currentDegree = 0;
        for(Action a: pProgram.aActions){
            Integer tempDegree = (Integer) a.accept(this);
            currentDegree += tempDegree;
        }
        return currentDegree % 360;
    }
}
