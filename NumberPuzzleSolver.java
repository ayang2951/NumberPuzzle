package project4;

/**
 * This class displays all possible solutions to a 1 dimension number puzzle 
 * in which a player can move to the left or right the number of spaces their current position 
 * takes the value of
 * 
 * The program discovers the solutions recursively
 * 
 * @author AudreyYang
 * @version Tuesday, April 20, 2021
 *
 */
public class WayFinderUpdate {

	/**
	 * This is main, it creates the integer array that represents the number puzzle from the command line
	 * also declares and initializes many other important parameters
	 * also prints the end result for how many solutions (if any) there are
	 * 
	 * @param args -- command line arguments that represents the puzzle
	 * @throws NumberFormatException -- if one of the command line arguments is not an integer and can't be cast
	 */
	public static void main(String[] args) throws NumberFormatException {
		// if the number of command line arguments is less than 2, then we don't have a valid puzzle
		// we print an error message then exit the program
		if (args.length <= 1) {
			System.err.println("At least two numbers are expected as input.");
			System.exit(1);
		}
		
		// we create an integer array to store the values of the puzzle, and transfer them from args[] to this array
		// the length is one more than args[] because the last position stores the # of solutions 
		int[] array = new int[args.length + 1];
	    for (int i = 0; i < args.length; i++) {
	    	try {
	    		// we cast each value into an integer and add it to the integer array representing the puzzle
	    		array[i] = Integer.parseInt(args[i]);
	    		
	    		// no negative values or values over 100 are accepted -- error printed and exit program
	    		if (array[i] > 99 || array[i] < 0) {
	    			System.err.println("ERROR: the puzzle values have to be positive integers between 0 and 99.");
	    			System.exit(1);
	    		}
	    		
	        }
	    	
	    	// catch the possibility of a numberformatexception occurring -- if one of the values isn't an integer
	    	// exits the program afterwards
	        catch (NumberFormatException e) {
	            System.err.println("ERROR: the puzzle values have to be integers.");
	            System.exit(1);
	        }
	    }
		
	    // if the last value in the array is not 0
	    if (array[array.length - 2] != 0) {
	    	System.err.println("ERROR: the last value in the puzzle must be zero..");
	    	System.exit(1);
	    }
	    
	    // creates a boolean array tracker variable to track locations that have been traversed
	    // if a location is traversed, set it to false -- can't be accessed again
		boolean[] tracker = new boolean[array.length - 1];
		
		// initializes the path variable
		// sets up the tracker variable so the indicies are all legitimate
		String path = "";
		//clearTracker(tracker);
		
		// calls the method that does the recursion (finds the solutions)
		findPath(array, tracker, 0, path);
		
		// prints out the number (if any) of solutions there are
		if (array[array.length - 1] == 0) {
			System.out.println("No way through this puzzle.");
		} else {
			System.out.println("There are " + array[array.length - 1] + " ways through the puzzle.");
		}
	}
	
	/**
	 * This method does the actual work in discovering solutions
	 * First goes left, then goes right (if viable), backtracks if solution is not found
	 * variables updated/reset accordingly
	 * 
	 * @param array -- array representing the puzzle, last index = number of solutions
	 * @param tracker -- tracks traversed indices
	 * @param index -- current index
	 * @param path -- the string representing the pathway of the solution
	 */
	private static void findPath(int[] array, boolean[] tracker, int index, String path) {
		
		// creates the left moving possibility and the right moving possibility
		int left = index - array[index]; int right = index + array[index];

		// if the left index is in the array & hasn't been traversed, go there
		if (left > 0 && !tracker[index]) {
			
			// updates the pathway by adding the new move
			String lPath = path + format(array, index, 'L');
			
			// sets the index to false, explores the path, then sets it back to true
			tracker[index] = true;
			findPath(array, tracker, left, lPath);
			tracker[index] = false;
		}
		
		// if the right index is in the array & hasn't been traversed, go there
		if (right < array.length - 1 && !tracker[index]) {
			
			// this is the base case: we've reached the end of the puzzle and won!
			if (right == array.length - 2 && array[index] != 0) {
				
				// adds the last move to the path string
				String rPath = path +format(array, index, 'R');
				
				// prints out the pathway
				System.out.println(rPath);
				
				// adds 1 to the last index in array[] that stores the number of solutions since a new one is found
				array[array.length - 1] = array[array.length - 1] + 1;
				
				// stops recursion
				return;
			}
			
			// updates the pathway by adding the new move
			String rPath = path + format(array, index, 'R');
			
			// sets the index to false, explores the path, then sets it back to true
			tracker[index] = true;
			findPath(array, tracker, right, rPath);
			tracker[index] = false;
		}
	}
	
	/** 
	 * This method does the formatting for the string that represents the pathway
	 * 
	 * @param array -- this is the array representing the puzzle
	 * @param index -- current index to be added
	 * @param dir -- whether the move was to the left or right
	 * @return the string representing the line that is the new step in the path
	 */
	private static String format(int[] array, int index, char dir) {
		
		// adds the beginning bracket
		String path = "[";
		
		for (int i = 0; i < array.length - 2; i++) {
	
			// if this is the spot where the move is mad
			if (i == index) {
				// we add the correct character to represent the move
				path += String.format("%3d%s", array[i], dir + ",");
			} else {
				// just a space otherwise
				path += String.format("%3d%s", array[i], " ,");
			}
				
		}
			
		// we add the last value, then the closing bracket and a new line
		path += "  0 ]" +"\n";
		path = "[" + path.substring(2, path.length());
			
		// return the string
		return path;
	}
}
