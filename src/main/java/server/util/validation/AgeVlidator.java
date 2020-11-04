package server.util.validation;

import server.model.Employee;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class AgeVlidator implements ConstraintValidator<Age, Employee> {
    @Override
    public boolean isValid(Employee value, ConstraintValidatorContext context) {
        Period p = Period.between(value.getBirthDate().toLocalDate(), LocalDateTime.now().toLocalDate());
        return p.getYears() == value.getAge();
    }

    @Override
    public void initialize(Age constraintAnnotation) {

    }
}
