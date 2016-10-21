package homework1;

import static org.junit.Assert.*;

import org.junit.Test;

public class YarnTests {

    @Test
    public void testYarn() {
        Yarn ball = new Yarn();
    }

    @Test
    public void testIsEmpty() {
        Yarn ball = new Yarn();
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());
        ball.remove("not_empty");
        assertTrue(ball.isEmpty());
    }

    @Test
    public void testGetSize() {
        Yarn ball = new Yarn();
        assertEquals(ball.getSize(), 0);
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
    }

    @Test
    public void testGetUniqueSize() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getUniqueSize(), 1);
        ball.insert("unique");
        assertEquals(ball.getUniqueSize(), 2);
        
        Yarn emptyYarn = new Yarn();
        assertEquals(emptyYarn.getUniqueSize(), 0);
    }

    @Test
    public void testInsert() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));

        Yarn max = new Yarn();
        String test = "test";
        while (max.getUniqueSize() < 100) {
        	test += 1;
        	max.insert(test);
        }
        assertFalse(max.insert("last"));
        
    }

    @Test
    public void testRemove() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 1);
        ball.remove("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        
        Yarn a = new Yarn();
        a.insert("dog");
        a.insert("dog");
        a.remove("dog");
        assertEquals(a.getSize(), 1);
        assertEquals(a.getUniqueSize(), 1);
        assertTrue(a.contains("dog"));
        a.remove("dog");
        assertEquals(a.getSize(), 0);
        assertEquals(a.getUniqueSize(), 0);
        assertFalse(a.contains("dog"));
        assertEquals(a.remove("cat"), 0);
    }

    @Test
    public void testRemoveAll() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
        ball.removeAll("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        ball.removeAll("");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
    }

    @Test
    public void testCount() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.count("dup"), 2);
        assertEquals(ball.count("unique"), 1);
        assertEquals(ball.count("forneymon"), 0);
        assertEquals(ball.count(""), 0);
    }

    @Test
    public void testContains() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));
        assertFalse(ball.contains(""));
    }

    @Test
    public void testGetNth() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        Yarn comparison = ball.clone();
        for (int i = 0; i < ball.getSize(); i++) {
            String gotten = ball.getNth(i);
            assertTrue(comparison.contains(gotten));
            comparison.remove(gotten);
        }
        
        Yarn a = new Yarn();
        a.insert("llama");
        a.insert("llama");
        a.insert("llama");
        a.insert("llama");
        assertEquals(a.getNth(3), "llama");
        a.remove("llama");
        a.insert("reject");
        assertEquals(a.getNth(3), "reject");
    }

    @Test
    public void testGetMostCommon() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals(ball.getMostCommon(), "dup");
        
        Yarn a = new Yarn();
        assertEquals(a.getMostCommon(), null);
        a.insert("yes");
        a.insert("no");
        a.insert("maybe");
        assertEquals(a.getMostCommon(), "yes");       
    }

    @Test
    public void testClone() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        Yarn dolly = ball.clone();
        assertEquals(dolly.count("dup"), 2);
        assertEquals(dolly.count("unique"), 1);
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));
        
        int a = ball.getSize();
        int b = ball.getUniqueSize();        
        assertEquals(dolly.getSize(), a);
        assertEquals(dolly.getUniqueSize(), b);
    }

    @Test
    public void testSwap() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));

        assertEquals(y2.count("dup"), 2);
    }

    @Test
    public void testKnit() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        
        int a1 = y1.getSize();
        int b1 = y2.getSize();
        int yarn3ExpectedSize = a1 + b1;
        
        Yarn y3 = Yarn.knit(y1, y2);
        assertEquals(y3.getSize(), yarn3ExpectedSize);
        
        assertEquals(y3.count("dup"), 3);
        assertEquals(y3.count("unique"), 1);
        assertEquals(y3.count("cool"), 1);
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));        
    }

    @Test
    public void testTear() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.tear(y1, y2);
        assertEquals(y3.count("dup"), 1);
        assertEquals(y3.count("unique"), 1);
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));

        Yarn a = new Yarn();
        Yarn b = new Yarn();
        assertEquals(Yarn.tear(a, b), 0);
    }

    @Test
    public void testSameYarn() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(Yarn.sameYarn(y1, y2));
        assertTrue(Yarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(Yarn.sameYarn(y1, y2));

        Yarn a = new Yarn();
        Yarn b = new Yarn();
        assertTrue(Yarn.sameYarn(a, b));
        
    }
}
