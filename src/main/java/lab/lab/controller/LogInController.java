package lab.lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lab.lab.App;
import lab.lab.domain.User;
import lab.lab.service.ServiceUser;

import java.io.IOException;

public class LogInController extends AbstractController{
    @FXML
    private TextField nume;
    @FXML
    private PasswordField parola;
    @FXML
    private Text rezultat;

    @FXML
    public void logIn() throws IOException {
        User u = new User(nume.getText(), 0, String.valueOf(parola.getText().hashCode()));
        for(User user: su.getAllUsers())
            if(user.getName().equals(u.getName()) && user.getPassword().equals(u.getPassword()))
            {
                su.setUserCurent(user);
                App.changeRoot("meniu_principal");
                return;
            }
        rezultat.setText("NU EXISTA UTILIZATOR CU ACEST USERNAME SI PAROLA");
        rezultat.setFill(Color.RED);
    }
    @FXML
    private void changeToRegister() throws IOException {
        App.changeRoot("inregistrare");
        System.out.println(su.getAllUsers());
    }
}
