package by.carservice.app.file.writer.implement;

import by.carservice.app.file.writer.TransportWriterException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JSONWriter extends FileWriter {

    public JSONWriter(final String validTransportFileName, final String invalidTransportFileName) {
        super(validTransportFileName, invalidTransportFileName);
    }

    public <T> void write(final String transportFileWay, final List<T> content) throws TransportWriterException {
        try (final BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(transportFileWay, StandardCharsets.UTF_8))) {
            final JSONArray jsonTransportArray = new JSONArray();

            for (final T value : content) {
                final String[] TransportObjectParts = value.toString().split(", ");

                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", TransportObjectParts[0]);
                jsonObject.put("model", TransportObjectParts[1]);
                if (TransportObjectParts.length == 3) {
                    jsonObject.put("price", TransportObjectParts[2]);
                }
                jsonTransportArray.put(jsonObject);
            }
            writer.write(String.valueOf(jsonTransportArray.toString(jsonTransportArray.length())));
        } catch (final Exception ex) {
            throw new TransportWriterException("Ошибка при записи файла " + new File(transportFileWay).getAbsolutePath(), ex);
        }
    }
}
