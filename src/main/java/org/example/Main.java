package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        getStarted();
    }
    public static void getStarted() throws SQLException {
        int Option;
        System.out.println("""
                1 -> Login
                2 -> SignUp
                3 -> View Product Details
                """);
        Scanner scanner = new Scanner(System.in);
        Option = scanner.nextInt();
        switch (Option) {
            case 1 -> new Authentication().login();
            case 2 -> new Authentication().signUp();
            case 3 -> new Buyer().viewOurProduct("View");
            default -> {
                System.out.println("""
                        Selected is not a Valid Option
                        Please Select Another One
                        """);
                throw new IllegalArgumentException();

            }
        }
    }
}