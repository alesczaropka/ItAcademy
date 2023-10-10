package by.itacademy;

import by.itacademy.database.postgres.connector.implement.JDBCConnector;
import by.itacademy.reader.implement.JDBCListTransportReader;
import by.itacademy.writer.implemlent.ConsoleWriter;

public class Application {

    public static void main(String[] args) {
        try {
            System.out.println("Программа запущена.");

            final var driverManager = new JDBCConnector();
            final var connection = driverManager.open();

            final var reader = new JDBCListTransportReader();
            final var transportList = reader.read(connection);

            final var writer = new ConsoleWriter();
            writer.write(transportList);

            System.out.println("завершение работы программы.");
        } catch (final Exception e) {
            System.err.println("Ошибка работы программы. " + e.getMessage());
            e.printStackTrace();
        }
    }
}
