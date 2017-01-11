package bank.system.annotations;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {

   private int minLength;
   private int maxLength;
   private boolean containsDigit;
   private boolean containsLowercase;
   private boolean containsUppercase;
   private boolean containsSpecialSymbol;
   private String massage;

    public PasswordValidator() {
    }

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.containsDigit = password.containsDigit();
        this.containsLowercase = password.containsLowercase();
        this.containsUppercase = password.containsUppercase();
        this.containsSpecialSymbol = password.containsSpecialSymbol();
        this.massage = password.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(this.massage)
                .addConstraintViolation();
        if (s.length() < this.minLength || s.length() > this.maxLength){
           return false;
        }

        if (this.containsDigit){
            if (!s.matches(".*[0-9].*")){
                return false;
            }
        }

        if (this.containsUppercase){
            if (!s.matches(".*[A-Z].*")){
                return false;
            }
        }


        if (this.containsLowercase){
            if (!s.matches(".*[a-z].*")){
                return false;
            }
        }

        if (this.containsSpecialSymbol){
            if (!s.matches(".*[!@#$%^&*()_+<>?)].*")){
                return false;
            }
        }

        return true;
    }
}
