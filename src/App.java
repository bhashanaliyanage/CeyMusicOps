import java.io.BufferedReader;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws Exception {
        String file = "test.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 2) { // Ensure at least two values exist in the row
                    String isrc = row[0];
                    String earnings = row[1]; // Remove double quotes and convert earnings to double

                    String ISRCWithoutQuotes = isrc.replace("\"", "");
                    String earningsWithoutQuotes = earnings.replace("\"", "");

                    System.out.println("ISRC: " + ISRCWithoutQuotes);
                    System.out.println("Earnings in EUR: " + earningsWithoutQuotes);

                    if (!ISRCWithoutQuotes.equals("") && !earningsWithoutQuotes.equals("Reported Royalty")) {
                        Boolean done = Database.addEntry(ISRCWithoutQuotes, earningsWithoutQuotes);
                        if (done == true) {
                            System.out.println("Earning Added...");
                        } else {
                            System.out.println("Database Entry Faliure...");
                        }
                        Database.closeConnection();
                    }
                    System.out.println();
                }
            }

            System.out.println("Monthly Earnings Read.");
        } catch (Exception e) {
            System.out.println("Error...!");
        }
    }
}
