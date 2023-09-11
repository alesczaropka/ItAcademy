package by.carservice.app.sorting;

import by.carservice.app.transport.Transport;

import java.util.Comparator;
import java.util.function.Function;

public enum SortingType {

    TYPE((transport -> transport.getTransportType().name())),
    MODEL(Transport::getModel),

    PRICE(Transport::getPrice);

    private final Comparator<Transport> transportComparator;

    <T extends Comparable<T>> SortingType(final Function<Transport, T> function) {
        this.transportComparator = Comparator.comparing(function);
    }

    public Comparator<Transport> getTransportComparator() {
        return transportComparator;
    }
}
