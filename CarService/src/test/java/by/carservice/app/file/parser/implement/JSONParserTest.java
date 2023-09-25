package by.carservice.app.file.parser.implement;

import by.carservice.app.file.parser.TransportParserException;
import by.carservice.app.transport.TransportChecked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.carservice.app.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONParserTest {
    private JSONParser parser;

    @BeforeEach
    public void init() {
        parser = new JSONParser();
    }

    @Test
    public void parse_content_from_file() throws TransportParserException {
        final List<TransportChecked> transportList = parser.parse(TEST_JSON_FILE_CONTENT);

        assertEquals(TEST_EXPECTED_TRANSPORT_LIST, transportList);
    }

    @Test
    public void parse_content_from_empty_file() {
        final TransportParserException exception =
                Assertions.assertThrows(TransportParserException.class, () -> parser.parse(""));

        assertEquals("Файл пуст", exception.getMessage());
    }

    @Test
    public void parse_wrong_json_content_from_file() {
        final TransportParserException exception =
                Assertions.assertThrowsExactly(TransportParserException.class, () -> parser.parse(TEST_JSON_WRONG_STRUCTURE_CONTENT));

        assertEquals("Структура данных не соответсвует массиву JSON", exception.getMessage());
    }

    @Test
    public void parse_wrong_content_from_file() {
        final TransportParserException exception =
                Assertions.assertThrowsExactly(TransportParserException.class, () -> parser.parse(TEST_JSON_WRONG_MODEL_CONTENT));

        assertEquals("Ошибка определения типа транспорта", exception.getMessage());
    }
}