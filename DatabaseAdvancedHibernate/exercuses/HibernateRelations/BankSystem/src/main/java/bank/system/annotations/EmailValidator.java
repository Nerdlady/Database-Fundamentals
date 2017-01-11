package bank.system.annotations;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<Email, String> {

    private String message;

    public EmailValidator() {

    }

    public void initialize(Email constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+@([A-Za-z-]+)\\.([A-Za-z]+)$");
        Matcher matcher = pattern.matcher(value);
        if (!matcher.find()) {

            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(this.message)
                    .addConstraintViolation();
            return false;
        }

        return true;

    }
}
