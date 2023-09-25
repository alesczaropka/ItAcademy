package by.carservice.app.file.writer.implement;

import by.carservice.app.file.writer.TransportWriter;
import by.carservice.app.file.writer.TransportWriterException;
import by.carservice.app.transport.Transport;
import by.carservice.app.transport.TransportChecked;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class FileWriter implements TransportWriter {

    protected final String validTransportFileWay;
    protected final String invalidTransportFileWay;

    public FileWriter(final String validTransportFileName, final String invalidTransportFileName) {
        this.validTransportFileWay = validTransportFileName;
        this.invalidTransportFileWay = invalidTransportFileName;
    }

    @Override
    public void write(final List<TransportChecked> transportCheckeds, final Comparator<Transport> comparator
    ) throws TransportWriterException {

        final List<String> invalidTransport = new ArrayList<>();
        final List<Transport> validTransports = new ArrayList<>();

        transportCheckeds.forEach(transportChecked -> {
            if (transportChecked.isValid()) {
                validTransports.add(transportChecked.getTransport());
            } else {
                invalidTransport.add(transportChecked.getValuesTransportString());
            }
        });

        if (comparator != null) {
            validTransports.sort(comparator);
        }

        write(validTransportFileWay, validTransports);
        write(invalidTransportFileWay, invalidTransport);
    }

    public abstract <T> void write(final String transportFileWay, final List<T> content) throws TransportWriterException;
}
