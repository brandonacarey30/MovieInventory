// Brandon Carey
// CSC 111
// Inventory (BST) Project 3
// December 6, 2013
// This class allows the main method, "MoviesBST.java" to be implemented in the 
// form of a binary search tree.

import java.io.*;
import java.util.Scanner;

public class DvdBST
{
  protected DVDNodeBST root;

  public DvdBST()
  {
    root = null;
  }

  // Creates a boolean opperator do check if the list is empty. 
  public boolean isEmpty() 
  {
    return (root == null);
  }
  
  // Etsablishes adding a String DVD into the list. 
  public void add(String title)
  {
    root = addToSubtree(title, root);
  }
 
  // Establishes the addition to the subtree to the right or left. 
  private DVDNodeBST addToSubtree(String title, DVDNodeBST current)
  {
    if (current == null)
      return new DVDNodeBST (title, 1);

    else if (title.compareToIgnoreCase(current.getTitle()) < 0)
    {
      current.setLeft(addToSubtree(title, current.getLeft()));

    }

    else if (title.compareToIgnoreCase(current.getTitle()) > 0)
    {
      current.setRight(addToSubtree(title, current.getRight()));
    }

    else current.plusStock();

    return current;
   
  }

  // Keeps track of the actual tree size. 
  private int subtreeSize(DVDNodeBST subroot)
  {
    if (subroot == null)
      return 0;
    else
      return (subtreeSize(subroot.getLeft())
              + subtreeSize(subroot.getRight()) + 1);
  }

  // Removes a DVD String from the tree. 
  public void delete(String title)
  {
    root = deleteItemFromSubtree(root, title);
  }
  
  // Sets up the delete opperator, so the user can delete from the list. 
  private DVDNodeBST deleteItemFromSubtree(DVDNodeBST subRoot, String title)
  {
    if (subRoot == null)
    {
      System.out.println("The movie has been removed from the list.");
      System.out.println();
      return null; 
    }
    
    else if (subRoot.getTitle().compareToIgnoreCase(title) < 0)
    {
      subRoot.setRight(deleteItemFromSubtree(subRoot.getRight(), title));
      return subRoot;
    }
  
    else if (subRoot.getTitle().compareToIgnoreCase(title) > 0)
    {
      subRoot.setLeft(deleteItemFromSubtree(subRoot.getLeft(), title));
      return subRoot; 
    }

    else 
      return deleteRoot(subRoot);
  }

  // Deletes a DVD from the root of the tree. 
  private DVDNodeBST deleteRoot(DVDNodeBST subRoot)
  {
    DVDNodeBST successor, successorParent;
    
    if (subRoot.getLeft() == null)
      return subRoot.getRight();

    else if (subRoot.getRight() == null)
      return subRoot.getLeft();
 
    else 
    {
      successorParent = subRoot.getRight();
    
      if (successorParent.getLeft() == null)
      {
        successorParent.setLeft(subRoot.getLeft());
        return successorParent;
      }
      
      while (successorParent.getLeft().getLeft() != null)
        successorParent = successorParent.getLeft();

      successor = successorParent.getLeft();
      successorParent.setLeft(successor.getRight());

      successor.setLeft(subRoot.getLeft());
      successor.setRight(subRoot.getRight());

      return successor; 
    }
  }

  public void display()
  {
    displaySubtree(root);
  }

  // Allows the user to display the list on the command line. 
  private void displaySubtree(DVDNodeBST subroot)
  {
    
    if (subroot == null)
    { 
      return;
    }

    displaySubtree(subroot.getLeft());

    System.out.println(subroot.getTitle() + " (" + subroot.getStock() + ")");

    displaySubtree(subroot.getRight());
  }
 
  // Saves the list for later use. 
  public void save()
  {
    PrintWriter pw = null;

    try
    {
      pw = new PrintWriter(new File("InventBST.dat"));
    }
    catch(IOException e)
    {
    }
    pw.println(subtreeSize(root));
    saveSubtree(pw, root);
    pw.close();
  }

  private void saveSubtree(PrintWriter pw, DVDNodeBST subroot)
  {
    if (subroot == null)
      return;
    else
    {
      saveSubtree(pw, subroot.getLeft());
      pw.print(subroot.getStock() + " ");
      pw.println(subroot.getTitle());
      saveSubtree(pw, subroot.getRight());
    }
  }

  // Restores the list from the quit function. 
  public void restore()
  {
    Scanner fs = null;

    try
    {
      fs = new Scanner(new FileReader("InventBST.dat"));
    }
    catch(FileNotFoundException e)
    {
      return;
    }
    
    int size = fs.nextInt();
    root = restoreSubtree(fs, size);
  }

  private DVDNodeBST restoreSubtree(Scanner fs, int subtreeSize)
  {
    DVDNodeBST left, right, subRoot;
    String subRootItem;

    if (subtreeSize == 0)
      return null;

    left = restoreSubtree(fs, subtreeSize/2);
    
    int stock = fs.nextInt();
    subRootItem = fs.nextLine().trim();
    subRoot = new DVDNodeBST(subRootItem, stock);

    right = restoreSubtree(fs, (subtreeSize-1)/2);
    
    subRoot.setLeft(left);
    subRoot.setRight(right);

    return subRoot;
  }

  // Allows the user to implement the delete method, while 
  // getting rid of the titles in the tree. 
  public void sell(String title, Scanner kbd)
  {
    DVDNodeBST node = root;
    char h;
   
   
    while (node != null && !title.equalsIgnoreCase(node.getTitle())) 
    {
      if (title.compareToIgnoreCase(node.getTitle()) < 0)
      node = node.getLeft();
     
      else 
      node = node.getRight();
    }

    if (node == null) 
    {
      System.out.println("The movie is not in stock");
      System.out.println();
      return;
    }
    
    else
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

  // Traverses the list and searches for a specific title. 
  public void inquire(String title)
  {
    DVDNodeBST node = root;

    while (node != null && !title.equalsIgnoreCase(node.getTitle())) 
    {
      if (title.compareToIgnoreCase(node.getTitle()) < 0)
      node = node.getLeft();
     
      else 
      node = node.getRight();
    }

    if (node != null)
    {
      System.out.print(node.getTitle() + " Copies: ");
      System.out.println(node.getStock());
      System.out.println();
    }
    
    else
    {
      System.out.println("The movie is not in stock.");
      System.out.println();
    }
  }
}

  
