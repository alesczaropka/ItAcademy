package by.carservice.app.file;

import by.carservice.app.file.parser.implement.FileParser;
import by.carservice.app.file.parser.implement.JSONAnnotationParser;
import by.carservice.app.file.parser.implement.JSONParser;
import by.carservice.app.file.parser.implement.TXTParser;
import by.carservice.app.file.writer.implement.FileWriter;
import by.carservice.app.file.writer.implement.JSONWriter;
import by.carservice.app.file.writer.implement.TXTWriter;

import static by.carservice.app.utils.Constants.*;

public enum TypeFile {
    TXT(new TXTParser(), new TXTWriter(PATCH + VALID_TRANSPORT_FILE_NAME, PATCH + INVALID_TRANSPORT_FILE_NAME)),
    JSON(new JSONAnnotationParser(), new JSONWriter(PATCH + VALID_TRANSPORT_FILE_NAME, PATCH + INVALID_TRANSPORT_FILE_NAME));

    private final FileParser parser;
    private final FileWriter writer;

    TypeFile(final FileParser parser, final FileWriter writer) {
        this.parser = parser;
        this.writer = writer;
    }

    public FileParser getParser() {
        return parser;
    }

    public FileWriter getWriter() {
        return writer;
    }
}
