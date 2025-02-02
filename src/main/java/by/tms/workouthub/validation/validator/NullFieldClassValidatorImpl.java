package by.tms.workouthub.validation.validator;

import by.tms.workouthub.exceptions.AccessDeniedException;
import by.tms.workouthub.exceptions.ValidationException;
import java.lang.reflect.Field;

public class NullFieldClassValidatorImpl implements DtoClassValidator {

    public void validate(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(object)!= null) {
                    return;
                }
            } catch (IllegalAccessException e) {
                throw new AccessDeniedException("Field access error" + field.getName());
            }
        }
        throw new ValidationException("All fields of the object null");
        }
}
