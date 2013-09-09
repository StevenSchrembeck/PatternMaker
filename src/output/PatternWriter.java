package output;

/**
 * General interface for all output methods.
 * 
 * @author Optimus Prime
 *
 */
public abstract class PatternWriter {
	public abstract void write(int[][] toWrite);
	
	/**
	 * Shorthand helper methods
	 */
	protected void print(String toPrint){
		System.out.print(toPrint);
	}
	protected void printLn(String toPrint){
		System.out.println(toPrint);
	}
	protected void printErrLn(String toPrint){
		System.err.println(toPrint);
	}
	protected String giveMeSpaces(int count){
		String toReturn="";
		for(int i=0; i < count; i++){
			toReturn += " ";
		}
		return toReturn;
	}
	protected int getMaxValue(int[][] toTest){//only works for positive numbers
		int maxValue = 0;
		for(int i = 0; i < toTest.length; i++){
			for(int j = 0; j < toTest[i].length; j++){
				if(toTest[i][j] > maxValue){
					maxValue = toTest[i][j];
				}
			}
		}
		
		return maxValue;
	}
}
