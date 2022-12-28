package lab.lab.controller.tableviews;

public class UserView {
    private String nume;
    private int age;
    private String status;

    public UserView(String nume, int age, String status) {
        this.nume = nume;
        this.age = age;
        this.status = status;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
