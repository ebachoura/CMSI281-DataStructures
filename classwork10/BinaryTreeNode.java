package classwork10;

public class BinaryTreeNode {
  public static void main (String[] args) {
    BinaryTreeNode dog = new BinaryTreeNode(2);
    dog.add(1, "L");
    dog.add(3, "R");
    BinaryTreeNode cat = new BinaryTreeNode(2);
    cat.add(1, "L");
    cat.add(7, "R");
    System.out.println(dog.sameTree(dog, cat));
  }
  private int data;
  private BinaryTreeNode left, right;

  BinaryTreeNode (int s) {
    data = s;
    left = null;
    right = null;
  }

  public void add (int s, String child) {
    if (child.equals("L")) {
      left = new BinaryTreeNode(s);
    } else if (child.equals("R")) {
      right = new BinaryTreeNode(s);
    } else {
      throw new IllegalArgumentException();
    }
  }

  public BinaryTreeNode getChild (String child) {
    return (child.equals("L")) ? left : right;
  }

  public int getInt () {
    return data;
  }

  public void doubleTree () {
    helpDouble(this);
  }

  public static boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
    if (n1 == null || n2 == null) { return n1 == null && n2 == null; }
    if (n1.data != n2.data) { return false; }
    return (sameTree(n1.right, n2.right) && sameTree(n1.left, n2.left));
  }

  private void helpDouble (BinaryTreeNode current) {
    if (current == null) { return; }
    helpDouble(current.left);
    BinaryTreeNode storedLeft = current.left;
    current.add(current.data, "L");
    current.left.left = storedLeft;
    helpDouble(current.right);
  }
}
