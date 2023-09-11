package by.carservice.app.parser.implement;

public enum TypeParser {
    TXT (new TXTParser()),
    JSON(new JSONParser());

    private final FileParser parser;

    TypeParser(final FileParser parser) {
        this.parser = parser;
    }

    public FileParser getParser() {
        return parser;
    }
}
