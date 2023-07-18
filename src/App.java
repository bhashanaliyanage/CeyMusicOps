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
                    System.out.println("Here!");
                    String earnings = row[1]; // Remove double quotes and convert earnings to double

                    System.out.println("ISRC: " + isrc);
                    System.out.println("Earnings: " + earnings);
                    System.out.println();
                }
            }

            System.out.println("Monthly Earnings Read.");
        } catch (Exception e) {
            System.out.println("Error...!");
        }
    }
}
