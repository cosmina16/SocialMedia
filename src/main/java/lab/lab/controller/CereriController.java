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

public class CereriController extends AbstractController{
    @FXML
    private TextField cautare;
    @FXML
    private TableView<User> tabelTrimise;
    @FXML
    private TableView<User> tabelPrimite;

    @FXML
    private Button anulare;
    @FXML
    private Button accept;
    @FXML
    private Button blocare;

    @FXML
    private void backToMainMenu() throws IOException {
        App.changeRoot("meniu_principal");
    }

    @FXML
    private void initialize(){
        //User - ce contine tabelul la care adaug coloana
        //String - ce contine coloana asta a tabelului
        TableColumn<User,String> nume = new TableColumn<User,String>("Nume"); //creaza o coloana ... Nume =e cum se numeste in interfata
        //cand adaug un User nou in tabel coloana va fi automat populata cu atributul name din user
        nume.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<User,Integer> age = new TableColumn<User,Integer>("Varsta");
        age.setCellValueFactory(new PropertyValueFactory("age"));

        tabelPrimite.getColumns().addAll(nume, age);

        TableColumn<User,String> nume1 = new TableColumn<User,String>("Nume"); //creaza o coloana ... Nume =e cum se numeste in interfata
        nume1.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<User,Integer> age1 = new TableColumn<User,Integer>("Varsta");
        age1.setCellValueFactory(new PropertyValueFactory("age"));
        tabelTrimise.getColumns().addAll(nume1, age1);
    }

    @Override
    public void myinitialize() {
        super.myinitialize();
        updateTables();
    }

    @FXML
    private void updateTables(){
        ArrayList<User> primite = new ArrayList<>();
        ArrayList<User> trimise = new ArrayList<>();
        for(User u: su.getAllUsers()){
            if (!u.getName().toLowerCase().contains(cautare.getText().toLowerCase()))
                continue;
            Friendship fr =sf.getFriendship(u.getId(),su.getUserCurent().getId());
            if(fr ==null)
                continue;
            if (!fr.getStatus().equals(StatusFriendship.Pending))
                continue;
            //user-ul logat a trimis cererea
            if(su.getUserCurent().getId() == fr.getIdUser1())
                trimise.add(u);
            else
                primite.add(u);
            System.out.println(u.getName());
        }
        tabelPrimite.getItems().setAll(primite);
        tabelTrimise.getItems().setAll(trimise);

        updateButtons();
    }

    @FXML
    private void updateButtons(){
        updateBlock();
        updateAccept();
        updateAnulare();
    }

    private void updateAnulare(){
        User u = tabelTrimise.getSelectionModel().getSelectedItem();
        if(u == null)
            anulare.setDisable(true);
        else
            anulare.setDisable(false);
    }

    private void updateAccept(){
        User u = tabelPrimite.getSelectionModel().getSelectedItem();
        if(u == null)
            accept.setDisable(true);
        else
            accept.setDisable(false);
    }

    private void updateBlock(){
        User u = tabelPrimite.getSelectionModel().getSelectedItem();
        if(u == null)
            blocare.setDisable(true);
        else
            blocare.setDisable(false);
    }
    @FXML
    private void acceptRequest() {
        int u = tabelPrimite.getSelectionModel().getSelectedItem().getId();
        sf.acceptRequest(su.getUserCurent().getId(), u);
        updateTables();
    }

    @FXML
    private void blockRequest() {
        int u = tabelPrimite.getSelectionModel().getSelectedItem().getId();
        sf.declineRequst(su.getUserCurent().getId(), u);
        updateTables();
    }

    @FXML
    private void anulareRequest() {
        int u = tabelTrimise.getSelectionModel().getSelectedItem().getId();
        sf.deleteFriendship(su.getUserCurent().getId(), u);
        updateTables();
    }


}

