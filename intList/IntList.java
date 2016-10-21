package intList;

public class IntList {
	private int size;
	private int[] items;
	
	public IntList () {
		size = 0;
		items = new int[8];
	}
	
	public void append (int toAdd) {
		checkAndGrow();
		items[size] = toAdd;
		size++;
	}
	
	public int getAt (int index) {
		if (index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return items[index];
	}
	
	public void removeAt (int index) {
		shiftLeft(index);
		size--;
	}
	
	//Shift all elements to the right of an index one left
	private void shiftLeft (int index) {
		for (int i = index; i < size -1; i++) {
			items[i] = items [i + 1];
		}
	}
	
	private void checkAndGrow () {
		//Case: not at capacity, so do nothing
		if (size < items.length) {
			return;
		}
		
		//Case we're at capacity and need to grow
		int[] newItems = new int[items.length * 2];
		
		for (int i = 0; i < items.length; i++) {
			items[i] = newItems[i];
		}
		
		items = newItems;
	}
}