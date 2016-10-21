package homework1;

import static org.junit.Assert.assertTrue;

public interface YarnInterface {
	public static void main (String[] args) {
		
	}
	
	
    boolean isEmpty ();
    int getSize ();
    int getUniqueSize ();
    boolean insert (String toAdd);
    int remove (String toRemove);
    int count (String toCount);
    void removeAll (String toNuke);
    boolean contains (String toCheck);
    String getNth (int n);
    String getMostCommon ();
    Yarn clone();
    void swap (Yarn other);
    
}
