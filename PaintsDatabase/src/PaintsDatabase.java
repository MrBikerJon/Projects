import java.util.ArrayList;
import java.util.Scanner;

public class PaintsDatabase {

    private static ArrayList<Paint> paintsDatabase = new ArrayList<>();

    public static void main(String[] args) {

        populatePaintsDatabase();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Print all paints");
        System.out.println("2. Enter a new paint");
        System.out.println("0. Quit");
        System.out.println("Please input your selection:");

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
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }


    }

    /**
     * populate the paintsDatabase with some paints
     */

    public static void populatePaintsDatabase() {
        Paint p1 = new Paint("Tamiya", "Black", "X-1");
        paintsDatabase.add(p1);
        Paint p2 = new Paint("Tamiya", "White", "X-2");
        paintsDatabase.add(p2);
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

        s.close();

    }
}
