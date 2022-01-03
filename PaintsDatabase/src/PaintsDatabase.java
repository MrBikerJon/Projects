import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class PaintsDatabase {

    private static HashMap<String, Paint> paintDatabase= new HashMap<>();

    public static void main(String[] args) {

        openDatabase();

        Scanner scanner = new Scanner(System.in);

        displayMenu();

        while(scanner.hasNext()) {

            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    printAllPaints();
                    break;
                case 2:
                    addPaint();
                    break;
                case 3:
                    amendPaint();
                    break;
                    case 0:
                    saveDatabase(paintDatabase);
                    scanner.close();
                    System.exit(1);
                    break;
                default:
                    break;
            }

            displayMenu();

        }

        System.out.println("Reached here");
    }

    /**
     * populate the paintsDatabase with some paints
     */

    public static void populatePaintDatabase () {

        openDatabase();
    }


    /**
     * Print all the paints in the database
     */

    private static void printAllPaints () {
        paintDatabase.forEach(
                (key, value)
                        -> System.out.println("Paint reference: " + key + " = " + value));
    }


    /**
     * Input a new paint into the database.
     * Gets user input for Brand, Colour and Reference
     * Sets up a new Paint, and adds it to the database.
     */

    private static void addPaint () {

        Scanner s = new Scanner(System.in);

        System.out.println("Please enter the brand:");
        String aBrand = s.nextLine();
        System.out.println("Please enter the colour:");
        String aColour = s.nextLine();
        System.out.println("Please enter the reference:");
        String aReference = s.nextLine();

        Paint newPaint = new Paint(aBrand, aColour, aReference);

        paintDatabase.put(aReference, newPaint);

    }


    /**
     * Input a new paint into the database.
     * Gets user input for Brand, Colour and Reference
     * Sets up a new Paint, and adds it to the database.
     */

    private static void amendPaint () {

        Scanner s = new Scanner(System.in);

        System.out.println("Please enter the reference of the paint to amend:");
        String aReference = s.nextLine();

        String currentBrand = paintDatabase.get(aReference).getBrand();
        System.out.println("Current brand is " + paintDatabase.get(aReference).getBrand() +
                ". Please enter the new brand, or 'Enter' to leave unchanged:\n");
        String newBrand = s.nextLine();
        if(!newBrand.equals("")) {
            paintDatabase.get(aReference).setBrand(newBrand);
            System.out.println("Successfully changed the brand from " + currentBrand + " to " + newBrand);
        }

        String currentColour = paintDatabase.get(aReference).getColour();
        System.out.println("Current colour is " + currentColour +
                ". Please enter the new colour, or 'Enter' to leave unchanged:\n");
        String newColour = s.nextLine();
        if(!newColour.equals("")) {
            paintDatabase.get(aReference).setColour(newColour);
            System.out.println("Successfully changed the colour from " + currentColour + " to " + newColour);
        }

    }


    /**
     * Displays the menu
     */

    private static void displayMenu () {

        System.out.println();
        System.out.println("MAIN MENU");
        System.out.println("1. Print all paints");
        System.out.println("2. Enter a new paint");
        System.out.println("3. Amend an existing paint");
        System.out.println("0. Save database & Exit\n");
        System.out.println("Please input your selection:");
    }

    private static void saveDatabase (HashMap aPaintDatabase) {

        System.out.println("Saving the database...");

        try{  // Catch errors in I/O if necessary.
            // Open a file to write to, named SavedObj.sav.
            FileOutputStream saveFile=new FileOutputStream("paintDatabase.sav");

            // Create an ObjectOutputStream to put objects into save file.
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            // Now we do the save.
            save.writeObject(aPaintDatabase);

            // Close the file.
            save.close(); // This also closes saveFile.
        }
        catch(Exception exc){
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }

    private static void openDatabase () {

        System.out.println("Opening the database...");

        // Wrap all in a try/catch block to trap I/O errors.
        try{
            // Open file to read from, named SavedObj.sav.
            FileInputStream saveFile = new FileInputStream("paintDatabase.sav");

            // Create an ObjectInputStream to get objects from save file.
            ObjectInputStream save = new ObjectInputStream(saveFile);

            // Now we do the restore.
            // readObject() returns a generic Object, we cast those back into their original class type.
            paintDatabase = (HashMap<String, Paint>) save.readObject();

            // Close the file.
            save.close(); // This also closes saveFile.
            }

            catch(Exception exc){
                exc.printStackTrace(); // If there was an error, print the info.
            }

    }
}
