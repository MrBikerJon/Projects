
/**
 * Write a description of class Library here.
 *
 * @author Jonathan Furminger
 * @version 22 November 2021
 */

import java.util.ArrayList; // import the ArrayList class (Question 2 part b)

public class Library
{
    private ArrayList<Book> books; // ArrayList of Book is used to store books owned by the library (Question 2 part b)
    private String name; // represents the name of the library (Question 2 part b)

    /**
     * Constructor for objects of class Library (Question 2 part c)
     */
    public Library(String aName)
    {
        name = aName; // use ithe parameter to set the library's name
        books = new ArrayList<Book>();  //The constructor should also initialize the library's field books 
        //to an empty collection of the appropriate type ******CHECK DOES THIS DO IT???? *****
    }

     /**
     * This method adds a new Book to the library books collection using the supplied parameters.
     * (Question 2, part d i)
     *
     * @params      anAuthor, aTitle, anId **** SHOULD I DESCRIBE EACH PARAMETER? CHECK EXAMPLES *******************
     * @return      None
     */
    public void addBook(String anAuthor, String aTitle, String anId)
    {
        books.add(new Book(anAuthor, aTitle, anId));
    }
    
    /**
     * This method calculateFine(double, int) will calculate a fine for a late book that a library user is returning. 
     * The method has parameters for the price of the book and the number of days late the book is. 
     * Calculate a fine for the late book as 2 percent of the price of the book, per day late. 
     * The method returns the fine as a double value. 
     * (Question 2, part d ii)
     *
     * @params      bookPrice, daysLate  
     * @return      The method should return the fine as a double value ********** CHECK DESCRIPTION **********
     */
    public double calculateFine(double bookPrice, int daysLate)
    {
        double fine;
        fine = bookPrice * 0.02 * daysLate; // Calculate a fine for the late book as 2 percent of the price of the book, per day late. 
        return fine;
    }
    
    /**
     * getMatchingBooks(String) receives the title of a Book as a parameter, and returns an ArrayList 
     * of books the library owns whose titles match the parameter string. 
     * You should use the string equals method to match the titles of the books (not the list 
     * contains method).
     * Hint: Use a local variable to create a new list of books, add any matching books to that 
     * list, and then return the list when done.
     * (Question 2, part d iii)
     *
     * @params      aBook, the title of a Book 
     * @return      an ArrayList of books the library owns whose titles match the parameter string
     */
    public ArrayList getMatchingBooks(String aBookTitle)
    {
       ArrayList<Book> tempList = new ArrayList<Book>();
       int numberOfBooks = books.size() - 1;
       
       for(int i = 0; i < numberOfBooks; i++) {
           if(books.get(i).getTitle() == aBookTitle) {
               tempList.add(books.get(i));
            }
        }
        
        return tempList;
    }
    
    /**
    * isAvailable(Book) returns true if the parameter’s title matches that of any book in the collection and the book is not on loan. 
    * Otherwise, the method returns false
    * (Question 2, part d iv)
    *
    * @params      aBook 
    * @return      boolean
    */
    public boolean isAvailable(Book aBook)
    {
        if(books.contains(aBook)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * listAllBooks() takes no arguments and prints all the books in the library in the order they appear in the books collection.
     * (Question 2, part d v)
     *
     * @params      None 
     * @return      None
     */
    public void listAllBooks()
    {
        int numberOfBooks = books.size() - 1;
        for(int i = 0; i < numberOfBooks; i++) {
            System.out.println(books.get(i));
        }   
    }

    /**
     * loanBook(Book) takes a Book as an argument and sets the first book in the books list with a 
     * matching id to be on loan. (Assume that book ids are unique.) 
     * If there is no matching id then the method has no effect.
     * (Question 2, part d vi)
     *
     * @params      Book 
     * @return      None
     */
    public void loanBook(Book aBook)
    {
       int index = books.indexOf(aBook);
       books.set(index, aBook); // ****** CAN I SHORTEN THIS???   *******
    }
    
    /**
     * removeBook(Book) takes a Book as an argument and removes from the books collection the first book 
     * with a matching id, if the id is found. 
     * If the book is not found the method prints "Book not found".
     * Hint: see the example on page 134 of B&K.
     * (Question 2, part d vii)
     *
     * @params      Book 
     * @return      None
     */
    public void removeBook(Book aBook)
    {
        
    }
}
