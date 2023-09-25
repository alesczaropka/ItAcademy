package by.carservice.app.transport.validat.processor.implement;

import by.carservice.app.transport.validat.annotation.Parameter;
import by.carservice.app.transport.validat.processor.ValidationProcessor;
import by.carservice.app.transport.validat.processor.ValidationProcessorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ParameterValidationProcessor implements ValidationProcessor {
    @Override
    public boolean validate(final Object object) throws ValidationProcessorException {
        boolean isValidField = false;

        for (final Field field : object.getClass().getDeclaredFields()) {
            isValidField = processField(field, object);

            if (!isValidField) {
                break;
            }
        }
        return isValidField;
    }

    private boolean processField(final Field field, final Object object) throws ValidationProcessorException {
        for (final Annotation annotation : field.getDeclaredAnnotations()) {
            if (!(annotation instanceof Parameter)) {
                continue;
            }

            final Parameter parameter = (Parameter) annotation;

            if (!field.canAccess(object) && !field.trySetAccessible()) {
                continue;
            }

            try {
                final Object fieldValue = field.get(object);
                final String value;
                final Predicate<String> predicate = Pattern.compile(parameter.pattern()).asPredicate();

                switch (fieldValue.getClass().getSimpleName()) {
                    case "String" -> {
                        value = (String) fieldValue;
                    }
                    case "TransportType" -> {
                        value = fieldValue.toString();
                    }
                    case "Integer" -> {
                        value = String.valueOf(fieldValue);
                    }
                    default -> {
                        return false;
                    }
                }
                if (predicate.test(value)) {
                    return true;
                }
            } catch (final IllegalAccessException ex) {
                throw new ValidationProcessorException("Failed to process Parameter annotation", ex);
            }
        }
        return false;
    }

}

