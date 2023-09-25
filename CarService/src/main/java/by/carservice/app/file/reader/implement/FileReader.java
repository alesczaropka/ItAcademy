package by.carservice.app.file.reader.implement;

import by.carservice.app.file.parser.ContentParser;
import by.carservice.app.file.parser.TransportParserException;
import by.carservice.app.file.reader.TransportReader;
import by.carservice.app.file.reader.TransportReaderException;
import by.carservice.app.transport.TransportChecked;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader implements TransportReader {

    private final String fileName;
    private final ContentParser fileParser;

    public FileReader(final String fileName, final ContentParser fileParser) {
        this.fileName = fileName;
        this.fileParser = fileParser;
    }

    @Override
    public List<TransportChecked> read() throws TransportReaderException {
        try {
            final String content = getReader()
                    .lines()
                    .collect(Collectors.joining("\n"));
            return fileParser.parse(content);
        } catch (final TransportParserException ex) {
            throw new TransportReaderException("Ошибка при чтении файла " + fileName, ex);
        }
    }

    private BufferedReader getReader() throws TransportReaderException {
        final var in = getClass().getClassLoader().getResourceAsStream(fileName);
        if (in != null) {
            return new BufferedReader(new InputStreamReader(in));
        }
        throw new TransportReaderException(String.format("Файл \"%s\" не найден:", fileName), null);
    }
}
