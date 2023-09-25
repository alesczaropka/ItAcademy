package by.carservice.app.transport;

import by.carservice.app.transport.validat.annotation.Parameter;

import java.util.Objects;


public class Transport {
    @Parameter(pattern = "^[A-Za-z]{7,10}$")
    private final TransportType transportType;

    @Parameter(pattern = "^[A-Za-z]+(.[A-Za-z0-9-_]*)+[A-Za-z0-9]$")
    private final String model;

    @Parameter(pattern = "^[0-9]{1,4}$")
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
