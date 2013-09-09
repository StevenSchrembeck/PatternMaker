package output;

/**
 * This output method will write all output to the console.
 * 
 * @author Optimus Prime
 *
 */
public class ConsoleWriter extends PatternWriter {

	@Override
	public void write(int[][] toWrite) {
		if(toWrite == null){
			printErrLn("Output was null. No pattern to write.");
			return;
		}
		
		//compute offset for spaces to make the columns line up
		int maxValue = getMaxValue(toWrite);
		int maxTextLength = Integer.toString(maxValue).length();
		
		for(int i = 0; i < toWrite.length; i++){
			for(int j = 0; j < toWrite[i].length; j++){
				if(toWrite[i][j] != -1){
					int bucketTextLength = Integer.toString(toWrite[i][j]).length();
					
					print(toWrite[i][j]+giveMeSpaces(maxTextLength-bucketTextLength + 1));//write the bucket number with spacing offset to the console
				}
				else{
					print(giveMeSpaces(maxTextLength+1));//-1 is a marker to print nothing
				}
			}
			printLn("");
		}
	}
}
