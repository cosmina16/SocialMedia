package lab.lab.domain.validators;

public class ValidatorException extends Exception{
    String mesaj;

    public ValidatorException(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getMesaj() {
        return mesaj;
    }
}
