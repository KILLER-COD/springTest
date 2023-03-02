package com.mkyong.methods;

import java.util.Scanner;

public class ConsoleInputService {
    public int readInt() {
        Scanner scanner = new Scanner(System.in);
        int resultInt = scanner.nextInt();
        scanner.close();
        return resultInt;
    }

    public double readDouble() {
        Scanner scanner = new Scanner(System.in);
        double resultDouble = scanner.nextDouble();
        scanner.close();
        return resultDouble;
    }

    public String readString() {
        Scanner scanner = new Scanner(System.in);
        String resultString = scanner.nextLine();
        scanner.close();
        return resultString;
    }
}
