package by.itacademy.writer;

import java.sql.SQLException;
import java.util.List;

public interface Writer {

    <T> void write(final List<T> content) throws WriterException, SQLException;
}
