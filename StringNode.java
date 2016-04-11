// Brandon Carey
// CSC 111
// Inventory Project 2
// November 8, 2013
// This class represents a single item in a DVDLinkedList.
// It includes a data item for the current element on the list
// and a reference to the next element on the list.  The next
// reference is null if this is the last item on the list.

public class StringNode //Creates a string node class.
{
  private String title;
  private int numInStock;
  private StringNode next;

  public StringNode(String s, int n)
  {
    this.title = s;
    this.next = null;
    this.numInStock = n;
  }

  public String getTitle()// Creates a method for returning a title. 
  {
    return this.title;
  }

  public StringNode getNext() // Gets next node in list.
  {
    return this.next;
  }

  public void setNext(StringNode newNext) // Sets the next node in the list.
  {
    this.next = newNext;
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
