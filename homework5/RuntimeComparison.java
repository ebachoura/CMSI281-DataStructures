package homework5;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class RuntimeComparison {
	private final int MAX_INT = 50000;
	
	@Test
	public void HashtableInsertion() {
		Hashtable<Integer, Integer> hash = new Hashtable<Integer,Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			hash.put(i, i);
		}
	}
	
	@Test
	public void HashtableRetrieval() {
		Hashtable<Integer, Integer> hash = new Hashtable<Integer,Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			hash.put(i, i);
		}
		
		for (Integer i = 0; i < MAX_INT; i++) {
			hash.get(i);
		}
	}
	
	@Test
	public void LinkedListPrependInsertion() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			list.add(0, i);
		}
	}
	
	@Test
	public void LinkedListPrependRetrieval() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			list.add(0, i);
		}
		
		for (Integer i = 0; i < MAX_INT; i++) {
			list.get(i);
		}
	}
	
	@Test
	public void LinkedListAppendInsertion() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			list.add(i);
		}
	}
	
	@Test
	public void LinkedListAppendRetrieval() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			list.add(i);
		}
		
		for (Integer i = 0; i < MAX_INT; i++) {
			list.get(i);
		}
	}
	
	@Test
	public void ArrayListPrependInsertion() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			list.add(0, i);
		}
	}
	
	@Test
	public void ArrayListPrependRetrieval() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			list.add(0, i);
		}
		
		for (Integer i = 0; i < MAX_INT; i++) {
			list.get(i);
		}
	}
	
	@Test
	public void ArrayListAppendInsertion() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			list.add(i);
		}
	}
	
	@Test
	public void ArrayListAppendRetrieval() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Integer i = 0; i < MAX_INT; i++) {
			list.add(i);
		}
		
		for (Integer i = 0; i < MAX_INT; i++) {
			list.get(i);
		}
	}
}
