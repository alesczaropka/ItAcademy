package by.carservice.app.parser;

import by.carservice.app.reader.TransportReaderException;
import by.carservice.app.transport.TransportChecked;

import java.util.List;

public interface ContentParser {

    List<TransportChecked> parse(final String content) throws TransportParserException, TransportReaderException;
}
