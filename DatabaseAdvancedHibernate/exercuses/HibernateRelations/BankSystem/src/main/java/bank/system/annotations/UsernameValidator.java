package bank.system.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    private String message;

    @Override
    public void initialize(Username tag) {
        this.message = tag.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.length() < 3){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(this.message)
                    .addConstraintViolation();
            return false;
        }

        Pattern pattern = Pattern.compile("^[A-Za-z][A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(s);

        if (!matcher.find()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(this.message)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
