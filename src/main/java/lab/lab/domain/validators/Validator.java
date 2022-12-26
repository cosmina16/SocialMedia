package lab.lab.domain.validators;

import lab.lab.domain.Entity;

public interface Validator<T extends Entity> {
    void validate(T a) throws ValidatorException;
}
