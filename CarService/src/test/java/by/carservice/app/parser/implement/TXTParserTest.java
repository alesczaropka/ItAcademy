package by.carservice.app.parser.implement;

import by.carservice.app.parser.TransportParserException;
import by.carservice.app.transport.TransportChecked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.carservice.app.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TXTParserTest {
    private TXTParser parser;

    @BeforeEach
    public void init() {
        parser = new TXTParser();
    }

    @Test
    public void parse_content_from_file() throws TransportParserException {
        final List<TransportChecked> transportList = parser.parse(TEST_TXT_FILE_CONTENT);

        assertEquals(TEST_EXPECTED_TRANSPORT_LIST, transportList);
    }

    @Test
    public void parse_content_from_empty_file() {
        final TransportParserException exception =
                Assertions.assertThrows(TransportParserException.class, () -> parser.parse(""));

        assertEquals("Файл пуст", exception.getMessage());
    }

    @Test
    public void parse_wrong_txt_content_from_file() {
        final TransportParserException exception =
                Assertions.assertThrowsExactly(TransportParserException.class, () -> parser.parse(TEST_TXT_WRONG_STRUCTURE_CONTENT));

        assertEquals("Ошибка анализа прочтенного файла", exception.getMessage());
    }

    @Test
    public void parse_wrong_content_from_file() {
        final TransportParserException exception =
                Assertions.assertThrowsExactly(TransportParserException.class, () -> parser.parse(TEST_WRONG_MODEL_CONTENT));

        assertEquals("Ошибка определения типа транспорта", exception.getMessage());
    }
}