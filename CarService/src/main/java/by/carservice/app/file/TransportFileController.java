package by.carservice.app.file;

import by.carservice.app.file.parser.ContentParser;
import by.carservice.app.file.reader.TransportReader;
import by.carservice.app.file.reader.implement.FileReader;
import by.carservice.app.file.writer.TransportWriter;

public class TransportFileController {
    private final String fileName;
    private final TypeFile typeFile;
    private final TransportReader transportReader;
    private final ContentParser fileParser;
    private final TransportWriter transportWriter;


    public TransportFileController(final String fileName) {
        this.fileName = fileName;
        this.typeFile = getFileFormat(fileName);
        this.fileParser = typeFile.getParser();
        this.transportReader = new FileReader(fileName, fileParser);
        this.transportWriter = typeFile.getWriter();
    }

    public TransportReader getTransportReader() {
        return transportReader;
    }

    public TransportWriter getTransportWriter() {
        return transportWriter;
    }

    private TypeFile getFileFormat(final String fileName) {
        final String[] parts = fileName.split("\\.");
        final String fileType = parts[parts.length - 1];
        return TypeFile.valueOf(fileType.toUpperCase());
    }
}
