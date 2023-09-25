package by.carservice.app.file.writer;

import by.carservice.app.transport.Transport;
import by.carservice.app.transport.TransportChecked;

import java.util.Comparator;
import java.util.List;

public interface TransportWriter {
    void write(List<TransportChecked> transportCheckeds, Comparator<Transport> comparator) throws TransportWriterException;

}
