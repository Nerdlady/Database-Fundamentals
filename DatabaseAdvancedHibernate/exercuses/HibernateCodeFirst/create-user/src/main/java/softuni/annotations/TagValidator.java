package softuni.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TagValidator implements ConstraintValidator<Tag, String> {
    private String message;

    @Override
    public void initialize(Tag tag) {
        this.message = tag.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty() || s.contains(" ") || !s.startsWith("#") || s.length() > 20) {
            throw new IllegalArgumentException(this.message);
        }

        return true;
    }
}
