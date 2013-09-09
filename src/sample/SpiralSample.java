package sample;

import builder.ClockwiseSpiralPattern;
import builder.PatternMaker;

public class SpiralSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PatternMaker patternMaker = new PatternMaker();
		
		patternMaker.printPattern(new ClockwiseSpiralPattern(), 112, PatternMaker.OutputType.File);
		patternMaker.printPattern(new ClockwiseSpiralPattern(), 112, PatternMaker.OutputType.Console);
		
	}

}
