/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Sportif;


import entities.CourCoach;
import entities.CourSalle;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.MessagingException;
import services.CourCoachCRUD;
import services.CourSalleCRUD;
import utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class GestionSportifController implements Initializable {

    @FXML
    private Button cours;
    @FXML
    private Button coachs;
    @FXML
    private TableView<CourSalle> tablecoursalle;
    @FXML
    private TableView<CourCoach> tablecourcoach;
    @FXML
    private TableColumn<CourSalle, String> nom;
    @FXML
    private TableColumn<CourSalle, Date> date;
    @FXML
    private TableColumn<CourSalle, Time> tCour;
    @FXML
    private TableColumn<CourSalle, Integer> nbrTotal;
    @FXML
    private TableColumn<CourSalle, Integer> nbrActuel;
    @FXML
    private TableColumn<CourSalle, String> info;
    @FXML
    private Button supprimerCour;
    @FXML
    private TableColumn<CourCoach, String> nomprenom;
    @FXML
    private TableColumn<CourCoach, Date> date1;
    @FXML
    private TableColumn<CourCoach, Time> heure;

    /**
     * Initializes the controller class.
     */
    CourSalleCRUD cs = new CourSalleCRUD();
    CourCoachCRUD cc = new CourCoachCRUD();
    User connected = new User(8, "SportifTest", "reguigui","brahim.br244@gmail.com");
    @FXML
    private TableColumn<CourSalle, String> salle1;
    @FXML
    private TableColumn<CourCoach, String> etat;
    @FXML
    private Button supprimerreservation;
    @FXML
    private Button com;
    @FXML
    private Button besoin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableCoachListener();
        tableCourListener();
        afficherTableReservationCoach();
        afficherTableCourSalle();
    }

   
  

    public void afficherTableCourSalle() {
        ObservableList<CourSalle> list = FXCollections.observableArrayList(cs.affichageReservationCourSalleEffectue(connected));
        nom.setCellValueFactory(new PropertyValueFactory<CourSalle, String>("nom_cour"));
        salle1.setCellValueFactory(new PropertyValueFactory<CourSalle, String>("nom_salle"));
        date.setCellValueFactory(new PropertyValueFactory<CourSalle, Date>("date"));
        tCour.setCellValueFactory(new PropertyValueFactory<CourSalle, Time>("t_cour"));
        nbrTotal.setCellValueFactory(new PropertyValueFactory<CourSalle, Integer>("nbr_total"));
        nbrActuel.setCellValueFactory(new PropertyValueFactory<CourSalle, Integer>("nbr_actuel"));
        info.setCellValueFactory(new PropertyValueFactory<CourSalle, String>("information"));
        tablecoursalle.setItems(list);
    }

    public void tableCourListener() {
        tablecoursalle.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                supprimerCour.setDisable(false);
            } else {
                supprimerCour.setDisable(true);
            }
        });
    }

    public void tableCoachListener() {
        tablecourcoach.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                supprimerreservation.setDisable(false);
            } else {
                supprimerreservation.setDisable(true);
            }
        });
    }

    @FXML
    private void supprimerReservationCour(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre choix : La reservation sera annulée!!!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            CourSalle cour = tablecoursalle.getSelectionModel().getSelectedItem();
            cs.supprimerReservationCourSalle(connected, cour);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionSportif.fxml"));
            try {
                Parent root = loader.load();
                cours.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
 @FXML
    private void listecours(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserverCourSalle.fxml"));
        try {
            Parent root = loader.load();
            cours.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void listeCoachsDisponibilites(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeCoachsDisponibilites.fxml"));
        try {
            Parent root = loader.load();
            cours.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void afficherTableReservationCoach() {
        ObservableList<CourCoach> list = FXCollections.observableArrayList(cc.afficherReservationEffectue(connected));
        System.out.println(list);
        nomprenom.setCellValueFactory(new PropertyValueFactory<CourCoach, String>("nom_coach"));
        etat.setCellValueFactory(new PropertyValueFactory<CourCoach, String>("etat"));
        date1.setCellValueFactory(new PropertyValueFactory<CourCoach, Date>("date"));
        heure.setCellValueFactory(new PropertyValueFactory<CourCoach, Time>("time"));
        tablecourcoach.setItems(list);
    }

    @FXML
    private void annulerReservation(ActionEvent event) throws MessagingException {
        CourCoach cour = tablecourcoach.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez confirmer votre choix : La reservation sera annulée!!!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println(cour.getEtat());
            System.out.println(cour.getEtat()=="Accepte");
            if (cour.getEtat() == "Accepte") {
                JavaMail.sendMail(cc.getEmailById(cour.getId_coach_id()), "reservationAnnuléeParSportif", cour);
            }
            cc.annulerReservation(connected, cour.getDate(), cour.getTime(), cour.getId_coach_id());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionSportif.fxml"));
            try {
                Parent root = loader.load();
                cours.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void review(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Event/review.fxml"));
        try {
            Parent root = loader.load();
            cours.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Besoin(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Aziz/result.fxml"));
        try {
            Parent root = loader.load();
            cours.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
