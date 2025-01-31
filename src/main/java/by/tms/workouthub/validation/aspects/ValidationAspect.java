package by.tms.workouthub.validation.aspects;

import by.tms.workouthub.validation.validator.NullFieldValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Before(value = "@annotation(by.tms.workouthub.validation.annotation.NonNullFieldValidation)")
    public void validateNonNullField(JoinPoint joinPoint) {
        Object object = joinPoint.getTarget();
        NullFieldValidator.validate(object);

    }
}

