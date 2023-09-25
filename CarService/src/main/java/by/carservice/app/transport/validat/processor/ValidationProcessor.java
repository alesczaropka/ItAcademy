package by.carservice.app.transport.validat.processor;

public interface ValidationProcessor {

    boolean validate(Object object) throws ValidationProcessorException;
}
