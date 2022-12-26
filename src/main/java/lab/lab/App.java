package lab.lab;
import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lab.lab.controller.AbstractController;
import lab.lab.domain.Friendship;
import lab.lab.domain.StatusFriendship;
import lab.lab.domain.User;
import lab.lab.repo.RepoDBAbstract;
import lab.lab.repo.RepoFriendshipDB;
import lab.lab.repo.RepoGeneric;
import lab.lab.repo.RepoUserDB;
import lab.lab.service.ServiceFriendship;
import lab.lab.service.ServiceUser;

import java.io.IOException;
import java.time.LocalDate;

//application e o clasa in javafx
public class App extends Application {

//metoda de schimbat scena e statica deci si service-urile trbuie sa fie statice
//intr-o metoda statica poti accesa doar metode si atribute statice
//statice = ale clasei
//intr-o metoda non-statica se pot accesa atribute si metode statice si non-statice
//chetsiile non-statice sunt ale obictului
    private static ServiceFriendship servfr = null;
    private static ServiceUser servu = null;
    private static Scene scene = null;

    @Override
    public void start(Stage stage) {

        String url = "jdbc:postgresql://127.0.0.1:5431/socialmedia";
        String username = "cosminabahna";
        String password = null;
        servfr= new ServiceFriendship(new RepoFriendshipDB(url, username, password) ,servu);
        servu = new ServiceUser(new RepoUserDB(url,username, password), servfr);

        try {
            changeRoot("autentificare");
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("socialmedia");

        //controller = face legatura dintre program si interfata
        //aici am obtinut controller-ul creat automat de interfata ca sa injectam in el service-uri
        //a injecta = introducem in controller o dependinta de service-uri


        stage.setScene(scene);
        stage.show();
    }


    //le am facut statice ca sa poate fi accesate de oriunde
    public static void changeRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml+".fxml"));
//extrage radacina din ierarhia de obiecte din fxml
        Parent root = fxmlLoader.load();

        AbstractController ab  = fxmlLoader.getController();
        ab.setSf(servfr);
        ab.setSu(servu);

        if (scene == null)
            scene = new Scene(root);
        else
            scene.setRoot(root);
    }

    public static void main(String[] args) throws SQLException {
        launch(); //imi cheama functia start
    }
}