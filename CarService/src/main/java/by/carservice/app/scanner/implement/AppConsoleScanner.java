package by.carservice.app.scanner.implement;

import by.carservice.app.scanner.ConsoleScanner;
import by.carservice.app.scanner.ConsoleScannerException;

import java.util.Scanner;

public class AppConsoleScanner implements ConsoleScanner {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String nextLine() throws ConsoleScannerException {
        return scanner.nextLine();
    }
}
