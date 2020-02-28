/**
 * Name: Jeremy Sveom
 * Class ID: 182
 * Assignment #1
 * 
 * SimpleList is a class in which a 10 index array is housed and modified. This
 * array can be modified by adding, removing, and searching. The count of the
 * array is also stored in this class and can be requested.
 * 
 * @author Jeremy Sveom
 * @version %G%
 */

package cse360assign2;

public class SimpleList {
	// private class variables to be modified by the following methods
	private int list[];
	private int count;
	
	/**
	 * Sole constructor. Initializes integer array to 10 indices and sets count
	 * to zero.
	 */
	public SimpleList() {
		list = new int[10];
		count = 0;
	}
	
	/**
	 * The add method adds the passed integer to the 'head' of the array. All
	 * other integers are moved down one index. If the list is full, then the
	 * last integer 'drops off' the list.
	 * 
	 * @param add	the integer to be added to the list
	 */
	public void add(int add) {		
		// move all other entries over one index
		for(int index = 8; index >= 0; index--) {
			list[index + 1] = list[index];
		}
		
		list[0] = add;
		
		if (count < 10) {
			count ++;
		}
		
		return;
	}
	
	/**
	 * The remove method removes the first instance of the passed integer. This
	 * method relies on the search method to find the first instance of the
	 * integer.
	 * 
	 * @param remove		integer to be removed from the list
	 */
	public void remove(int remove) {
		int location = search(remove);
		
		if (location != -1) {
			// parse through the array and move all values down one to fill in
			// the gap.
			for(int index = location; index < count - 1; index++) {
				list[index] = list[index + 1];
			}
			count --;
		}
		
		return;
	}
	
	/**
	 * The count method is a get method that returns the current number of
	 * entries in the list.
	 * 
	 * @return	the number of entries in the array
	 */
	public int count() {
		return count;
	}
	
	/**
	 * The toString method overwrites the built in Java toString method. It
	 * traverses through the list and creates a String containing all of the
	 * current entries.
	 * 
	 * @return	the list with spaces between each entry
	 */
	public String toString() {
		String listString = "";
		
		// traverse through the array and add each entry to the return String
		for(int index = 0; index < count - 1; index++) {
			listString += list[index];
			listString += " ";
		}
		
		// The final entry does not have a space after it
		if (count != 0) {
			listString += list[count - 1];
		}
		
		return listString;
	}
	
	/**
	 * The search method searches for the passed integer within the list and
	 * returns the location of that integer as an index.
	 * 
	 * @param search		the integer to be searched for in the list
	 * @return			the index at which the integer is located. Returns -1
	 * 					if the integer cannot be found.
	 */
	public int search(int search) {
		int location = -1;
		boolean found = false;
		int index = 0;
		
		// traverse through the array, checking each index for equality
		while(!found && index <= count - 1) {
			if(list[index] == search) {
				location = index;
				found = true;
			} else {
				index++;
			}
		}
		
		return location;
	}
}