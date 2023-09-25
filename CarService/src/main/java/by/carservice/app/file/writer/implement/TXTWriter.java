package by.carservice.app.file.writer.implement;

import by.carservice.app.file.writer.TransportWriterException;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TXTWriter extends FileWriter {

    public TXTWriter(final String validTransportFileName, final String invalidTransportFileName) {
        super(validTransportFileName, invalidTransportFileName);
    }

    public <T> void write(final String transportFileWay, final List<T> content) throws TransportWriterException {
        try (final BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(transportFileWay, StandardCharsets.UTF_8))) {
            for (final T value : content) {
                if (value.toString().split(", ").length == 3) {
                    writer.write(value.toString().replace(", ", "|") + "\n");
                } else {
                    writer.write(value.toString() + "\n");
                }
            }
        } catch (final Exception ex) {
            throw new TransportWriterException("Ошибка при записи файла " + new File(transportFileWay).getAbsolutePath(), ex);
        }
    }
}
