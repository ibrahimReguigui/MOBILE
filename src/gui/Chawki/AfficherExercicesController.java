/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Chawki;

import entities.Exercice;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Exercice2Crud;
import services.ExerciceCRUD;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherExercicesController implements Initializable {
 Exercice2Crud cc = new Exercice2Crud();
    @FXML
    private TableView<Exercice> table;
    @FXML
    private TableColumn<Exercice, String> nom;
    @FXML
    private TableColumn<Exercice, String> description;
    @FXML
    private TableColumn<Exercice, String> categorie;
    @FXML
    private TableColumn<Exercice, Integer> nbrRep;
    @FXML
    private TableColumn<Exercice, Integer> nbrSerie;
    ExerciceCRUD cs = new ExerciceCRUD();
    @FXML
    private Button ajouter;
    @FXML
    private Button retour;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherTable();
        tableListener();
    }

    private void afficherTable() {
        ObservableList<Exercice> list = FXCollections.observableArrayList(cs.afficherExercice());
        System.out.println(list);
        nom.setCellValueFactory(new PropertyValueFactory<Exercice, String>("nom_exercice"));
        categorie.setCellValueFactory(new PropertyValueFactory<Exercice, String>("categorie_exercice"));
        description.setCellValueFactory(new PropertyValueFactory<Exercice, String>("description_exercice"));
        nbrRep.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("nbr_repetition"));
        nbrSerie.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("nbr_serie"));
        table.setItems(list);
    }

    @FXML
    private void AjouterE(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Chawki/ajouterexercice.fxml"));
        try {
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void RetourG(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Coach/GestionCoach.fxml"));
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
                ajouter.setDisable(false);
            } else {
                ajouter.setDisable(true);
            }

        });
    }
    @FXML
    private void SupprimerE(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Suppression!!!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
               
            Exercice e = table.getSelectionModel().getSelectedItem();
//            int id = cc.findIdUserByName(cour.getNom_coach());
            cc.supprimerE(e);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherExercices.fxml"));
            try {
                Parent root = loader.load();
                table.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
