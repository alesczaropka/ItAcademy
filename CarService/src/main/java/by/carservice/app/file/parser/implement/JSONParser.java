package by.carservice.app.file.parser.implement;

import by.carservice.app.file.parser.TransportParserException;
import by.carservice.app.transport.Transport;
import by.carservice.app.transport.TransportChecked;
import by.carservice.app.transport.TransportType;
import by.carservice.app.transport.rules.Rules;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser extends FileParser {

    @Override
    public List<TransportChecked> parse(final String jsonContent) throws TransportParserException {
        try {
            final String content = jsonContent
                    .lines()
                    .reduce(String::concat)
                    .orElse(null);
            final JSONArray jsonTransportArray = new JSONArray(String.valueOf(content));

            final List<TransportChecked> transportCheckedList = new ArrayList<>();

            jsonTransportArray.forEach(jsonTransportItem ->
                    transportCheckedList.add(mapToPair(jsonTransportItem)));

            return transportCheckedList;
        } catch (final JSONException ex) {
            if (jsonContent.equals("")) {
                throw new TransportParserException("Файл пуст", ex);
            } else {
                throw new TransportParserException("Структура данных не соответсвует массиву JSON", ex);
            }
        } catch (final IllegalArgumentException ex) {
            throw new TransportParserException("Ошибка определения типа транспорта", ex);
        } catch (final RuntimeException ex) {
            throw new TransportParserException("Ошибка анализа прочтенного файла", ex);
        }
    }

    private static TransportChecked mapToPair(final Object objectValues) {
        final JSONObject jsonValues = (JSONObject) objectValues;
        final TransportType transportType = TransportType.valueOf(jsonValues.getString("type").toUpperCase());
        final String model = jsonValues.getString("model");

        final boolean isModelValid = Rules.MODEL_VALIDATOR.test(model);

        final Transport transport = isModelValid ? new Transport(transportType, model) : null;

        return new TransportChecked(
                transport,
                jsonValues.getString("type") + ", " +
                        jsonValues.getString("model"),
                isModelValid
        );
    }
}
