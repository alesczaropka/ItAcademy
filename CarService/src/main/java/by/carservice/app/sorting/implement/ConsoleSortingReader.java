package by.carservice.app.sorting.implement;

import by.carservice.app.scanner.ConsoleScannerException;
import by.carservice.app.scanner.implement.AppConsoleScanner;
import by.carservice.app.sorting.*;
import by.carservice.app.transport.Transport;

import java.util.*;

public class ConsoleSortingReader implements SortingReader {

    private static final String SORTING_MESSAGE = "Введите поле для сортировки, одно из:"
            + "\n   " + Arrays.toString(SortingType.values())
            + "\nи через пробел направление сортировки, одно из:"
            + "\n   " + Arrays.toString(SortingDirection.values())
            + "\nили нажмите 'Enter' для завершения ввода.";

    @Override
    public Comparator<Transport> readSorting(final AppConsoleScanner scanner) throws SortingReaderException {
        try {
            final List<Sorting> sortingList = new ArrayList<>(SortingType.values().length);

            while (sortingList.size() < SortingType.values().length) {
                final Sorting sorting = readSortingInfo(scanner);
                if (sorting == null) {
                    break;
                }

                final SortingType sortingType = sorting.getSortingType();
                final boolean hasSorting = sortingList.stream().anyMatch(s -> s.getSortingType().equals(sortingType));

                if (hasSorting) {
                    System.out.println("Сортировка " + sortingType + " уже добавлена, выберите другую сортировку.");
                } else {
                    sortingList.add(sorting);
                }
            }
            System.out.println("Введено типов сортировок: " + sortingList.size());

            return sortingList.stream()
                    .map(Sorting::getComparator)
                    .reduce((t1, t2) -> 0, Comparator::thenComparing);
        } catch (final RuntimeException ex) {
            throw new SortingReaderException("Ошибка чтения сортировки для транспорта", ex);
        } catch (final ConsoleScannerException ex) {
            throw new SortingReaderException("Ошибка чтения консоли", ex);
        }
    }

    private static Sorting readSortingInfo(final AppConsoleScanner scanner) throws ConsoleScannerException {
        System.out.println(SORTING_MESSAGE);
        final String sorting = scanner.nextLine();

        if (sorting == null || sorting.isEmpty()) {
            return null;
        }

        final String[] parts = sorting.split("\\s");
        final SortingType sortingType = SortingType.valueOf(parts[0].toUpperCase());
        final SortingDirection sortingDirection = SortingDirection.valueOf(parts[1].toUpperCase());

        return new Sorting(sortingType, sortingDirection);
    }

}
