package by.itacademy.reader.implement;

import by.itacademy.model.Client;
import by.itacademy.model.Transport;
import by.itacademy.reader.DBReader;
import by.itacademy.reader.DBReaderException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCListTransportReader implements DBReader {
    @Override
    public List<Transport> read(final Connection connection) throws DBReaderException {
        try {
            final var transportResultSet = getQueryData(connection);
            return getTransportsListFrom(transportResultSet);
        } catch (final SQLException e) {
            throw new DBReaderException("Ошибка анализа данных при получении запроса", e);
        }
    }

    private static List<Transport> getTransportsListFrom(final ResultSet transportResultSet) throws SQLException {
        final List<Transport> transportList = new ArrayList<>();

        while (transportResultSet.next()) {
            final int id = transportResultSet.getInt("id");
            final var transportType = transportResultSet.getString("type_name");
            final var model = transportResultSet.getString("model");
            final var firstName = transportResultSet.getString("first_name");
            final var lastName = transportResultSet.getString("last_name");

            final var client = new Client(firstName, lastName);
            final var transport = new Transport(id, transportType, model, client);

            transportList.add(transport);
        }
        return transportList;
    }

    private static ResultSet getQueryData(final Connection connection) throws DBReaderException {
        try {
            final var query = """
                    SELECT
                        transport.id,
                        transport_type."name" as type_name,
                        model_type."name" as model,
                        client.first_name,
                        client.last_name
                    FROM
                        transport
                    LEFT JOIN
                        transport_type ON transport_type.id = transport.transport_type_id
                    LEFT JOIN
                        model_type ON model_type.id = transport.model_type_id
                    LEFT JOIN
                        client ON client.id = transport.client_id""";

            final var preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (final SQLException e) {
            throw new DBReaderException("Ошибка получения данных при запросе", e);
        }
    }
}