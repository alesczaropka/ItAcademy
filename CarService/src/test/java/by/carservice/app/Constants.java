package by.carservice.app;

import by.carservice.app.transport.Transport;
import by.carservice.app.transport.TransportChecked;
import by.carservice.app.transport.TransportType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Constants {
    public final static String TEST_JSON_FILE_FORMAT = "json";
    public final static String TEST_TXT_FILE_FORMAT = "txt";

    public final static String TEST_PATH_FILE = "src/test/resources/";

    public final static String TEST_JSON_FILE_NAME = TEST_JSON_FILE_FORMAT + "/" + "test-transport." + TEST_JSON_FILE_FORMAT;
    public final static String TEST_TXT_FILE_NAME = TEST_TXT_FILE_FORMAT + "/" + "test-transport." + TEST_TXT_FILE_FORMAT;

    public final static String TEST_JSON_VALID_FILE_NAME = TEST_JSON_FILE_FORMAT + "/" + "test-processed-transport." + TEST_JSON_FILE_FORMAT;
    public final static String TEST_JSON_INVALID_FILE_NAME = TEST_JSON_FILE_FORMAT + "/" + "test-invalid-transport." + TEST_JSON_FILE_FORMAT;

    public final static String TEST_TXT_VALID_FILE_NAME = TEST_TXT_FILE_FORMAT + "/" + "test-processed-transport." + TEST_TXT_FILE_FORMAT;
    public final static String TEST_TXT_INVALID_FILE_NAME = TEST_TXT_FILE_FORMAT + "/" + "test-invalid-transport." + TEST_TXT_FILE_FORMAT;


    public static final List<TransportChecked> TEST_EXPECTED_TRANSPORT_LIST = new ArrayList<>(
            Arrays.asList(
                    new TransportChecked(
                            new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14"),
                            "motorcycle, Ninja ZX-14",
                            true),
                    new TransportChecked(
                            new Transport(TransportType.AUTOMOBILE, "Audi Q7"),
                            "automobile, Audi Q7",
                            true),
                    new TransportChecked(
                            new Transport(TransportType.MINIBUS, "Sprinter264"),
                            "minibus, Sprinter264",
                            true),
                    new TransportChecked(
                            null,
                            "motorcycle, Ninja **",
                            false)
            )
    );
    public static final String TEST_TXT_FILE_CONTENT =
            """
                    motorcycle, Ninja ZX-14
                    automobile, Audi Q7
                    minibus, Sprinter264
                    motorcycle, Ninja **""";
    public static final String TEST_EXPECTED_TXT_VALID_FILE_CONTENT =
            """
                    MOTORCYCLE|Ninja ZX-14|10
                    AUTOMOBILE|Audi Q7|20
                    MINIBUS|Sprinter264|30""";
    public static final String TEST_EXPECTED_TXT_INVALID_FILE_CONTENT = "motorcycle, Ninja **";
    public static final String TEST_TXT_WRONG_STRUCTURE_CONTENT = null;
    public static final String TEST_WRONG_MODEL_CONTENT =
            """
                    jeep, Ninja ZX-14
                    automobile, Audi Q7""";

    public static final String TEST_JSON_FILE_CONTENT =
            """
                    [
                      {
                        "type": "motorcycle",
                        "model": "Ninja ZX-14"
                      },
                      {
                        "type": "automobile",
                        "model": "Audi Q7"
                      },
                      {
                        "type": "minibus",
                        "model": "Sprinter264"
                      },
                      {
                        "type": "motorcycle",
                        "model": "Ninja **"
                      }
                    ]""";
    public static final String TEST_EXPECTED_JSON_VALID_FILE_CONTENT =
            """
                    [
                    {"model":"Ninja ZX-14","type":"MOTORCYCLE"},
                    {"model":"Audi Q7","type":"AUTOMOBILE"},
                    {"model":"Sprinter264","type":"MINIBUS"
                    }
                    ]""";
    public static final String TEST_EXPECTED_JSON_INVALID_FILE_CONTENT = "motorcycle, Ninja **";
    public static final String TEST_JSON_WRONG_STRUCTURE_CONTENT = "Wrong json structure";
    public static final String TEST_JSON_WRONG_MODEL_CONTENT =
            """
                    [
                      {
                        "type": "jeep",
                        "model": "Ninja ZX-14"
                      },
                      {
                        "type": "jeep",
                        "model": "Ninja ZX-14"
                      }
                    ]""";
}
