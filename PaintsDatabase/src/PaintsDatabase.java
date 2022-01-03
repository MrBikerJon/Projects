import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PaintsDatabase {

    private static ArrayList<Paint> paintsDatabase = new ArrayList<>();

    public static void main(String[] args) {

        populatePaintsDatabase();

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
                case 0:
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

    public static void populatePaintsDatabase() {
        //Paint p1 = new Paint("Tamiya", "Black", "X-1");
        //paintsDatabase.add(p1);
        //Paint p2 = new Paint("Tamiya", "White", "X-2");
        //paintsDatabase.add(p2);
        openDatabase();
    }


    /**
     * Print all the paints in the database
     */

    private static void printAllPaints () {
        for (Paint paint : paintsDatabase) {
            System.out.println(paint);
        }
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

        paintsDatabase.add(newPaint);

        //Now save the database
        saveDatabase(paintsDatabase);

    }



    /**
     * Displays the menu
     */

    private static void displayMenu () {

        System.out.println();
        System.out.println("MAIN MENU");
        System.out.println("1. Print all paints");
        System.out.println("2. Enter a new paint");
        System.out.println("0. Quit");
        System.out.println("Please input your selection:");
    }

    private static void saveDatabase (ArrayList aPaintsDatabase) {

        try{  // Catch errors in I/O if necessary.
            // Open a file to write to, named SavedObj.sav.
            FileOutputStream saveFile=new FileOutputStream("paintsDatabase.sav");

            // Create an ObjectOutputStream to put objects into save file.
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            // Now we do the save.
            save.writeObject(aPaintsDatabase);

            // Close the file.
            save.close(); // This also closes saveFile.
        }
        catch(Exception exc){
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }

    private static void openDatabase () {

        // Create the data objects for us to restore.
        //ArrayList stuff = new ArrayList();

        // Wrap all in a try/catch block to trap I/O errors.
        try{
            // Open file to read from, named SavedObj.sav.
            FileInputStream saveFile = new FileInputStream("paintsDatabase.sav");

            // Create an ObjectInputStream to get objects from save file.
            ObjectInputStream save = new ObjectInputStream(saveFile);

            // Now we do the restore.
            // readObject() returns a generic Object, we cast those back into their original class type.
            paintsDatabase = (ArrayList) save.readObject();

            // Close the file.
            save.close(); // This also closes saveFile.
            }

            catch(Exception exc){
                exc.printStackTrace(); // If there was an error, print the info.
            }

    }
}
