package bank.system.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = {PasswordValidator.class})
@Target( { METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    int minLength() default 4;
    int maxLength() default 50;
    boolean containsDigit() default true;
    boolean containsLowercase() default true;
    boolean containsUppercase() default true;
    boolean containsSpecialSymbol() default true;

    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
