
/**
 * Write a description of class Book here.
 *
 * @author Jonathan Furminger
 * @version 22 November 2021
 */
public class Book
{
   /**
    * private fields for class Book (Q1 part b)
    */
    
    private String author; // the author of the book, which is a String
    private String title; // the title of the book, which is a String
    private String id; // an identifier for the book, which is a String
    private boolean onLoan; // a boolean value indicating whether a book is on loan. 

    /**
     * Constructor for objects of class Book (Q1 part c)
     */
    public Book(String anAuthor, String aTitle, String anId)
    {
        // initialise instance variables
        author = anAuthor;
        title = aTitle;
        id = anId;
        onLoan = false;
    }

    /**
     * Getter method for author field (Q1 part d)
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * Getter method for title field (Q1 part d)
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Getter method for id field (Q1 part d)
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * Getter method for onLoan field (Q1 part d)
     */
    public boolean isOnLoan()
    {
        return onLoan;
    }
    
    /**
     * Setter method for onLoan field (Q1 part d)
     */
    public void setOnLoan(boolean isOnLoan)
    {
        onLoan = isOnLoan;
    }
    
    /**
     * *** CHECK WILL THIS WORK - NEED TO PASS IN id OF A BOOK???
     * 
     * Return a string describing the book, using the following format:
     * Title: title, Author: author (availability) 
     * depending on whether the book is on loan or not, 
     * as determined by its onLoan field.(Q1 part e)
     */
    public String toString()
    {
        if(onLoan) {
            return ("Title: " + title +", Author: " + author + " (on loan)");
        } else {
            return ("Title: " + title +", Author: " + author + " (available)");
        }
}

    /**
     * 
     * Returns true if the book has a valid id, and otherwise returns false.
     * (Q 1 part f)
     * 
     */
    public boolean verifyId ()
    {
        int counter = 0; // declare a field to hold the end result of calculations on anId
        
        if(id.length() != 7) {
                return false;  // Check is the length of anId is equal to 7. If not, return false.
        } else 
        {
            for(int i = 0; i < 7; i++) {
                counter = counter + (id.charAt(i) % 10); //for each character ch in anId evaluate the expression ch % 10, and add the result to counter.
            }
        }
        
        if (counter % 7 == 0) {
            return true; // The total % 7 is calculated on counter. If the result is 0 then the id is valid, otherwise it is not valid.
        }   else { 
            return false; 
        }
    }
}