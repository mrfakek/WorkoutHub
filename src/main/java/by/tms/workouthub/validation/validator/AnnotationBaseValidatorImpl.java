package by.tms.workouthub.validation.validator;

import by.tms.workouthub.exceptions.ValidationException;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

public class AnnotationBaseValidatorImpl implements MyValidator {
    Map<Class<? extends Annotation>, DtoClassValidator> validationFunctions;
    Set<Class<? extends Annotation>> supportedAnnotations;

    public AnnotationBaseValidatorImpl(Map<Class<? extends Annotation>, DtoClassValidator> validationFunctions) {
        this.validationFunctions = validationFunctions;
        this.supportedAnnotations = validationFunctions.keySet();
    }

    @Override
    public void validate(Object object) {
        if (object == null) {
            throw new ValidationException("Object is null");
        }
        Class<?> clazz = object.getClass();
        supportedAnnotations
                .stream()
                .filter(clazz::isAnnotationPresent)
                .map(validationFunctions::get)
                .forEach(DtoClassValidator -> DtoClassValidator.validate(object));
    }
}
