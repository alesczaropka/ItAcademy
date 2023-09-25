package by.carservice.app;

import by.carservice.app.file.TransportFileController;
import by.carservice.app.file.writer.TransportWriterException;
import by.carservice.app.scanner.implement.AppConsoleScanner;
import by.carservice.app.sorting.SortingReader;
import by.carservice.app.sorting.SortingReaderException;
import by.carservice.app.sorting.implement.ConsoleSortingReader;
import by.carservice.app.transport.Transport;
import by.carservice.app.transport.TransportChecked;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import static by.carservice.app.utils.Constants.*;

public class Menu {
    private static final TransportFileController transportFileController = new TransportFileController(TRANSPORT_FILE_NAME);

    public static void run() {
        try {
            while (true) {
                final List<TransportChecked> transportList = transportFileController.getTransportReader().read();
                System.out.print(
                        """
                                _____________________________________________________________
                                |Меню:                                                      |
                                |1. Записать результаты диагностики в файл без сортировки.  |
                                |2. Отсортировать и записать результаты диагностики в файл. |
                                |-----------------------------------------------------------|
                                |-----------------------------------------------------------|
                                |9. Выход.                                                  |
                                |___________________________________________________________|
                                Сделайте выбор ->\s"""
                );
                final AppConsoleScanner scanner = new AppConsoleScanner();
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1 -> {
                        writeFileList(transportList, null);
                        return;
                    }
                    case 2 -> {
                        writeFileSortedList(transportList, scanner);
                        return;
                    }
                    case 9 -> {
                        return;
                    }
                    default -> {
                        System.out.println("*** Неверное значение. Введите чило соответсвующее пункту меню. ***");
                    }
                }
                System.out.println("--------------------\n\n\n");
            }
        } catch (final Exception ex) {
            System.err.println("Ошибка работы программы. " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void writeFileList(final List<TransportChecked> transportList, final Comparator<Transport> comparator) throws TransportWriterException {
        transportFileController.getTransportWriter().write(transportList, comparator);
        getAbsoluteDestination("Обработанные данные.", PATCH + VALID_TRANSPORT_FILE_NAME);
        getAbsoluteDestination("Данные с ошибкой.", PATCH + INVALID_TRANSPORT_FILE_NAME);
    }

    private static void writeFileSortedList(final List<TransportChecked> transportList, final AppConsoleScanner scanner) throws SortingReaderException, TransportWriterException {
        final SortingReader sortingReader = new ConsoleSortingReader();
        final Comparator<Transport> comparator = sortingReader.readSorting(scanner);

        writeFileList(transportList, comparator);
    }

    private static void getAbsoluteDestination(final String description, final String filePatch) {
        System.out.println("Файл: " + new File(filePatch).getAbsoluteFile() + " - " + description);
    }
}
