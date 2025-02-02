package by.tms.workouthub.validation.aspects;

import by.tms.workouthub.validation.validator.MyValidator;
import by.tms.workouthub.validation.validator.NullFieldClassValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Aspect
@Component
public class ValidationAspect {

    private final MyValidator validator;

    public ValidationAspect(MyValidator validator) {
        this.validator = validator;
    }

    @Before(value = "@annotation(by.tms.workouthub.validation.annotation.ValidateThis)")
    public void validate(JoinPoint joinPoint) {
        Stream.of(joinPoint.getArgs()).forEach(validator::validate);
    }
}

