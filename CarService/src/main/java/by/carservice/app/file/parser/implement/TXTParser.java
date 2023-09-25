package by.carservice.app.file.parser.implement;

import by.carservice.app.file.parser.TransportParserException;
import by.carservice.app.transport.Transport;
import by.carservice.app.transport.TransportChecked;
import by.carservice.app.transport.TransportType;
import by.carservice.app.transport.rules.Rules;

import java.util.List;


public class TXTParser extends FileParser {
    @Override
    public List<TransportChecked> parse(final String txtContent) throws TransportParserException {

        try {
            if (txtContent.equals("")) {
                throw new TransportParserException("Файл пуст", new RuntimeException());
            } else {
                return txtContent.lines()
                        .map(TXTParser::mapToPair)
                        .toList();
            }
        } catch (final IllegalArgumentException ex) {
            throw new TransportParserException("Ошибка определения типа транспорта", ex);
        } catch (final RuntimeException ex) {
            throw new TransportParserException("Ошибка анализа прочтенного файла", ex);
        }

    }

    private static TransportChecked mapToPair(final String valuesTransportString) {
        final String[] parts = valuesTransportString.split(",\\s");

        final TransportType transportType = TransportType.valueOf(parts[0].toUpperCase());
        final String model = parts[1];

        final boolean isModelValid = Rules.MODEL_VALIDATOR.test(model);

        final Transport transport = isModelValid ? new Transport(transportType, model) : null;

        return new TransportChecked(transport, valuesTransportString, isModelValid);
    }
}
