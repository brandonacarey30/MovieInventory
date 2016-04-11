// Brandon Carey
// CSC 111
// Inventory (BST) Project 3
// December 6, 2013
// This class represents a single item in a BST of generic DVDs.
// Includes a data item for the current element in the tree
// and references to the left and right children in the tree. 

public class DVDNodeBST
{
  private String title;
  private DVDNodeBST left, right;
  private int numInStock;

  public DVDNodeBST(String obj, int n)
  {
    this.title = obj;
    this.left = this.right = null;
    this.numInStock = n;
  }
  
  public String getTitle() // Aquires a title in the tree. 
  {
    return this.title;
  }
  
  public DVDNodeBST getLeft() // Aquires and moves left for a node.  
  {
    return this.left;
  }

  public void setLeft(DVDNodeBST newLeft) //Sets the current node left.
  {
    this.left = newLeft;
  }

  public DVDNodeBST getRight() // Aquires the right hand node.
  {
    return this.right;
  }
  
  public void setRight(DVDNodeBST newRight) // Sets the current node right.
  {
    this.right = newRight;
  }
  
  public int plusStock() // Adds a movie to the current inventory.
  {
    numInStock = numInStock + 1;
    return this.numInStock; // Returns the stock.
  }

  public int minusStock() // Decreases the stock of the inventory.
  {
    int ret = -1;

    if (numInStock < 1)
    {
      System.out.println("Inventory 0.");
    }
    else
    {
      numInStock = numInStock - 1;
      ret = this.numInStock;
    }
    return ret;
  }

  public int getStock() // Sets up a method for retrieving the Stock. 
  {
    return this.numInStock;
  }
}
