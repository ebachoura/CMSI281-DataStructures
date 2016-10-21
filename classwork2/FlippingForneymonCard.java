package classwork2;

public class FlippingForneymonCard extends ForneymonCard {
	private Boolean faceDown;
	
	public FlippingForneymonCard (String name, String type, Boolean face) {
		super(name,type);
		faceDown = face;
	}
	
	public FlippingForneymonCard () {
		super();
		faceDown = true;
	}
	
	//Getters
	public Boolean getFace() {
		return faceDown;
	}
	
	
	
	//Other Methods
	public void flip() {
		faceDown = !faceDown;
	}
	
	public int match (FlippingForneymonCard other) {
		if ((faceDown || other.getFace())) {
			return 2;
		} else if (!faceDown && !other.getFace() && sameName(other) && sameType(other)) {
			return 1;
		} return 0;
	}
	
	private Boolean sameName(FlippingForneymonCard other) {
		return getName().equals(other.getName());
	}
	
	private Boolean sameType(FlippingForneymonCard other) {
		return getType().equals(other.getType());
	}
	
	public String toString() {
		return !faceDown ? super.toString(): "?: ?";
	}
}