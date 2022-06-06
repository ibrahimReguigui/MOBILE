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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class GestionCoachController implements Initializable {

    @FXML
    private Button gestiondemande;
    @FXML
    private Button gestiondisponibilite;
    @FXML
    private TableView<CourCoach> table;
    @FXML
    private TableColumn<CourCoach, String> nom;
    @FXML
    private TableColumn<CourCoach, Date> date;
    @FXML
    private TableColumn<CourCoach, Time> heure;
    @FXML
    private Button anuller;

    CourCoachCRUD cc = new CourCoachCRUD();
    User connected = new User(2, "CoachTest", "test","ibrahim.reguigui@esprit.tn");
    @FXML
    private Button listeexercices;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherTable();
        tableListener();
    }

    public void afficherTable() {
        ObservableList<CourCoach> list = FXCollections.observableArrayList(cc.getDemandes2(connected, "Acceptée"));
        System.out.println(list);
        nom.setCellValueFactory(new PropertyValueFactory<CourCoach, String>("nom_participant"));
        date.setCellValueFactory(new PropertyValueFactory<CourCoach, Date>("date"));
        heure.setCellValueFactory(new PropertyValueFactory<CourCoach, Time>("time"));
        table.setItems(list);
    }

    @FXML
    private void gestionDisponibilite(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionDisponibilite.fxml"));
        try {
            Parent root = loader.load();
            gestiondemande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gestionDemandes(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionDemandes.fxml"));
        try {
            Parent root = loader.load();
            gestiondemande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void tableListener() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                anuller.setDisable(false);
            } else {
                anuller.setDisable(true);
            }

        });
    }

    @FXML
    private void annuler(ActionEvent event) throws MessagingException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre choix : La reservation sera supprimée!!!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            
            CourCoach cour = table.getSelectionModel().getSelectedItem();
//            int id = cc.findIdUserByName(cour.getNom_coach());
            cc.annulerReservationCoach(connected.getId(), cour.getDate(), cour.getTime());
            JavaMail.sendMail(cc.getEmailById(cour.getId_participant_id()), "annulationReservationParCoach", cour);
            Notifications notificationBuilder = Notifications.create().title("Gestion Coach").text("La reservation a été annulée !! Un mail est entrain d'etre envoyée").hideAfter(Duration.seconds(3)).position(Pos.CENTER);
            notificationBuilder.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionCoach.fxml"));
            try {
                Parent root = loader.load();
                table.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void ajouterExercice(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Chawki/ajouterexercice.fxml"));
        try {
            Parent root = loader.load();
            gestiondemande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void Programmes(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Chawki/GestionDemandes.fxml"));
        try {
            Parent root = loader.load();
            gestiondemande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void listeExercices(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Chawki/afficherExercices.fxml"));
        try {
            Parent root = loader.load();
            gestiondemande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
