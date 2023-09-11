package by.carservice.app;

import by.carservice.app.reader.TransportReader;
import by.carservice.app.reader.implement.FileReaderDistributor;
import by.carservice.app.scanner.implement.AppConsoleScanner;
import by.carservice.app.sorting.SortingReader;
import by.carservice.app.sorting.SortingReaderException;
import by.carservice.app.sorting.implement.ConsoleSortingReader;
import by.carservice.app.transport.Transport;
import by.carservice.app.transport.TransportChecked;
import by.carservice.app.writer.TransportWriter;
import by.carservice.app.writer.TransportWriterException;
import by.carservice.app.writer.implement.FileWriter;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class Menu {
    private static final String FORMAT = "json";
    private static final String PATCH = "CarService/src/main/resources/";

    private static final String TRANSPORT_FILE_NAME = FORMAT + "/" + "transport." + FORMAT;
    private static final String VALID_TRANSPORT_FILE_NAME = FORMAT + "/" + "/processed-transport." + FORMAT;
    private static final String INVALID_TRANSPORT_FILE_NAME = FORMAT + "/" + "/invalid-transport." + FORMAT;

    private static final String MENU_MESSAGE = """
            _____________________________________________________________
            |Меню:                                                      |
            |1. Записать результаты диагностики в файл без сортировки.  |
            |2. Отсортировать и записать результаты диагностики в файл. |
            |-----------------------------------------------------------|
            |-----------------------------------------------------------|
            |9. Выход.                                                  |
            |___________________________________________________________|
            Сделайте выбор ->\s""";
    private static final String WRONG_CHOOSE_MESSAGE = """
            *** Неверное значение. Введите чило соответсвующее пункту меню. ***
            \s""";

    public static void run() {
        try {
            while (true) {
                final TransportReader transportReader = new FileReaderDistributor(TRANSPORT_FILE_NAME);
                final List<TransportChecked> transportList = transportReader.read();

                System.out.print(MENU_MESSAGE);
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
                        System.out.println(WRONG_CHOOSE_MESSAGE);
                    }
                }
                System.out.println("--------------------\n\n\n");
            }
        } catch (final Exception ex) {
            System.err.println("Ошибка работы программы. " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void writeFileSortedList(final List<TransportChecked> transportList, final AppConsoleScanner scanner) throws SortingReaderException, TransportWriterException {
        final SortingReader sortingReader = new ConsoleSortingReader();
        final Comparator<Transport> comparator = sortingReader.readSorting(scanner);

        writeFileList(transportList, comparator);
    }

    private static void writeFileList(final List<TransportChecked> transportList, final Comparator<Transport> comparator) throws TransportWriterException {
        final TransportWriter transportWriter = new FileWriter(PATCH + VALID_TRANSPORT_FILE_NAME, PATCH + INVALID_TRANSPORT_FILE_NAME);
        transportWriter.write(transportList, comparator);
        showDestination();
    }

    private static void showDestination() {
        System.out.println(getAbsoluteDestination("Обработанные данные.", PATCH + VALID_TRANSPORT_FILE_NAME));
        System.out.println(getAbsoluteDestination("Данные с ошибкой.", PATCH + INVALID_TRANSPORT_FILE_NAME));
    }

    private static String getAbsoluteDestination(final String description, final String filePatch) {
        return "Файл: " + new File(filePatch).getAbsoluteFile() + " - " + description;
    }
}
