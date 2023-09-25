package by.carservice.app.transport.rules;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public final class ModelValidator {
    public static final Predicate<String> MODEL_VALIDATOR = Pattern.compile("^[A-Za-z]+(.[A-Za-z0-9-_]*)+[A-Za-z0-9]$").asPredicate();
}
