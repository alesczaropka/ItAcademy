package by.carservice.app.transport;

import java.util.Objects;

public class Transport {

    private final TransportType transportType;
    private final String model;
    private final int price;

    public Transport(final TransportType transportType, final String model) {
        this.transportType = transportType;
        this.model = model;
        this.price = transportType.getPrice();
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.join(", ",
                transportType.name(),
                model,
                String.valueOf(price)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        if (price != transport.price) return false;
        if (transportType != transport.transportType) return false;
        return Objects.equals(model, transport.model);
    }

    @Override
    public int hashCode() {
        int result = transportType.hashCode();
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
