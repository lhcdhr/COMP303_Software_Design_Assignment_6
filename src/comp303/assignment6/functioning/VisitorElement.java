package comp303.assignment6.functioning;

/**
 * Visitor Design Pattern
 * Interface for elements in Visitor Design Pattern
 * Haochen Liu
 *
 */
public interface VisitorElement {
    /**
     * Accept the visit of the visitor
     *
     * @param aVisitor the APVisitor object
     * @return Integer, Double determined by different elements and different visitors
     */
    public Object accept(APVisitor aVisitor);
}
