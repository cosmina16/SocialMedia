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

public class AllUsersController extends AbstractController{
    @FXML
    private Button back;
    @FXML
    private TextField cautare;
    @FXML
    private TableView<UserView> tabel;
    @FXML
    private Button trimitereCerere;

    //un fel de constructor
    @FXML
    private void initialize(){
        TableColumn<UserView,String> nume = new TableColumn<UserView,String>("Nume");
        nume.setCellValueFactory(new PropertyValueFactory("nume"));

        TableColumn<UserView,Integer> age = new TableColumn<UserView,Integer>("Varsta");
        age.setCellValueFactory(new PropertyValueFactory("age"));

        TableColumn<UserView,String> status = new TableColumn<UserView,String>("Status");
        status.setCellValueFactory(new PropertyValueFactory("status"));

        tabel.getColumns().addAll(nume, age, status);
    }
    @FXML
    private void updateTable(){
        ArrayList<UserView> list = new ArrayList<>();
        for (User u: su.getAllUsers()) {
            if (su.getUserCurent().getId() == u.getId())
                continue;
            if (!u.getName().toLowerCase().contains(cautare.getText().toLowerCase()))
                continue;
            //prieteni , blocat, in asteptare, neprieteni
            Friendship f = sf.getFriendship(u.getId(), su.getUserCurent().getId());
            String status;
            if (f == null)
                status = "neprieteni";
            else if (f.getStatus().equals(StatusFriendship.Active))
                status = "prieteni";
            else if (f.getStatus().equals(StatusFriendship.Pending))
                status = "in asteptare";
            else
                status = "blocat";
            UserView uv = new UserView(u.getName(), u.getAge(), status, u.getId());
            list.add(uv);
        }
        tabel.getItems().setAll(list);
    }

    @FXML
    private void backToMainMenu() throws IOException {
        App.changeRoot("meniu_principal");
    }

    @FXML
    private void updateButton(){
        UserView uv = tabel.getSelectionModel().getSelectedItem();

        if (uv != null && uv.getStatus().equals("neprieteni"))
            trimitereCerere.setDisable(false);
        else
            trimitereCerere.setDisable(true);
    }
@FXML
    private void sendRequest(){
        UserView uv = tabel.getSelectionModel().getSelectedItem();
        sf.sendRequest(su.getUserCurent().getId(), uv.getId());
        updateTable();
        updateButton();

    }

}
