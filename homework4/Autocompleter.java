package homework4;

import java.util.ArrayList;

public class Autocompleter implements AutocompleterInterface {

	public static void main (String[]args) {
		Autocompleter a = new Autocompleter();
		a.addTerm("ab");
		a.addTerm("about");
		a.addTerm("abort");
		System.out.println(a.hasTerm("it"));
		System.out.println(a.getSuggestedTerm("abortting"));
	}
    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    TTNode root;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Autocompleter () {
        root = null;
    }  
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    
    public boolean isEmpty () {
        return root == null;
    }
    
    public void addTerm (String toAdd) {
    	toAdd = normalizeTerm(toAdd);
    	if (isEmpty()) { root = new TTNode(toAdd.charAt(0), (1 == toAdd.length()) ? true : false); }
    	addLetter(root, toAdd, 0);
    }
    
    public boolean hasTerm (String query) {
        query = normalizeTerm(query);
/*here*/        if (isEmpty()) { return false; }
        return search(root, query, 0);
    }
    
    public String getSuggestedTerm (String query) {
        query = normalizeTerm(query);
        if (getNode(root, query, 0) == null) { return null; }
        TTNode queryNode = getNode(root, query, 0);
        if (queryNode.wordEnd == true) { return query; }
        return nearestTerm(queryNode.mid, query);
    }
    
    public ArrayList<String> getSortedTerms () {
    	return orderList(root, "", new ArrayList<String>());
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private String normalizeTerm (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }
    
    /*
     * Returns:
     *   int less than 0 if c1 is alphabetically less than c2
     *   0 if c1 is equal to c2
     *   int greater than 0 if c1 is alphabetically greater than c2
     */
    private static int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }
    
    // [!] Add your own helper methods here!
    
    private static void addLetter (TTNode current, String toAdd, int i) {
    	if (toAdd.length() == 1) {
    		int location = compareChars(current.letter, toAdd.charAt(i));
    		if (location == 0) { current.wordEnd = true; return; }
    		else if (location < 0) {
    			if (current.right == null) { current.right = new TTNode(toAdd.charAt(0), true); return; }
    			addLetter(current.right, toAdd, i);
    		} else {
    			if (current.left == null) { current.left = new TTNode(toAdd.charAt(0), true); return; }
    			addLetter(current.left, toAdd, i);
    		} 		
    	}
    	
    	int location = compareChars(current.letter, toAdd.charAt(i));
/*here*/    	if (i == toAdd.length() - 1 && location == 0) { current.wordEnd = true; return; }
    	if (location == 0) {
    		if (current.mid == null) { current.mid = new TTNode(toAdd.charAt(i + 1), (i + 1 >= toAdd.length() - 1) ? true : false); }
    		addLetter(current.mid, toAdd, i + 1);
    	} else if (location < 0) {
    		if (current.right == null) { current.right =  new TTNode(toAdd.charAt(i), (i >= toAdd.length() - 1) ? true : false); }
    		addLetter(current.right, toAdd, i);
    	} else if (location > 0) {
    		if (current.left == null) { current.left =  new TTNode(toAdd.charAt(i), (i >= toAdd.length() - 1) ? true : false); }
    		addLetter(current.left, toAdd, i);
    	}
    }
    
    private static Boolean search (TTNode current, String query, int i) {
    	if (query.length() - 1 == i) {
    		int location = compareChars(current.letter, query.charAt(i));
    		if (location == 0) { return current.wordEnd; }
    		else if (location < 0) { 
    			if (current.right == null) { return false; }
    			return true && search(current.right, query, i); 
    		} else { 
    			if (current.left == null) { return false; }
    			return true && search(current.left, query, i);
    		} 		
    	}
    	
    	int location = compareChars(current.letter, query.charAt(i));
    	if (location == 0) {
    		return true && search(current.mid, query, i + 1);
    	} else if (location < 0) {
    		if (current.right == null) { return false; }
    		return true && search(current.right, query, i);
    	} else {
    		if (current.left == null) { return false; }
    		return true && search(current.left, query, i);
    	}
    }
    
    private static String nearestTerm (TTNode current, String term) {
    	if (current.wordEnd == true) { return term + current.letter; }
    	if (current.left != null) { return nearestTerm(current.left, term); }
    	if (current.right != null) { return nearestTerm(current.right, term); }
    	if (current.mid != null) { return nearestTerm(current.mid, term += current.letter); }
    	return null;
    }
        
    private static ArrayList<String> orderList (TTNode current, String term, ArrayList<String> result) {
    	if (current.left != null) { orderList(current.left, term, result); }
    	if (current.wordEnd == true) { result.add(term + current.letter); }
    	if (current.mid != null) { orderList(current.mid, term + current.letter, result); }
    	if (current.right != null) { orderList(current.right, term, result); }
    	return result;
    }
    
    private static TTNode getNode (TTNode current, String query, int i) {
    	if (query.length() - 1 == i) {
    		int location = compareChars(current.letter, query.charAt(i));
    		if (location == 0) { return current; }
    		else if (location < 0) { 
    			if (current.right == null) { return null; }
    			return getNode(current.right, query, i); 
    		} else { 
    			if (current.left == null) { return null; }
    			return getNode(current.left, query, i);
    		}
    	}
    	
    	int location = compareChars (current.letter, query.charAt(i));
    	if (location == 0) {
/*here*/    		if (current.mid == null) { return null; }
    		return getNode(current.mid, query, i + 1);
    	} else if (location < 0) {
    		if (current.right == null) { return null; }
    		return getNode(current.right, query, i);
    	} else {
    		if (current.left == null) { return null; }
    		return getNode(current.left, query, i);
    	}
    }
    
    // -----------------------------------------------------------
    // TTNode Internal Storage
    // -----------------------------------------------------------
    
    /*
     * Internal storage of autocompleter search terms
     * as represented using a Ternary Tree with TTNodes
     */
    static private class TTNode {
        
        boolean wordEnd;
        char letter;
        TTNode left, mid, right;
        
        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
            left    = null;
            mid     = null;
            right   = null;
        }
        
    }
    
}
