package comp303.assignment6.functioning;

/**
 * Visitor Design Pattern
 *
 * Interface for visitors in Visitor Design Pattern
 *
 * Haochen Liu
 *
 */
public interface APVisitor<T> {
    /**
     * When visiting the moveAction,
     * return Integer, Double determined by different Visitors
     * @param pMove the MoveAction to visit
     * @return Integer, Double determined by different Visitors
     */
    T moveActionVisit(MoveAction pMove);

    /**
     * When visiting the TurnAction,
     * return Integer, Double determined by different Visitors
     * @param pTurn the TurnAction to visit
     * @return Integer, Double determined by different Visitors
     */
    T turnActionVisit(TurnAction pTurn);

    /**
     * When visiting the GrabAction,
     * return Integer, Double determined by different Visitors
     * @param pGrab the GrabAction to visit
     * @return Integer, Double determined by different Visitors
     */
    T grabActionVisit(GrabAction pGrab);

    /**
     * When visiting the ReleaseAction,
     * return Integer, Double determined by different Visitors
     * @param pRelease the ReleaseAction to visit
     * @return Integer, Double determined by different Visitors
     */
    T releaseActionVisit(ReleaseAction pRelease);

    /**
     * When visiting the CompactAction,
     * return Integer, Double determined by different Visitors
     * @param pCompact the CompactAction to visit
     * @return Integer, Double determined by different Visitors
     */
    T compactActionVisit(CompactAction pCompact);

    /**
     * When visiting the EmptyAction,
     * return Integer, Double determined by different Visitors
     * @param pEmpty the EmptyAction to visit
     * @return Integer, Double determined by different Visitors
     */
    T emptyActionVisit(EmptyAction pEmpty);

    /**
     * When visiting the ComplexAction,
     * return Integer, Double determined by different Visitors
     * @param pComplex the ComplexAction to visit
     * @return Integer, Double determined by different Visitors
     */
    T complexActionVisit(ComplexAction pComplex);

    /**
     * When visiting the RechargeDecoratorAction,
     * return Integer, Double determined by different Visitors
     * @param pRecharge the RechargeDecoratorAction to visit
     * @return Integer, Double determined by different Visitors
     */
    T rechargeDecoratorVisit(RechargeDecoratorAction pRecharge);

    /**
     * When visiting a Program,
     * return Integer, Double determined by different Visitors
     * @param pProgram the Program to visit
     * @return Integer, Double determined by different Visitors
     */
    T programVisit(Program pProgram);
}
