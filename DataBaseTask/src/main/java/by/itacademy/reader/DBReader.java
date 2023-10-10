package by.itacademy.reader;

import by.itacademy.model.Transport;

import java.sql.Connection;
import java.util.List;

public interface DBReader {
    List<Transport> read(final Connection connection) throws DBReaderException;
}
