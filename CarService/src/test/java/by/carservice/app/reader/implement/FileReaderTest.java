package by.carservice.app.reader.implement;

import by.carservice.app.parser.TransportParserException;
import by.carservice.app.parser.implement.JSONParser;
import by.carservice.app.reader.TransportReader;
import by.carservice.app.reader.TransportReaderException;
import by.carservice.app.transport.TransportChecked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static by.carservice.app.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FileReaderTest {

    @Test
    public void read_not_exist_file() {
        final TransportReader reader = new FileReader("Wrong file name", new JSONParser());

        final TransportReaderException exception = Assertions.assertThrowsExactly(TransportReaderException.class, reader::read);

        assertEquals(String.format("Файл \"%s\" не найден", "Wrong file name"), exception.getMessage());
    }

    @Test
    public void read_content_from_file() throws TransportParserException, TransportReaderException {
        final JSONParser parser = Mockito.mock(JSONParser.class);
        Mockito.when(parser.parse(TEST_JSON_FILE_CONTENT)).thenReturn(TEST_EXPECTED_TRANSPORT_LIST);

        final TransportReader reader = new FileReader(TEST_JSON_FILE_NAME, parser);
        final List<TransportChecked> transportCheckedList = reader.read();

        assertEquals(TEST_EXPECTED_TRANSPORT_LIST, transportCheckedList);
        Mockito.verify(parser).parse(TEST_JSON_FILE_CONTENT);
    }
}