package com.mkyong.methods;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInputService {

    public int readInt() {
        Scanner scanner = new Scanner(System.in);
        int resultInt;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("That is not a number!");
                scanner.next();
            }
            resultInt = scanner.nextInt();
        } while (resultInt <= 0);
        return resultInt;
    }

    public double readDouble() {
        Scanner scanner = new Scanner(System.in);
        double resultDouble;
        do {
            System.out.println("Please enter a positive and not zero");
            while (!scanner.hasNextDouble()) {
                System.out.println("That is not a Double!");
                scanner.next();
            }
            resultDouble = scanner.nextDouble();
        } while (resultDouble < 0);

        return resultDouble;
    }

    public String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
