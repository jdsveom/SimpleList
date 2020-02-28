/**
 * Name: Jeremy Sveom
 * Class ID: 182
 * Assignment #2
 * 
 * SimpleList is a class in which an array is housed and modified. This array
 * can be modified by adding, removing, and searching. The count of the array
 * is also stored in this class and can be requested. The add and remove
 * methods automatically change the size of the array to better fit the data
 * being saved.
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
	 * array increases by 50%.
	 * 
	 * @param add	the integer to be added to the list
	 */
	public void add(int add) {	
		// If the list is full, make the array 50% bigger
		if(count == list.length) {
			int temp[] = list;
			
			int newLength = 2;
			
			// If the list is already 1 index, then increasing it by 50% will
			// not get us to 2. So, we need to make sure this does not throw
			// an exception.
			if(list.length != 1) {
				// calculate the length of the list using the floor function
				newLength = (int) Math.floor(count * 1.5);
			}
			
			list = new int[newLength];
			
			// Fill in the new list with the old values
			for (int index = 0; index < count; index++) {
				list[index] = temp[index];
			}
		}
		
		// move all other entries over one index
		for(int index = count - 1; index >= 0; index--) {
			list[index + 1] = list[index];
		}
		
		list[0] = add;
		
		count ++;
		
		return;
	}
	
	/**
	 * The remove method removes the first instance of the passed integer. This
	 * method relies on the search method to find the first instance of the
	 * integer. If the list has more than 25% empty spaces, then it will
	 * decrease the size of the list until it does not.
	 * 
	 * @param remove		integer to be removed from the list
	 */
	public void remove(int remove) {
		int location = search(remove);
		
		if (location != -1) {
			list[location] = 0;
			
			// Parse through the array and move all values down one to fill in
			// the gap.
			for(int index = location; index < count - 1; index++) {
				list[index] = list[index + 1];
			}
			list[count - 1] = 0;
			count --;
		}
		
		// Calculate empty space
		int numEmptySpaces = list.length - count;
		double percentEmptySpaces = (double) numEmptySpaces / list.length;
		
		// Continue to decrease the length of the list until it has less than
		// 25% empty space.
		while ((percentEmptySpaces > 0.25) && (list.length > 1)) {
			int temp[] = list;
			
			int newLength = list.length - 1;
			
			list = new int[newLength];
			
			for (int index = 0; index < count; index++) {
				list[index] = temp[index];
			}
			
			// Recalculate empty space
			numEmptySpaces = list.length - count;
			percentEmptySpaces = (double) numEmptySpaces / list.length;
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