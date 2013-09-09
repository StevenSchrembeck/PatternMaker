package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class PatternFileWriter extends PatternWriter {
	
	final String OUTPUT_FILE = "output/spiralPattern.txt";
	
	@Override
	public void write(int[][] toWrite) {
		if(toWrite == null){
			printErrLn("Output was null. No pattern to write.");
			return;
		}
		
		
		Writer out = null;
		try {
			out = new BufferedWriter(new FileWriter(OUTPUT_FILE));
		
			//compute offset for spaces to make the columns line up
			int maxValue = getMaxValue(toWrite);
			int maxTextLength = Integer.toString(maxValue).length();
			
			for(int i = 0; i < toWrite.length; i++){
				for(int j = 0; j < toWrite[i].length; j++){
					if(toWrite[i][j] != -1){
						int bucketTextLength = Integer.toString(toWrite[i][j]).length();
						
						out.write(toWrite[i][j]+giveMeSpaces(maxTextLength-bucketTextLength + 1));//write the bucket number with spacing offset to the console
					}
					else{
						out.write(giveMeSpaces(maxTextLength+1));//-1 is a marker to print nothing
					}
				}
				out.write("\n");
			}
			out.flush();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
