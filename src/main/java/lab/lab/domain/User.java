package lab.lab.domain;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity{
    private String name;
    private int age;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }



    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("User{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", age=" + age + " }" );
        return str.toString();
    }
}
