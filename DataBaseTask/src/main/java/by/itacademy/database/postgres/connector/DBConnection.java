package by.itacademy.database.postgres.connector;

import java.sql.Connection;

public interface DBConnection {
    Connection open() throws DBConnectionException;
}
