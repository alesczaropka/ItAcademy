package by.carservice.app.writer.implement;

import by.carservice.app.transport.Transport;
import by.carservice.app.writer.TransportWriter;
import by.carservice.app.writer.TransportWriterException;
import by.carservice.app.transport.TransportChecked;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileWriter implements TransportWriter {

    private final String validTransportFileWay;
    private final String invalidTransportFileWay;

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

    private <T> void write(final String transportFileWay, final List<T> content) throws TransportWriterException {
        try (final BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(transportFileWay, StandardCharsets.UTF_8))) {
            switch (getFileFormat(transportFileWay)) {
                case "txt" -> {
                    for (final T value : content) {
                        writer.write(value.toString() + "\n");
                    }
                }
                case "json" -> {
                    final JSONArray jsonArray = new JSONArray();
                    for (final T value : content) {
                        final String[] validParts = value.toString().split("\\|");
                        if (validParts.length > 1) {
                            final JSONObject jsonObject = new JSONObject();
                            jsonObject.put("type", validParts[0]);
                            jsonObject.put("model", validParts[1]);
                            jsonArray.put(jsonObject);
                        } else {
                            final String[] invalidParts = value.toString().split(",\\s");
                            if (invalidParts.length > 0) {
                                writer.write(value + "\n");
                            }
                        }
                    }
                    if (jsonArray.length() > 0){
                        writer.write(jsonArray.toString());
                    }
                }
            }
        } catch (final Exception ex) {
            throw new TransportWriterException("Ошибка при записи файла " + new File(transportFileWay).getAbsolutePath(), ex);
        }
    }


    private String getFileFormat(final String fileName){
        final String[] parts = fileName.split("\\.");
        return parts[parts.length-1];
    }
}
