// Brandon Carey
// CSC 111
// Inventory Project 2
// November 8, 2013
// This class allows the main method, "movies.java" to be implemented in the 
// form of a linked list.
import java.io.*;
import java.util.*;
public class DVDLinkedList
{
  private StringNode head;
 
  
  // constructor: Create an empty list
  // POSTCONDITION: The list exists (and is empty)
  public DVDLinkedList()
  {
    head = null;
  }

  // length(): return the number of items in the list
  public int length()
  {
    int size = 0;
    StringNode current = head;


    while (current != null)
    {
      current = current.getNext();
      size++;
    }
    return size;
  }

  // isEmpty(): return true if the the head of the list is empty.
  public boolean isEmpty()
  {
    return (head == null);
    
  }

  public void add (String title)
  {
    add(title,1);
  }
  
  public void add (String title, int numInStock)
  {
    // First create a new StringNode
    StringNode addMe = new StringNode(title, numInStock);
    StringNode prev = head;
    int i;

    // Adds to the list if the head of the list is null.
    if (head == null || title.compareToIgnoreCase(head.getTitle()) < 0)
    {
      addMe.setNext(head);
      head = addMe;
    }
    else if (title.compareToIgnoreCase(head.getTitle()) == 0) 
    {
      head.plusStock(); // Adds to the current stock of the movie.
    }
    else
    {
      // Now find the node BEFORE the spot where we want to add the new item
      prev = head;

      while (prev.getNext() != null 
             && title.compareToIgnoreCase(prev.getNext().getTitle()) > 0)
      {
        prev = prev.getNext();
        if (prev == null)
          throw new ListException("Cannot add beyond end of list");
      }
      // Then adjust the references.
      if (prev.getNext() != null 
          && title.compareToIgnoreCase(prev.getNext().getTitle()) == 0)
      {
        prev.getNext().plusStock();
      }
      else
      {
        addMe.setNext(prev.getNext());
        prev.setNext(addMe);
      }
    }
  }

  // Even though this list is not position based, we use get in dumpList().
  // Get Traverses through the list and gets a position to be 
  // relayed to dumpList.
  public String get (int position)
  {
    StringNode node = head;
    int i;

    if (position <= 0)
      throw new ListException("Cannot retrieve element before position 1");

    for (i=1; i < position; i++)
    {
      node = node.getNext();
      if (node == null)
        throw new ListException("Cannot retrieve element past end of list");
    }
    // Sets up the format for the title and stock. 
    return node.getTitle() + " (" + node.getStock() + ")";
  }
  
  // Sets up the class for deleting a string in the list. 
  public void delete(String title)
  {
    StringNode prev = head;
    StringNode target;

    if (head == null)
      System.out.println("Cannot sell movies from empty list");

    else if(title.compareToIgnoreCase(head.getTitle()) == 0)
    {
      // Save target for later use
      target = head;

      // Reset head reference to skip over previous first node
      head = head.getNext();
      

      // null out the next reference of the target node
      target.setNext(null);
    }
    else
    {
      // Now find the node BEFORE the spot where we want to delete the item
      while (prev.getNext() != null 
             && title.compareToIgnoreCase(prev.getNext().getTitle()) > 0)
      {
        prev = prev.getNext();
       
      }
     
      target = prev.getNext();
      if (target == null)
        System.out.println("Cannot delete elements that are not in the list");
      
      
      else if (target.getTitle().compareToIgnoreCase(title) == 0)
      {
        prev.setNext(target.getNext());
        target.setNext(null);
      }
    }
  }
  
  // Allows the program to be saved to  a .dat file.
  public void save()
  {
    PrintWriter pw = null;

      try
      {
        pw = new PrintWriter(new FileWriter("inventory.dat"));
      }
      catch (IOException e)
      {
      }
      for (StringNode current = head; current != null; current = current.getNext())
      {
        pw.print(current.getStock());
        pw.println(" " + current.getTitle());
      }
    
    pw.close(); 
  }
   
  // This class allows the program to load from the .dat file.
  public void load()
  {
    Scanner fs = null;
    
      try
      {
        fs = new Scanner(new File("inventory.dat"));
      }
      catch (IOException e)
      {
      }
      if (fs != null)
      {
        while (fs.hasNext())
        {
          int numInStock = fs.nextInt();
          String title = fs.nextLine().trim();
          add(title, numInStock);
        }
      }
    fs.close();
  }
  
  // Allows the program to adjust the stocks, as well as get rid of 
  // movies in the list. 
  public void sell(String title, Scanner kbd)
  {
    StringNode node = head;
    char h;
   
    while (node != null && title.compareToIgnoreCase(node.getTitle()) > 0) 
    {
      node = node.getNext();
    } 
    
    if (title.equalsIgnoreCase(node.getTitle()))
    {
      node.minusStock();
    }
    
    // Asks the user if the stock is below 0, if they want to delete from 
    // the list. 
    if (node.getStock() <= 0)
    {
      System.out.println("Inventory is 0. Delete movie from list? (Y/N).");
      String ans = kbd.nextLine();
   
      h = ans.charAt(0);
      

      switch (Character.toUpperCase(h))
      {
        case 'Y':
          delete(title);
        case 'y':
          delete(title);
      }
    }
  }

  // Inquires about a certain title, however does crash when no title exsists.
  public void inquire(String title)
  {
    StringNode node = head;

    while (node != null && title.compareToIgnoreCase(node.getTitle()) > 0) 
    {
      node = node.getNext();
    } 
    if (title.equalsIgnoreCase(node.getTitle()))
    {
      System.out.print(node.getTitle() + " Copies: ");
      System.out.println(node.getStock());
    }
  }
}
