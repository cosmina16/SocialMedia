package lab.lab.controller;

import javafx.fxml.FXML;
import lab.lab.App;
import lab.lab.service.ServiceUser;

import java.io.IOException;

public class LogInController extends AbstractController{

    @FXML
    private void changeToRegister() throws IOException {
        App.changeRoot("inregistrare");
        System.out.println(su.getAllUsers());
    }
}
