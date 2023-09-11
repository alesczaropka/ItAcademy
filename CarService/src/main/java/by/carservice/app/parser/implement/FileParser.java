package by.carservice.app.parser.implement;

import by.carservice.app.parser.ContentParser;
import by.carservice.app.parser.TransportParserException;
import by.carservice.app.reader.TransportReaderException;
import by.carservice.app.transport.TransportChecked;

import java.util.List;

public abstract class FileParser implements ContentParser {
    @Override
    public abstract List<TransportChecked> parse(final String content) throws TransportParserException, TransportReaderException;
}
