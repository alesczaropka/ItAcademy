package by.carservice.app.reader.implement;

import by.carservice.app.parser.implement.FileParser;
import by.carservice.app.parser.implement.TypeParser;
import by.carservice.app.reader.TransportReader;
import by.carservice.app.reader.TransportReaderException;
import by.carservice.app.transport.TransportChecked;

import java.util.List;

public class FileReaderDistributor implements TransportReader {

    private final String fileName;
    private final String fileFormat;

    public FileReaderDistributor(final String fileName) {
        this.fileName = fileName;
        this.fileFormat = getFileFormat(fileName);
    }

    @Override
    public List<TransportChecked> read() throws TransportReaderException {
        try {
            final FileParser fileParser = TypeParser.valueOf(fileFormat.toUpperCase()).getParser();
            final FileReader fileReader = new FileReader(fileName, fileParser);
            return fileReader.read();
        } catch (final IllegalArgumentException ex){
            throw new TransportReaderException(String.format("Неверный формат файла \".%s\"", fileFormat), ex);
        }
    }

    private String getFileFormat(final String fileName){
        final String[] parts = fileName.split("\\.");
        return parts[parts.length-1];
    }
}
