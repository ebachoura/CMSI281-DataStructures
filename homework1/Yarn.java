package homework1;

public class Yarn implements YarnInterface {
    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Entry[] items;
    private int size;
    private int uniqueSize;
    private final int MAX_SIZE = 100;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Yarn () {
        items = new Entry[MAX_SIZE];
        size = 0;
        uniqueSize = 0;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        return size == 0;
    }
    
    public int getSize () {
        return  size;
    }
    
    public int getUniqueSize () {
        return uniqueSize;
    }
    
    public boolean insert (String toAdd) {
        if (uniqueSize == MAX_SIZE) {
        	return false;
        } else if (this.contains(toAdd)) {
        	int i = this.index(toAdd);
            items[i].count = items[i].count + 1;
            size++;
            return true;	
        }
        
        items[uniqueSize] = new Entry(toAdd, 1);
        size++;
        uniqueSize++;
        return true;   
    }
    
    public int remove (String toRemove) {
        if (!this.contains(toRemove)) {
        	return 0;
        }
        int i = this.index(toRemove);
        int count = items[i].count;
        if (count > 1) {
        	items[i].count = count - 1;
        	size--;
        	return count - 1;
        }
        this.delete(i);
        size--;
        return 0;
    }
    
    public void removeAll (String toNuke) {
    	if (this.contains(toNuke)) {
        	int i = index(toNuke);
        	int amount = items[i].count;
        	
        	this.delete(i);
        	size -= amount;
        }
    }
    
    public int count (String toCount) {
        return contains(toCount) ? items[index(toCount)].count : 0;
    }
    
    public boolean contains (String toCheck) {
        if (this.isEmpty()) {
        	return false;
        }
        int i = this.index(toCheck);
        return !(i == -1);
    }
    
    public String getNth (int n) {
    	if (n > size) {
    		throw new ArrayIndexOutOfBoundsException();
    	}
    	
        int counter = 0;
        for (int i = 0; i < uniqueSize; i++) {
        	for (int a = 0; a < items[i].count; a++) {
        		if (n == counter) {
        			return items[i].text;
        		}
        		counter++;
        	}
        }
        return null;
    }
    
    public String getMostCommon () {
        if (isEmpty()) {
        	return null;
        }
    	
    	Entry a = new Entry("hello", 0);
        for (int i = 0; i < uniqueSize; i++) {
        	if (a.count < items[i].count) {
        		a = items[i];
        	}
        }
        return a.text;
    }
    
    public Yarn clone () {
        Yarn clone = new Yarn();
        for (int i = 0; i < uniqueSize; i++) {
        	for (int a = 0; a < items[i].count; a++) {
        		clone.insert(items[i].text);
        	}
        }
        return clone;
    }
    
    public void swap (Yarn other) {
        Entry[] tempItems = items;
        int tempSize = size;
        int tempUniqueSize = uniqueSize;
        items = other.items;
        size = other.size;
        uniqueSize = other.uniqueSize;
        other.items = tempItems;
        other.size = tempSize;
        other.uniqueSize = tempUniqueSize;       
    }
    
    public String toString() {
    	String output = "{";
    	for (int i = 0; i < uniqueSize; i++) {
    		output+= "'" + items[i].text + "':" + items[i].count;
    		if (i != uniqueSize - 1) {
    			output += ", ";
    		}
    	} output+= "}";
    	return output;
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static Yarn knit (Yarn y1, Yarn y2) {
        Yarn knittedYarn = y1.clone();
        for (int i = 0; i < y2.size; i++) {
        	knittedYarn.insert(y2.getNth(i));
        }
        return knittedYarn;
    }
    
    public static Yarn tear (Yarn y1, Yarn y2) {
        Yarn tornYarn = y1.clone();
        for (int i = 0; i < y2.uniqueSize; i++) {
        	if (tornYarn.contains(y2.items[i].text)) {
        		tornYarn.remove(y2.items[i].text);
        	}
        }
        return tornYarn;
    }
    
    public static boolean sameYarn (Yarn y1, Yarn y2) {
        if (y1.size != y2.size && y1.uniqueSize != y2.uniqueSize) {
        	return false;
        }
        
        Yarn tempYarn = y1.clone();
        for (int i = 0; i < y2.size; i++) {
        	String test = y2.getNth(i);
        	if (tempYarn.contains(test)) {        		
        		tempYarn.remove(test);
        	}
        }
        return tempYarn.isEmpty();
    }
    
    
    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    
    private int index (String toFind) {
    	for (int i = 0; i < uniqueSize; i++) {
    		if (toFind == items[i].text) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    private void delete (int index) {
    	items[index] = items[uniqueSize - 1];
    	uniqueSize--;
    }
    
}

class Entry {
    String text;
    int count;
    
    Entry (String s, int c) {
        text = s;
        count = c;
    }
}