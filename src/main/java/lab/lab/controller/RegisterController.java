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

public class RegisterController extends AbstractController {
    @FXML
    private TextField nume;
    @FXML
    private TextField varsta;
    @FXML
    private PasswordField parola;
    @FXML
    private PasswordField confirmare;
    @FXML
    private Text rezultat;
    @FXML
    private void createAccount() {
        for (User u : su.getAllUsers())
            if (u.getName().equals(nume.getText())) {
                rezultat.setText("EXISTA DEJA UN UTILIZATOR CU ACEST NUME");
                rezultat.setFill(Color.RED);
                return;
            }
        if (!parola.getText().equals(confirmare.getText())) {
            rezultat.setText("CELE DOUA PAROLE NU COINCID");
            rezultat.setFill(Color.RED);
            return;
        }
        try {
            int varstaint = Integer.parseInt(varsta.getText());
            if(varstaint < 0 ) {
                rezultat.setText("VARSTA NU POATE SA FIE NEGATIVA");
                rezultat.setFill(Color.RED);
                return;
            }
            su.createUser(nume.getText(), varstaint, parola.getText());
            rezultat.setText("CONTUL A FOST CREAT CU SUCCES");
            rezultat.setFill(Color.GREEN);
        }
        catch (NumberFormatException e){
            rezultat.setText("VARSTA TREBUIE SA FIE UN NUMAR");
            rezultat.setFill(Color.RED);
        }
    }

    @FXML
    private void changeToLogIn() throws IOException {
        App.changeRoot("autentificare");
        System.out.println(su.getAllUsers());
    }
}
