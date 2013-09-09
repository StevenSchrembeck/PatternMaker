package builder;

import output.ConsoleWriter;
import output.PatternFileWriter;
import output.PatternWriter;

/**
 * This class combines input, patterns, and output in order to print a pattern.
 * 
 * @author Optimus Prime
 *
 */
public class PatternMaker {
	
	public enum OutputType{
		Console, File
	}
	
	/**
	 * Pass in any Pattern instance, an integer, and an OutputType and
	 * PatternMaker will output the pattern results for you.
	 * 
	 * @param pattern An instance of Pattern. Cannot be null.
	 * @param input An integer. Patterns may place restrictions on valid range.
	 * @param outputType An OutputType.
	 */
	public void printPattern(Pattern pattern, int input, OutputType outputType) throws IllegalArgumentException{
		if(pattern == null){
			throw new IllegalArgumentException("Pattern cannot be null.");
		}
			
		int[][] patternResult = pattern.getPatternOutput(input);//run the pattern and harvest the output
		
		PatternWriter output;
		switch(outputType){			
			case File:
				output = new PatternFileWriter();
				break;
				
			case Console://handle this as default, don't place more cases below here
			default:
				output = new ConsoleWriter();
		}
		output.write(patternResult);//write the output
	}
}
