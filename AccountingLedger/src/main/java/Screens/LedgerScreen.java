package Screens;

import java.util.ArrayList;

import Models.Transaction;
import Services.DataService;
import Utilities.Utils;

public class LedgerScreen {
    final ReportsScreen reports;
    final DataService dataService;

    public LedgerScreen() {
        reports = new ReportsScreen();
        dataService = new DataService();
    }


    public void showLedgerScreenOptionsMenu() {
        Utils.printTitle("LEDGER SCREEN");
        System.out.println("Select option to start: ");
        System.out.println("A: All");
        System.out.println("D: Deposits");
        System.out.println("P: Payments");
        System.out.println("R: Reports");
        System.out.println("H: Home \n");
    }

    public String receiveUserOption() {
        //take user's option and assign it to a new String.
        String selectedOption = "INVALID";

        while (selectedOption.equals("INVALID")) {
            String userOption = Utils.getStringFromTerminal("Please choose one of the options.").toUpperCase();
            selectedOption = switch (userOption) {
                case "A" -> "ALL";
                case "D" -> "DEPOSITS";
                case "P" -> "PAYMENTS";
                case "R" -> "REPORTS";
                case "H" -> "HOME";
                default -> "INVALID";
            };
            if (selectedOption.equals("INVALID")) {
                Utils.printError("Invalid option. Please enter A, D, P, R or H.");
            }
        }
        return selectedOption;
    }

    public boolean performUserOption(String userOption) {
        if(userOption.equals("HOME")) {
            return true;
        }
        switch (userOption) {
            case "ALL" -> showAllTransactions();
            case "DEPOSITS" -> showOnlyDeposits();
            case "PAYMENTS" -> showOnlyPayments();
            case "REPORTS" -> showReportsScreen();
        }
        return false;
    }

    public void showAllTransactions() {
        ArrayList<Transaction> transactions = dataService.getSortedTransactions();
        Utils.reportTitle("All Transactions");
        Utils.printTransactions(transactions);
    }

    private void showOnlyDeposits() {
        ArrayList<Transaction> deposits = dataService.search("","","","","",false,true);
        Utils.reportTitle("Deposits");
        Utils.printTransactions(deposits);
    }

    private void showOnlyPayments() {
        ArrayList<Transaction> payments = dataService.search("","","","","",true,false);
        Utils.reportTitle("Payments");
        Utils.printTransactions(payments);
    }

    private void showReportsScreen() {
        reports.showReportsScreenOptionsMenu();
        String userOption = reports.receiveUserOption();
        reports.performUserOption(userOption);
    }


}
