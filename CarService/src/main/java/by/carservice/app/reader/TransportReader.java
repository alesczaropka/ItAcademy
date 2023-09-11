package by.carservice.app.reader;

import by.carservice.app.transport.TransportChecked;

import java.util.List;

public interface TransportReader {
    List<TransportChecked> read() throws TransportReaderException;
}
