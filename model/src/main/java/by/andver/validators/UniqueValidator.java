package by.andver.validators;

import by.andver.annotations.Unique;
import by.andver.interfaces.FieldValueExists;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique,Object> {

    private FieldValueExists service;

    @Override
    public void initialize(Unique unique) {
        Class<? extends FieldValueExists> clazz = unique.service();
        String serviceQualifier = unique.serviceQualifier();

        if (!serviceQualifier.equals("")) {
            this.service = (FieldValueExists) ApplicationContextProvider.getBean(serviceQualifier, clazz);
        } else {
            this.service = (FieldValueExists) ApplicationContextProvider.getBean(clazz);
        }
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return !this.service.fieldValueExists(o);
    }
}
