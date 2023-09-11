package by.carservice.app.writer.implement;

import by.carservice.app.transport.Transport;
import by.carservice.app.writer.TransportWriter;
import by.carservice.app.writer.TransportWriterException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.stream.Collectors;

import static by.carservice.app.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FileWriterTest {


    @Test
    public void write_json_processed_without_sorting() throws TransportWriterException {
        final String actualContent = getActualContent(TEST_JSON_VALID_FILE_NAME, TEST_JSON_INVALID_FILE_NAME, null);

        assertEquals(TEST_EXPECTED_JSON_VALID_FILE_CONTENT.lines().reduce(String::concat).orElse(null), actualContent);
    }

    @Test
    public void write_txt_processed_without_sorting() throws TransportWriterException {
        final String actualContent = getActualContent(TEST_TXT_VALID_FILE_NAME, TEST_TXT_INVALID_FILE_NAME, null);

        assertEquals(TEST_EXPECTED_TXT_VALID_FILE_CONTENT, actualContent);
    }

    @Test
    public void write_json_invalid_without_sorting() throws TransportWriterException {
        final String actualContent = getActualContent(TEST_JSON_INVALID_FILE_NAME, TEST_JSON_INVALID_FILE_NAME, null);

        assertEquals(TEST_EXPECTED_JSON_INVALID_FILE_CONTENT, actualContent);
    }

    @Test
    public void write_txt_invalid_without_sorting() throws TransportWriterException {
        final String actualContent = getActualContent(TEST_TXT_INVALID_FILE_NAME, TEST_TXT_INVALID_FILE_NAME, null);

        assertEquals(TEST_EXPECTED_TXT_INVALID_FILE_CONTENT, actualContent);
    }

    private String getActualContent(final String testValidFileName, final String testInvalidFileName, final Comparator<Transport> comparator) throws TransportWriterException {
        final TransportWriter transportWriter = new FileWriter(
                TEST_PATH_FILE + testValidFileName,
                TEST_PATH_FILE + testInvalidFileName);
        transportWriter.write(TEST_EXPECTED_TRANSPORT_LIST, comparator);

        final String actualContent = getLinesFile(testValidFileName);
        return actualContent;
    }

    private String getLinesFile(final String filename) {
        final InputStream in = getClass().getClassLoader().getResourceAsStream(filename);

        assertNotEquals(null, in);

        return new BufferedReader(new InputStreamReader(in))
                .lines().
                collect(Collectors.joining("\n"));
    }
}