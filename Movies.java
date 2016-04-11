/* Brandon Carey
   CSC 111
   Inventory Project 2
   November 8, 2013
   This program initializes a users input, and creates a linked list based on 
   the input. The list will be an inventory of movies that are in stock, and
   how many copies of each movie the store has on file. 
*/


import java.util.*;

import java.io.*;


public class Movies
{
  public static void main(String[] args)
  {
    
    DVDLinkedList movies = new DVDLinkedList(); // Creates new list.
    Scanner kbd = new Scanner(System.in); //Creates new Scanner for user input.
    char ch;
    int numInStock; // Integer that keeps track of inventory stock.
    movies.load(); // Calls the load method saved in the .dat file. 
   

    System.out.println("Please enter a command.");
    String cmd = kbd.next().toUpperCase();
    String title = kbd.nextLine().trim();
    
    // Initiates a case switch method for multiple user inputs.
    while (!cmd.equalsIgnoreCase("Q"))
    {
      ch = cmd.charAt(0);
      

      switch (Character.toUpperCase(ch))
      {
        case 'H': //Creates the help case.
          System.out.println("H: Prints this help list.");
          System.out.println("L: Lists the titles in the inventory in "
                                + "alphabetical order.");
          System.out.println("I <title>: Displays the number of copies "
                                + "currently in stock of a particular title.");
          System.out.println("A <title>: Add a copy of the given title to the "
                                + "current inventory.");
          System.out.println("S <title>: Sell a copy of the given title.");
          System.out.println("Q: Ends the program.");
        break;

        case 'L': //Calls the dumpList(), list all movies. 
          System.out.println();
          System.out.println("Brandon's Movie Store (Inventory):");
          System.out.println();
          dumpList(movies);  
        break;
   
        case 'I': // Inquires on a certain title and stock. 
          System.out.println();
          movies.inquire(title);
          
        break;

        case 'A': // Adds a movie to the Linked List.
          movies.add(title);
        break;
  
        case 'S': // Deletes a movie form the Linked List.
           movies.sell(title, kbd);
        break;

        default:
           System.out.println("Invalid Execution");
        break;
   
      }
      System.out.println("Please enter another command."); // Prompts input 2*
      cmd = kbd.next().toUpperCase();
      title = kbd.nextLine().trim();
    }
    movies.save(); // Saves the program for later use.

  }

  // Creates the dumpList method allowing a user to display all titles
  // in the list. 
  public static void dumpList(DVDLinkedList list)
  {
    int i;
      
    if (list.isEmpty())
      System.out.println("List is Empty.");
    else
    {
      for (i=1; i <= list.length(); i++)
        System.out.println(list.get(i));
    }
      System.out.println();
  }
}
