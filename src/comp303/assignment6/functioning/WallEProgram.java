package comp303.assignment6.functioning;

/**
 * Visitor Design Pattern
 *
 * A concrete class for Program.
 *
 * Haochen Liu
 *
 */
public class WallEProgram extends Program{

    /**
     * Constructor.
     *
     * @param pName name of this WallEProgram
     */
    public WallEProgram(String pName) {
        super(pName);
    }

    @Override
    public Object accept(APVisitor visitor) {
        return visitor.programVisit(this);
    }
}
