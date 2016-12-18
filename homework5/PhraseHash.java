package homework5;

import java.util.LinkedList;

public class PhraseHash implements PhraseHashInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    
    private final static int BUCKET_COUNT = 1000;
    private int size, longest;
    private LinkedList<String>[] buckets;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    
    @SuppressWarnings("unchecked") // Don't worry about this >_>
    PhraseHash () {
        buckets = new LinkedList[BUCKET_COUNT];
        size = 0;
        longest = 0;
    }
    
    
    // -----------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------
    
    public int size () {
        return size;
    }
    
    public boolean isEmpty () {
        return size == 0;
    }
    
    public void put (String s) {
    	if (s.equals("")) { return; }
    	
        if (s.split("\\s+").length > longest) { longest = s.split("\\s+").length; }
    	if (buckets[hash(s)] == null) { buckets[hash(s)] = new LinkedList<String>(); }
        buckets[hash(s)].add(s);
        size++;
    }
    
    public String get (String s) {
        LinkedList<String> hashList = buckets[hash(s)];
        if (hashList == null) { return null; }
        if (hashList.contains(s)) { return s; }
        return null;
    }
    
    public int longestLength () {
        return longest;
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private int hash (String s) {
        int hash = 7;
        for (int i = 0; i < s.length(); i++) {
        	hash = hash*31 + s.charAt(i);
        }
        return Math.abs(hash%BUCKET_COUNT);
    }
}
