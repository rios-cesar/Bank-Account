import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class BankAccount {
   private static final String FILENAME = new File("").getAbsolutePath()+File.separator+"log.html"; //path to log.html

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String next = "";
        do {
            System.out.print("Please enter in a command (Deposit, Withdraw, Balance, Exit) :");
            next = input.next();     //read user input
            switch (next.toLowerCase()) {// user input is a case insensitive

                case "deposit":
                    System.out.print("Please enter an amount to deposit:");
                    next = input.next();     //read user input
                    if (isDoubleValid(next)) {
                        doDeposit(next, FILENAME);//deposit
                    }
                    break;
                case "withdraw":
                    System.out.print("Please enter an amount to withdraw:");
                    next = input.next();     //read user input
                    if (isDoubleValid(next)) {
                        doWithdraw(next, FILENAME);//withdraw
                    }

                    break;
                case "balance":
                    System.out.println("The current balance is: $" + getBalance(FILENAME)); // get balance
                    break;
            }
        } while (!"Exit".equalsIgnoreCase(next));

    }

    /**
     * This method checks the validity of the input
     * valid double: 44; 44.1; 55.20;
     * invalid double: -25.00; 48.548;
     * @param input user input
     */
    private static boolean isDoubleValid(String input) {
        String pattern = "\\d+(\\.\\d{1,2})?";

        return input.matches(pattern);
    }

    /**
     * Counts and returns the balance amount from a file .html
     * @param fileName path to log.html
     */
    private static double getBalance(String fileName) {
        BigDecimal rez = BigDecimal.ZERO;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains("<tr><td>")) {
                    sCurrentLine = sCurrentLine.trim(); //Returns a string whose value is this string, with any leading and trailing whitespace removed.
                    sCurrentLine = sCurrentLine.replace("<tr><td>", "").replace("</td></tr>", "");
                    BigDecimal value = new BigDecimal(sCurrentLine.trim()); //
                    rez = rez.add(value); //sum the amounts within the 'transactions' table
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rez.doubleValue();

    }

    /**
     * This method appending a row at the end of the 'transactions' table.
     * Withdrawal amounts  convert to negative double
     * @param fileName path to log.html
     * @param withdraw user input
     **/
    public static void doWithdraw(String withdraw, String fileName) {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String sCurrentLine;
            boolean start = false;
            while ((sCurrentLine = br.readLine()) != null) {

                if (sCurrentLine.trim().contains("id=\"transactions\"")) { //looking for a table with id = transactions
                    start = true;
                }
                if (sCurrentLine.trim().contains("</tbody>") && start) {

                    sb.append("                <tr><td>").append("-").append(withdraw).append("</td></tr>").append("\n"); //appending a row at the end of the 'transactions' table
                }
                sb.append(sCurrentLine).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();

        }


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            bw.write(sb.toString()); // write to file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method appending a row at the end of the 'transactions' table.
     * Deposit amounts  convert to double
     * @param fileName path to log.html
     * @param deposit user input
     **/
    public static void doDeposit(String deposit, String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String sCurrentLine;
            boolean start = false;
            while ((sCurrentLine = br.readLine()) != null) {

                if (sCurrentLine.trim().contains("id=\"transactions\"")) { //looking for a table with id = transactions
                    start = true;
                }
                if (sCurrentLine.trim().contains("</tbody>") && start) {

                    sb.append("                <tr><td>").append(deposit).append("</td></tr>").append("\n"); //appending a row at the end of the 'transactions' table
                }
                sb.append(sCurrentLine).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            bw.write(sb.toString()); // write to file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}