package by.itacademy.database.postgres.connector;

public class DBConnectionException extends Exception {
    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
