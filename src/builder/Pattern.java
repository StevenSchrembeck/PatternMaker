package builder;


/**
 * All patterns should inherit from this interface. Note that later
 * this could be improved with the use of generics instead of just
 * using integers.
 * 
 * @author Optimus Prime
 *
 */
public interface Pattern {
	
	int[][] getPatternOutput(int input);
}
