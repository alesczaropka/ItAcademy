package by.carservice.app.transport;

import java.util.Objects;

public class TransportChecked {

    private final Transport transport;
    private final String valuesTransportString;
    private final boolean isValid;

    public TransportChecked(final Transport transport, final String valuesTransportString, final boolean isValid) {
        this.transport = transport;
        this.valuesTransportString = valuesTransportString;
        this.isValid = isValid;
    }

    public boolean isValid() {
        return isValid;
    }

    public Transport getTransport() {
        return transport;
    }

    public String getValuesTransportString() {
        return valuesTransportString;
    }

    @Override
    public String toString() {
        return "TransportChecked{" +
                "transport=" + transport +
                ", valuesTransportString='" + valuesTransportString + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportChecked that = (TransportChecked) o;

        if (isValid != that.isValid) return false;
        if (!Objects.equals(transport, that.transport)) return false;
        return Objects.equals(valuesTransportString, that.valuesTransportString);
    }

    @Override
    public int hashCode() {
        int result = transport != null ? transport.hashCode() : 0;
        result = 31 * result + (valuesTransportString != null ? valuesTransportString.hashCode() : 0);
        result = 31 * result + (isValid ? 1 : 0);
        return result;
    }
}
