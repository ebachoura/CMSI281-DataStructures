package classwork2;

import java.util.Arrays;

public abstract class ForneymonCard {
	private String name;
	private String type;
	private String[] acceptedTypes = {"Burnymon", "Dampymon", "Leafymon"};
	
	public ForneymonCard (String n, String t) {
		argCheck (n, t);
		name = n;
		type = t;
	}
	
	public ForneymonCard () {
		name = "MissingNu";
		type = "Burnymon";
	}
	
	public void argCheck (String name, String type) {
		if (name == "") {
			throw new IllegalArgumentException();
		} if (!Arrays.asList(acceptedTypes).contains(type)) {
			throw new IllegalArgumentException();
		}
	}
	
	//Getters
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//Other Methods
	public String toString() {
		return type + ": " + name;
	}
}
