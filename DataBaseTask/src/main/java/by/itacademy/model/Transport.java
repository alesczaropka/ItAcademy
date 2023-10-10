package by.itacademy.model;

public class Transport {
    private final int id;
    private final String transportType;
    private final String model;
    private final Client client;

    public Transport(final int id, final String transportType, final String model, final Client client) {
        this.id = id;
        this.transportType = transportType;
        this.model = model;
        this.client = client;
    }

    @Override
    public String toString() {
        return String.format("%2s | %-10s | %-10s | %-20s | ", Integer.toString(id), transportType, model, client.toString());
    }
}
