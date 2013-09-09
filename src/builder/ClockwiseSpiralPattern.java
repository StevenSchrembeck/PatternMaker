package builder;

import java.util.ArrayList;
import java.util.List;


/**
 * This pattern takes a positive integer, or a list of integers, and
 * uses the numbers to draw a clockwise spiral, which is returned in
 * a 2d array of integers.
 * 
 * @author Optimus Prime
 *
 */
public class ClockwiseSpiralPattern implements Pattern{
	
	private enum DirectionValue{//not currently used, but useful for debugging
		Right, Up, Down, Left
	}
	/**
	 * Inner class used to keep track of the direction the spiral pattern is recording in.
	 * 
	 * @author Optimus Prime
	 *
	 */
	private class Direction {
		Direction next;
		@SuppressWarnings("unused")
		Direction prev; //could be used in a Counterclockwise option
		DirectionValue dirVal;//the semantic version of the direction (E.g. "right")
		Point coordVal;//the coordinate direction (E.g. (1,0)), so basically a 2d vector
		
		public Direction(DirectionValue dirVal, Point coordVal){
			this.dirVal = dirVal;
			this.coordVal = coordVal;
		}
		
		@Override
		public String toString(){
			return dirVal.toString();
		}
	}
	
	
	/**
	 * This Pattern has an overloaded getPatternOutput to allow you to
	 * use a single number as the seed. In this case the numbers from
	 * 0 to your input will be used in the pattern.
	 * 
	 * @param input A positive integer.
	 * @return A 2D array containing the resulting pattern.
	 */
	@Override
	public int[][] getPatternOutput(int input) throws IllegalArgumentException{
		if(input < 0){
			throw new IllegalArgumentException("Input integer must be positive.");
		}
		
		//this algorithm works by plotting each point on the spiral in 2d space then pushing all the points into a 2d array at the end
		//an advantage here is that you can easily use this point list to draw the numbers in coordinate space as a different output method
		
		List<Point> spiralPoints = new ArrayList<Point>(input + 1);//create a list to hold each point, it doesn't need to be bigger than the input + 1
		
		//Here we create a little linked list to keep track of which direction the spiral is currently drawing in
		Direction right = new Direction(DirectionValue.Right, new Point(1,0));
		Direction down = new Direction(DirectionValue.Down, new Point(0,-1));
		Direction left = new Direction(DirectionValue.Left, new Point(-1, 0));
		Direction up = new Direction(DirectionValue.Up, new Point(0, 1));
		//R -> D -> L -> U -> R ... etc.
		right.next = down;
		right.prev = up;
		down.prev = right;
		down.next = left;
		left.prev = down;
		left.next = up;
		up.prev = left;
		up.next = right;
		
		Point center = new Point(0,0);//we start at (0,0)
		spiralPoints.add(center);
		Point pos = new Point(0,0);//the current recording position
		
		int sideLength = 1;//current spiral side length
		int bucketCount = 1;//total number of buckets recorded
		int sideCount = 1;//total number of sides recorded
		Direction dir = up;//the current direction
		
		int minX = 0;//keep track of the smallest and largest positions because that will determine our 2d array size
		int minY = 0;
		int maxX = 0;
		int maxY = 0;
		
		boolean notComplete = true;
		while(notComplete){//each iteration here represents a side being recorded
			for(int i=0; i < sideLength; i++){//each iteration here represents a bucket being recorded
				if(bucketCount >= (input+1)){//break out when we're done recording
					notComplete = false;
					break;
				}
				
				//record the new mins and maxes if they exist
				if(pos.x < minX){
					minX = pos.x;
				}
				else if(pos.x > maxX){
					maxX = pos.x;
				}
				if(pos.y < minY){
					minY = pos.y;
				}
				else if(pos.y > maxY){
					maxY = pos.y;
				}
				pos.x += dir.coordVal.x;//move the recording cursor in the current direction
				pos.y += dir.coordVal.y;	
				spiralPoints.add(new Point(pos.x, pos.y));//record a point in the current position			
				bucketCount++;
			}
			
			if(isEven(sideCount)){
				sideLength++;//side length increases every other time a side is recorded
			}
			dir = dir.next;//direction changes every time a side is recorded
			sideCount++;			
		}
		
		int width = maxX-minX;
		int height = maxY-minY;
		int[][] result = new int[width+1][height+1];
		for(int i = 0; i < result.length; i++){
			for(int j = 0; j < result[i].length; j++){
				result[i][j] = -1;//-1 is a marker to print nothing
			}
		}
		
		
		int resultCount=0;
		for(Point spiralPoint: spiralPoints){
			result[spiralPoint.x + Math.abs(minX)][spiralPoint.y + Math.abs(minY)] = resultCount;//offet the positions and put them in a 2d array
			resultCount++;
		}
		
		return result;
	}
	
	
	
	/**
	 * Helper method to test if an integer is even.
	 */
	private boolean isEven(int toTest){
		return (toTest % 2 == 0);
	}

}
