package lab.lab.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lab.lab.App;
import lab.lab.service.ServiceUser;

import java.io.IOException;

public class MainMenuController extends AbstractController{
    @FXML
    private Text utilizator;

    //prima oara se cheama constructorul
    //dupa se leaga chestiile din cod marcate cu @FXML de fisierul ".fxml"
    //se cheama functia initialize
    //dupa se seteaza service-urile
    @Override
    public void myinitialize() {
        utilizator.setText("LOGAT CA "+su.getUserCurent().getName());
    }

    @FXML
    public void logOut() throws IOException {
        App.changeRoot("autentificare");
    }
    @FXML
    public void changeToPrieteni() throws IOException {
        App.changeRoot("prieteni");
    }

    @FXML
    public void changeToCereri() throws IOException {
        App.changeRoot("cereri");
    }

    @FXML
    public void changeToAllUsers() throws IOException {
        App.changeRoot("toti_utilizatorii");
    }
}
