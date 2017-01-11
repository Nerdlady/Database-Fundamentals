package softuni.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Constraint(validatedBy = {TagValidator.class})
@Target( { METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Tag {
    String message() default "Invalid tag";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
