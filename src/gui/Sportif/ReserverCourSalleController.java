/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Sportif;

import entities.CourSalle;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.CourSalleCRUD;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ReserverCourSalleController implements Initializable {

    @FXML
    private TableView<CourSalle> table;
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
    private TableColumn<CourSalle, String> salle;
    @FXML
    private TextField recherche;
    @FXML
    private Button reserver;
    @FXML
    private Button retour;

    CourSalleCRUD cs = new CourSalleCRUD();
       User connected = new User(8, "SportifTest", "reguigui","brahim.br244@gmail.com");
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableListener();
        afficherTable();
    }

    public void afficherTable() {
        CourSalleCRUD cs = new CourSalleCRUD();
        ObservableList<CourSalle> list = FXCollections.observableArrayList(cs.afficherReservationPossible(connected));
        salle.setCellValueFactory(new PropertyValueFactory<CourSalle, String>("nom_salle"));
        nom.setCellValueFactory(new PropertyValueFactory<CourSalle, String>("nom_cour"));
        date.setCellValueFactory(new PropertyValueFactory<CourSalle, Date>("date"));
        tCour.setCellValueFactory(new PropertyValueFactory<CourSalle, Time>("t_cour"));
        nbrTotal.setCellValueFactory(new PropertyValueFactory<CourSalle, Integer>("nbr_total"));
        nbrActuel.setCellValueFactory(new PropertyValueFactory<CourSalle, Integer>("nbr_actuel"));
        info.setCellValueFactory(new PropertyValueFactory<CourSalle, String>("information"));
        table.setItems(list);
        FilteredList<CourSalle> filteredData = new FilteredList<>(list, b -> true);
        System.out.println(filteredData);
        recherche.setOnKeyPressed(e -> {
            recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super CourSalle>) cour -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (cour.getNom_salle().toLowerCase().contains(lowerCaseFilter)||cour.getNom_cour().toLowerCase().contains(lowerCaseFilter)||cour.getInformation().toLowerCase().contains(lowerCaseFilter)||
                            String.valueOf(cour.getNbr_total()).toLowerCase().contains(lowerCaseFilter)||String.valueOf(cour.getNbr_actuel()).toLowerCase().contains(lowerCaseFilter)||
                            String.valueOf(cour.getDate()).toLowerCase().contains(lowerCaseFilter)||String.valueOf(cour.getT_cour()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<CourSalle> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
    }

    @FXML
    private void reserver(ActionEvent event) {
        CourSalle cour = table.getSelectionModel().getSelectedItem();
        if (cs.testCourComplet(cour)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Cour Complet !!!!");
            alert.showAndWait();
        } else if (cs.testDejaParticipant(connected, cour)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous Participez Deja A Ce Cour !!!!");
            alert.showAndWait();
        } else {
            Notifications notificationBuilder = Notifications.create().title("Gestion Sportif").text("La reseervation a été effectuée !! ").hideAfter(Duration.seconds(1)).position(Pos.CENTER);
                notificationBuilder.show();
            cs.reserverCourSalle(connected, cour);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionSportif.fxml"));
            try {
                Parent root = loader.load();
                retour.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionSportif.fxml"));
        try {
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void tableListener() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                reserver.setDisable(false);
            } else {
                reserver.setDisable(true);
            }
        });
    }

}
