package lab.lab.controller.tableviews;

public class UserView {
    private String nume;
    private int age;
    private String status;
    private int id;


    public UserView(String nume, int age, String status, int id) {
        this.nume = nume;
        this.age = age;
        this.status = status;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
