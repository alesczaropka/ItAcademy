package by.carservice.app.reader.implement;

import by.carservice.app.reader.TransportReader;
import by.carservice.app.reader.TransportReaderException;
import by.carservice.app.transport.TransportChecked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.carservice.app.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderDistributorTest {

    @Test
    public void read_json_file() throws TransportReaderException {
        final TransportReader transportReader = new FileReaderDistributor(TEST_JSON_FILE_NAME);
        final List<TransportChecked> transportList = transportReader.read();

        assertEquals(TEST_EXPECTED_TRANSPORT_LIST, transportList);
    }

    @Test
    public void read_txt_file() throws TransportReaderException {
        final TransportReader transportReader = new FileReaderDistributor(TEST_TXT_FILE_NAME);
        final List<TransportChecked> transportList = transportReader.read();

        assertEquals(TEST_EXPECTED_TRANSPORT_LIST, transportList);
    }

    @Test
    public void read_wrong_file() {
        final TransportReader transportReader = new FileReaderDistributor("Wrong file name");
        final TransportReaderException exception =
                Assertions.assertThrowsExactly(TransportReaderException.class, transportReader::read);

        assertEquals(String.format("Неверный формат файла \".%s\"", "Wrong file name"), exception.getMessage());
    }
}