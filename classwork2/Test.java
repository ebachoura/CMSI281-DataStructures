  package classwork2;
  
  public class Test {
  
      public static void main (String[] args) {
          FlippingForneymonCard burny = new FlippingForneymonCard("burny", "Burnymon", false);
          // "Burnymon: burny"
          System.out.println(burny);
          burny.flip();
          // "?: ?"
          System.out.println(burny);
          
          FlippingForneymonCard missingNu = new FlippingForneymonCard();
          // "?: ?"
          System.out.println(missingNu);
          missingNu.flip();
          // "Burnymon: MissingNu"
          System.out.println(missingNu);
      }
      
  }