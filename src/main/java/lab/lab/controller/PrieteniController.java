package lab.lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lab.lab.App;
import lab.lab.controller.tableviews.UserView;
import lab.lab.domain.Friendship;
import lab.lab.domain.StatusFriendship;
import lab.lab.domain.User;

import java.io.IOException;
import java.util.ArrayList;

public class PrieteniController extends AbstractController{
    @FXML
    private TableView<User> tabelPrieteni;

    @FXML
    private TextField cautare;
    @FXML
    private Button stergere;
    @FXML
    private void initialize(){
        TableColumn<User,String> nume = new TableColumn<User,String>("Nume");
        nume.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<User,Integer> age = new TableColumn<User,Integer>("Varsta");
        age.setCellValueFactory(new PropertyValueFactory("age"));

        tabelPrieteni.getColumns().addAll(nume, age);
    }
    @FXML
    private void updateTable(){
        ArrayList<User> list = new ArrayList<>();
        for (User u: su.getAllUsers()) {
            if (!u.getName().toLowerCase().contains(cautare.getText().toLowerCase()))
                continue;
            Friendship fr =sf.getFriendship(u.getId(),su.getUserCurent().getId());
            if (fr ==null)
                continue;
            if(fr.getStatus().equals(StatusFriendship.Active))
                list.add(u);
        }
        tabelPrieteni.getItems().setAll(list);
        updateButton();
    }

    @FXML
    private void deleteFr(){
        int u = tabelPrieteni.getSelectionModel().getSelectedItem().getId();
        sf.deleteFriendship(su.getUserCurent().getId(),u);
        updateTable();
    }
    @Override
    public void myinitialize() {
        super.myinitialize();
        updateTable();
    }

    @FXML
    private void backToMainMenu() throws IOException {
        App.changeRoot("meniu_principal");
    }
    @FXML
    private void updateButton(){
        User u = tabelPrieteni.getSelectionModel().getSelectedItem();
        if(u == null)
            stergere.setDisable(true);
        else
            stergere.setDisable(false);
    }
}
