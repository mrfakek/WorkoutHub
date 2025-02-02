package by.tms.workouthub.configuration;

import by.tms.workouthub.validation.annotation.NonNullFieldValidation;
import by.tms.workouthub.validation.validator.AnnotationBaseValidatorImpl;
import by.tms.workouthub.validation.validator.DtoClassValidator;
import by.tms.workouthub.validation.validator.MyValidator;
import by.tms.workouthub.validation.validator.NullFieldClassValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableAspectJAutoProxy
public class ValidationConfiguration {

    @Bean
    public MyValidator getDtoValidator() {
        Map<Class<? extends Annotation>, DtoClassValidator> dtoValidatorMap = new HashMap<>();
        dtoValidatorMap.put(NonNullFieldValidation.class, new NullFieldClassValidatorImpl());
        return new AnnotationBaseValidatorImpl(dtoValidatorMap);
    }
}
