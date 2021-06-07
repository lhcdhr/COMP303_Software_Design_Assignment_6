package comp303.assignment6.functioning;
/**
 * Visitor Design Pattern
 *
 * To show the total compacted item count for each Action/Program
 * Detailed explanation can be found in the APVisitor interface.
 *
 * Haochen Liu
 *
 */
public class VisitorCompactNum implements APVisitor {

    @Override
    public Integer moveActionVisit(MoveAction pMove) {
        return 0;
    }

    @Override
    public Integer turnActionVisit(TurnAction pTurn) {
        return 0;
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
        // For the 6 basic actions, only CompactAction will make robot compact.
        // So for the others, 0 will be returned.
        return 1;
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
        int compactCount = 0;
        for(Action a: pComplex.aActions){
            Integer temp =  (Integer)a.accept(this);
            compactCount += temp;
        }
        return compactCount;
    }

    @Override
    public Integer programVisit(Program pProgram) {
        // Visit each action stored and cumulate the result.
        int compactCount = 0;
        for(Action a: pProgram.aActions){
            Integer temp =  (Integer)a.accept(this);
            compactCount += temp;
        }
        return compactCount;
    }
}
