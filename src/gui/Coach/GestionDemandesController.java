/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Coach;

import entities.CourCoach;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import services.CourCoachCRUD;
import utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class GestionDemandesController implements Initializable {

    @FXML
    private TableView<CourCoach> table;
    @FXML
    private TableColumn<CourCoach, String> nom;
    @FXML
    private TableColumn<CourCoach, Date> date;
    @FXML
    private TableColumn<CourCoach, Time> heure;
    @FXML
    private Button refuser;
    @FXML
    private Button retour;
    @FXML
    private Button accepter;
    CourCoachCRUD cc = new CourCoachCRUD();
    User connected = new User(2, "CoachTest", "test", "ibrahim.reguigui@esprit.tn");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherTable();
        tableListener();
    }

    public void afficherTable() {
        ObservableList<CourCoach> list = FXCollections.observableArrayList(cc.getDemandes2(connected, "En Attente"));
        System.out.println(list);
        nom.setCellValueFactory(new PropertyValueFactory<CourCoach, String>("nom_participant"));
        date.setCellValueFactory(new PropertyValueFactory<CourCoach, Date>("date"));
        heure.setCellValueFactory(new PropertyValueFactory<CourCoach, Time>("time"));
        table.setItems(list);
        System.out.println(list);
    }

    @FXML
    private void Refuser(ActionEvent event) throws MessagingException {
        CourCoach cour = table.getSelectionModel().getSelectedItem();

        cc.annulerReservationCoach(cour.getId_coach_id(), cour.getDate(), cour.getTime());
        JavaMail.sendMail(cc.getEmailById(cour.getId_participant_id()), "demandeReservationRefusée", cour);
        Notifications notificationBuilder = Notifications.create().title("Gestion Coach").text("La reservation a été refusée ").hideAfter(Duration.seconds(1)).position(Pos.CENTER);
        notificationBuilder.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionDemandes.fxml"));
        try {
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Accepter(ActionEvent event) throws MessagingException {
        CourCoach cour = table.getSelectionModel().getSelectedItem();
//        int id = cc.findIdUserByName(cour.getId_coach_id());
        System.out.println("11111111111111111111111111" + cour);
        cc.accepterReservation(connected, cour.getDate(), cour.getTime(), cour.getId_participant_id());
        JavaMail.sendMail(cc.getEmailById(cour.getId_participant_id()), "demandeReservationAcceptée", cour);
        Notifications notificationBuilder = Notifications.create().title("Gestion Coach").text("La reservation a été acceptée ").hideAfter(Duration.seconds(1)).position(Pos.CENTER);
        notificationBuilder.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionDemandes.fxml"));
        try {
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void tableListener() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                refuser.setDisable(false);
                accepter.setDisable(false);
            } else {
                refuser.setDisable(true);
                accepter.setDisable(true);
            }

        });
    }

    @FXML
    private void Retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionCoach.fxml"));
        try {
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
