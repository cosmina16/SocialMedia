package lab.lab.controller;

import javafx.fxml.FXML;
import lab.lab.App;
import lab.lab.service.ServiceUser;

import java.io.IOException;

public class RegisterController extends AbstractController {

    @FXML
    private void changeToLogIn() throws IOException {
        App.changeRoot("autentificare");
        System.out.println(su.getAllUsers());
    }
}
