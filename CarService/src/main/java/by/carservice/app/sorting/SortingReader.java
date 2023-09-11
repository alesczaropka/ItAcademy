package by.carservice.app.sorting;

import by.carservice.app.scanner.implement.AppConsoleScanner;
import by.carservice.app.transport.Transport;

import java.util.Comparator;

public interface SortingReader {

    Comparator<Transport> readSorting(final AppConsoleScanner scanner) throws SortingReaderException;

}
