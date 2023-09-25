package by.carservice.app.file.parser.implement;

import by.carservice.app.file.parser.TransportParserException;
import by.carservice.app.transport.Transport;
import by.carservice.app.transport.TransportChecked;
import by.carservice.app.transport.TransportType;
import by.carservice.app.transport.validat.processor.ValidationProcessor;
import by.carservice.app.transport.validat.processor.ValidationProcessorException;
import by.carservice.app.transport.validat.processor.implement.ParameterValidationProcessor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONAnnotationParser extends FileParser {

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

    private static TransportChecked mapToPair(final Object objectValues){
        final JSONObject jsonValues = (JSONObject) objectValues;
        final Transport transport;
        final boolean isFieldsValid;

        final String transportTypeToStringFormat = jsonValues.getString("type").toUpperCase();
        final boolean isTransportTypeExist = Arrays.stream(TransportType.values())
                .anyMatch(enumValue -> enumValue.name().equals(transportTypeToStringFormat));

        if (isTransportTypeExist) {
            final TransportType transportType = TransportType.valueOf(transportTypeToStringFormat);
            final String model = jsonValues.getString("model");

            transport = new Transport(transportType, model);
            try {
                final ValidationProcessor validationProcessor = new ParameterValidationProcessor();
                isFieldsValid = validationProcessor.validate(transport);
            } catch (final ValidationProcessorException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            isFieldsValid = false;
            transport = null;
        }
        return new TransportChecked(
                transport,
                jsonValues.getString("type") + ", " + jsonValues.getString("model"),
                isFieldsValid
        );
    }
}
