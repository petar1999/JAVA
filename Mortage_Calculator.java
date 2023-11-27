

import java.text.NumberFormat;
import java.util.Scanner;

public class Mortage_Calculator {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {


int principal= (int) readNumber("Principal: ", 1000, 1000000);
float annualInterest= (float) readNumber("Annual Interest Rate: ", 1, 30);
byte years = (byte) readNumber("Period: ", 1, 30);

        printMortage(principal, annualInterest, years);

        printPaymentSchedule(years, principal, annualInterest);

    }

    private static void printMortage(int principal, float annualInterest, byte years) {
        double mortage = calculateMortage(principal, annualInterest, years);
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String result = currency.format(mortage);
        System.out.println();
        System.out.println("MORTAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + result);
    }

    private static void printPaymentSchedule(byte years, int principal, float annualInterest) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("------------");
        for(short month = 1; month <= years * MONTHS_IN_YEAR; month++){
            double balace = calculateBalance(principal, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balace));
        }
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if(value>=min && value<=max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }
    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsMade){


        float numberOfPayments = years * MONTHS_IN_YEAR;
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;

        double balance = principal
                * (Math.pow(1+monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1+monthlyInterest, numberOfPayments) - 1);

        return balance;
    }

    public static double calculateMortage(
            int principal,
            float annualInterest,
            byte years){



        float numberOfPayments = years * MONTHS_IN_YEAR;
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;

        double mortage = principal*
                (monthlyInterest*Math.pow(1+monthlyInterest,numberOfPayments))
                /(Math.pow(1+monthlyInterest,numberOfPayments)-1);

        return mortage;
    }

}