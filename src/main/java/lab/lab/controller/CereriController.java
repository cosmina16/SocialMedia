package lab.lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lab.lab.App;

import java.io.IOException;

public class CereriController extends AbstractController{
    @FXML
    private Button back;

    @FXML
    private void backToMainMenu() throws IOException {
        App.changeRoot("meniu_principal");
    }
}
