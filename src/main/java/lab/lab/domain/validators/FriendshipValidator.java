package lab.lab.domain.validators;

import lab.lab.domain.Friendship;

public class FriendshipValidator implements Validator<Friendship>{
    @Override
    public void validate(Friendship a) throws ValidatorException {
        if (a.getIdUser1() == a.getIdUser2())
            throw new ValidatorException("Id-urile nu pot fi identice");
    }
}
