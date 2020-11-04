package server.util.validation;

import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AgeVlidator.class})
public @interface Age {
    String message() default "Author is not allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
