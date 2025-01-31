package by.tms.workouthub.validation.validator;

import by.tms.workouthub.validation.annotation.NonNullFieldValidation;
import by.tms.workouthub.exceptions.AllFieldsNullException;

import java.lang.reflect.Field;

public class NullFieldValidator {

    public static void validate(Object object) {
        if (!object.getClass().isAnnotationPresent(NonNullFieldValidation.class)) {
            return;
        }
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(object)!= null) {
                    return;
                }
            } catch (IllegalAccessException e) {
                throw new AllFieldsNullException("Ошибка доступа к полю" + field.getName());
            }
        }
        throw new AllFieldsNullException("Все поля объекта равны null");
        }
}
