package by.itacademy.database.postgres.connector.implement;

import by.itacademy.Application;
import by.itacademy.database.postgres.connector.DBConnection;
import by.itacademy.database.postgres.connector.DBConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static by.itacademy.util.Constant.*;

public class JDBCConnector implements DBConnection {
    @Override
    public Connection open() throws DBConnectionException {
        try {
            final var properties = getProperties();

            return DriverManager.getConnection(
                    properties.getProperty(URL),
                    properties.getProperty(USER),
                    properties.getProperty(PASSWORD)
            );
        } catch (final SQLException e) {
            throw new DBConnectionException("Ошибка подключения к базе данных.", e);
        }
    }

    private static Properties getProperties() throws DBConnectionException {
        try (final InputStream in = Application.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)) {
            final Properties properties = new Properties();
            properties.load(in);

            return properties;
        } catch (final IOException e) {
            throw new DBConnectionException("Ошибка получения данных для доступа к базе данных.", e);
        }
    }
}
