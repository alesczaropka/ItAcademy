package by.itacademy.writer.implemlent;

import by.itacademy.writer.Writer;

import java.util.List;

public class ConsoleWriter implements Writer {

    @Override
    public <T> void write(final List<T> content) {
        for (T element : content) {
            System.out.println(element.toString());
        }
    }
}
