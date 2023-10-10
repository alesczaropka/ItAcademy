package by.itacademy.model;

public class Client {
    private final String firstName;
    private final String lastName;

    public Client(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s", firstName, lastName);
    }
}
