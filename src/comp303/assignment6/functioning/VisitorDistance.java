package comp303.assignment6.functioning;

/**
 * Visitor Design Pattern
 *
 * To show the moving distance for each Action/Program
 * Detailed explanation can be found in the APVisitor interface.
 *
 * Haochen Liu
 *
 */
public class VisitorDistance implements APVisitor {
    @Override
    public Double moveActionVisit(MoveAction pMove) {
        // For the 6 basic actions, only MoveAction will make robot move.
        // So for the others, 0.0 will be returned.
        return pMove.aDistance;
    }

    @Override
    public Double turnActionVisit(TurnAction pTurn) {
        return 0.0;
    }

    @Override
    public Double grabActionVisit(GrabAction pGrab) {
        return 0.0;
    }

    @Override
    public Double releaseActionVisit(ReleaseAction pRelease) {
        return 0.0;
    }

    @Override
    public Double compactActionVisit(CompactAction pCompact) {
        return 0.0;
    }

    @Override
    public Double emptyActionVisit(EmptyAction pEmpty) {
        return 0.0;
    }

    @Override
    public Double rechargeDecoratorVisit(RechargeDecoratorAction pRecharge) {
        // Should actually visit the Action being decorated.
        return (Double) pRecharge.toDecorate.accept(this);
    }

    @Override
    public Double complexActionVisit(ComplexAction pComplex) {
        // Visit each action stored and cumulate the result.
        Double tDistance = 0.0;
        for(Action a:pComplex.aActions){
            Double tempDistance = (Double) a.accept(this);
            tDistance += tempDistance;
        }
        return tDistance;
    }

    @Override
    public Double programVisit(Program pProgram) {
        // Visit each action stored and cumulate the result.
        Double tDistance = 0.0;
        for(Action a: pProgram.aActions){
            Double tempDistance = (Double) a.accept(this);
            tDistance += tempDistance;
        }
        return tDistance;
    }
}
