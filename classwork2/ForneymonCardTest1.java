package classwork2;

import static org.junit.Assert.*;

import org.junit.Test;

public class ForneymonCardTest1 {

	@Test
	public void testFlip() {
		FlippingForneymonCard eddie = new FlippingForneymonCard("Eddie", "Burnymon", true);
		assertEquals(eddie.getFace(), true);
		eddie.flip();
		assertEquals(eddie.getFace(), false);
	}
	
	@Test
	public void testMatch1() {
		FlippingForneymonCard a = new FlippingForneymonCard("Eddie", "Burnymon", false);
		FlippingForneymonCard b = new FlippingForneymonCard("Dog", "Burnymon", true);
		FlippingForneymonCard c = new FlippingForneymonCard("Eddie", "Dampymon", false);
		FlippingForneymonCard d = new FlippingForneymonCard("Eddie", "Burnymon", false);
		
		assertEquals(a.match(c), 0);
	}
	
	@Test
	public void testMatch2() {
		FlippingForneymonCard a = new FlippingForneymonCard("Eddie", "Burnymon", false);
		FlippingForneymonCard b = new FlippingForneymonCard("Dog", "Burnymon", true);
		FlippingForneymonCard c = new FlippingForneymonCard("Eddie", "Dampymon", false);
		FlippingForneymonCard d = new FlippingForneymonCard("Eddie", "Burnymon", false);
		
		assertEquals(a.match(b), 2);
	}

	@Test
	public void testMatch3() {
		FlippingForneymonCard a = new FlippingForneymonCard("Eddie", "Burnymon", false);
		FlippingForneymonCard b = new FlippingForneymonCard("Dog", "Burnymon", true);
		FlippingForneymonCard c = new FlippingForneymonCard("Eddie", "Dampymon", false);
		FlippingForneymonCard d = new FlippingForneymonCard("Eddie", "Burnymon", false);
		
		assertEquals(a.match(d), 1);
	}
	
	@Test
	public void testMatch4() {
		FlippingForneymonCard a = new FlippingForneymonCard("Eddie", "Burnymon", false);
		FlippingForneymonCard b = new FlippingForneymonCard("Dog", "Burnymon", true);
		FlippingForneymonCard c = new FlippingForneymonCard("Eddie", "Dampymon", false);
		FlippingForneymonCard d = new FlippingForneymonCard("Eddie", "Burnymon", false);
		
		assertEquals(c.match(d), 0);
	}
}
