package homework2;

import java.util.NoSuchElementException;

public class LinkedYarn implements LinkedYarnInterface {
	// -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Node head;
    private int size, uniqueSize, modCount;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    LinkedYarn () {
        head = null;
        size = 0;
        uniqueSize = 0;
        modCount = 0;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        return size == 0;
    }
    
    public int getSize () {
        return size;
    }
    
    public int getUniqueSize () {
        return uniqueSize;
    }
    
    public void insert (String toAdd) {
    	if (isEmpty()) {
    		head = new Node(toAdd, 1);
    		uniqueSize++;
    	} else if (!contains(toAdd)) {
    		Node i = lastNode();
    		i.next = new Node(toAdd, 1);
    		i.next.prev = i;
    		uniqueSize++;
    	} else {
    		getNode(toAdd).count++;
    	}
    	size++;
    	modCount++;
    }
    
    public int remove (String toRemove) {
        if (!contains(toRemove)) {return 0;}
        
        Node removal = getNode(toRemove);
        if (removal.count == 1) {
        	removeAll(toRemove);
        	return 0;
        } else {
        	removal.count--;
        	size--;
        	modCount++;
        	return removal.count;
        }
    }
    
    public void removeAll (String toNuke) {
    	if (!contains(toNuke)) {return;}
    	Node removal = getNode(toNuke);
    	
    	size -= removal.count;
    	uniqueSize--;
    	modCount++;
    	
    	if (removal == head) {
    		head = removal.next;
    		if (head == null) {return;}
    		head.prev = null;
    	} else if (removal.next == null) {
    		removal.prev.next = null;
    	} else {
    		removal.prev.next = removal.next;
    		removal.next.prev = removal.prev;
    	}
    }
    
    public int count (String toCount) {
    	if (!contains(toCount)){return 0;}
        return getNode(toCount).count;
    }
    
    public boolean contains (String toCheck) {
    	Node i = head;
    	while (i != null) {
    		if (i.text == toCheck) {return true;}
    		i = i.next;
    	}
    	return false;
    }
    
    public String getMostCommon () {
    	if (isEmpty()) {return null;}
    	
    	Node i = head;
    	Node mostCommon = head;
    	while (i != null) {
    		if (i.count > mostCommon.count) {mostCommon = i;}
    		i = i.next;
    	}
    	return mostCommon.text;
    }
    
    public LinkedYarn clone () {
        LinkedYarn clone = new LinkedYarn();
        
        Node i = head;
        while (i != null) {
        	for (int j = 0; j < i.count; j++) {
        		clone.insert(i.text);
        	}
        	i = i.next;
        }
        return clone;
    }
    
    public void swap (LinkedYarn other) {
    	Node copyHead = head;
    	int copySize = size;
    	int copyUniqueSize = uniqueSize;
    	
    	head = other.head;
    	size = other.size;
    	uniqueSize = other.uniqueSize;
    	modCount++;
    	
    	other.head = copyHead;
    	other.size = copySize;
    	other.uniqueSize = copyUniqueSize;
    	other.modCount++;
    }
    
    public LinkedYarn.Iterator getIterator () {
        if (isEmpty()) { throw new IllegalStateException();}
    	return new Iterator(this);
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
    	LinkedYarn knittedYarn = y1.clone();
    	Node i = y2.head;
        while (i != null) {
        	for (int j = 0; j < i.count; j++) {
        		knittedYarn.insert(i.text);
        	}
        	i = i.next;
        }
        return knittedYarn;
    }
    
    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn tornYarn = y1.clone();
        Node i = y2.head;
        while (i != null) {
        	for (int j = 0; j < i.count; j++) {
        		tornYarn.remove(i.text);
        	}
        	i = i.next;
        }
        return tornYarn;
    }
    
    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {
    	return tear(y1, y2).isEmpty() && tear(y2, y1).isEmpty();
    }
    
    
    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    private Node getNode(String theHunt) {
    	Node i = head;
    	while (i != null) {
    		if (i.text == theHunt) {return i;}
    		i = i.next;
    	}
    	return null;
    }
    
    private Node lastNode() {
    	Node i = head;
    	while (i != null) {
    		if (i.next == null) {return i;}
    		i = i.next;
    	}
    	return null;
    }
    
    public String toString() {
    	String a = "{";
    	
    	Node i = head;
    	while (i != null) {
    		a += i.text + ": " + i.count;
    		if (i.next != null) {
    			a += ", ";
    		}
    		i = i.next;
    	}
    	
    	return a += "}";
    }
    
    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        int itModCount;
        int countWithinNode;
        
        Iterator (LinkedYarn y) {
            owner = y;
            current = y.head;;
            itModCount = y.modCount;
            countWithinNode = 1;
        }
        
        public boolean hasNext () {
        	if (!isValid()) {return false;}
            return !(countWithinNode == current.count && (current.next == null));
        }
        
        public boolean hasPrev () {
        	if (!isValid()) {return false;}
        	return !(countWithinNode == 1 && (current == head));
        }
        
        public boolean isValid () {
            return itModCount == owner.modCount;
        }
        
        public String getString () {
            if (!isValid()) {return null;}
        	return current.text;
        }

        public void next () {
            if (!isValid()) {throw new IllegalStateException();}
            if (countWithinNode != current.count) {countWithinNode++; return;}
            if (hasNext()) {
            	countWithinNode = 1;
            	current = current.next;
            	return;
            }
            throw new NoSuchElementException();
        }
        
        public void prev () {
        	if (!isValid()) {throw new IllegalStateException();}
            if (countWithinNode != 1) {countWithinNode--; return;}
            if (hasPrev()) {
            	current = current.prev;
            	countWithinNode = current.count;
            	return;
            }
            throw new NoSuchElementException();
        }
        
        public void replaceAll (String toReplaceWith) {
            if (!isValid()) {throw new IllegalStateException();}
            current.text = toReplaceWith;
            owner.modCount++;
            itModCount++;
        }
    }
    
    class Node {
        Node next, prev;
        String text;
        int count;
        
        Node (String t, int c) {
            text = t;
            count = c;
        }
    }
    
}