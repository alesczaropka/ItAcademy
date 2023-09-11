package by.carservice.app.sorting;

import by.carservice.app.transport.Transport;

import java.util.Comparator;

public class Sorting {

    private final SortingType sortingType;
    private final SortingDirection sortingDirection;

    public Sorting(final SortingType sortingType, final SortingDirection sortingDirection) {
        this.sortingType = sortingType;
        this.sortingDirection = sortingDirection;
    }

    public SortingType getSortingType() {
        return sortingType;
    }

    public Comparator<Transport> getComparator() {
        return sortingDirection.transform(sortingType.getTransportComparator());
    }
}
