package lab.lab.domain.validators;

import lab.lab.domain.User;

public class UserValidator implements Validator<User>{
    @Override
    public void validate(User a) throws ValidatorException {
        if (a == null)
            throw new ValidatorException("Utilizator null");
        if (a.getName().equals(""))
            throw new ValidatorException("Numele e string gol");
        if (a.getName() == null)
            throw new ValidatorException("Nume null");
        if (a.getAge()<0)
            throw new ValidatorException("Varsta negativa");


    }
}
