package lab.lab.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lab.lab.App;
import lab.lab.service.ServiceUser;

import java.io.IOException;

public class MainMenuController extends AbstractController{
    @FXML
    private Text utilizator;

    @Override
    public void setSu(ServiceUser su) {
        super.setSu(su);
        utilizator.setText("LOGAT CA "+su.getUserCurent().getName());
    }

    @FXML
    public void logOut() throws IOException {
        App.changeRoot("autentificare");
    }
}
