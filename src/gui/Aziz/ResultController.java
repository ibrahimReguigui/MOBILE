/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Aziz;


import entities.User;
import entities.caracteristiquesportif;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.caracCRUD;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ResultController implements Initializable {

    @FXML
    private TableView<caracteristiquesportif> table;
    @FXML
    private Button rtr;
    @FXML
    private Button plat;
    @FXML
    private TableColumn<caracteristiquesportif, Float> taille_sportif;   
    @FXML
    private TableColumn<caracteristiquesportif, Float> poid_sportif;
    @FXML
    private TableColumn<caracteristiquesportif, Integer> age_sportif;
    @FXML
    private TableColumn<caracteristiquesportif, String> objectif_sportif;
    @FXML
    private TableColumn<caracteristiquesportif, Integer> besoin_calories;
    @FXML
    private TableColumn<caracteristiquesportif, Integer> besoin_protein;
    @FXML
    private TableColumn<caracteristiquesportif, Integer> besoin_carbs;
    @FXML
    private TableColumn<caracteristiquesportif, Integer> Bmi_sportif;
     caracCRUD cc=new caracCRUD();

   User connected = new User(8, "SportifTest", "reguigui","brahim.br244@gmail.com");
    @FXML
    private Button carac;
    @FXML
    private Button supp;

    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        afficherTable ();
        tableListener();
      
     
       
        
        
       
     

    }    
     public void tableListener() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                rtr.setDisable(false);
            } else {
                rtr.setDisable(true);
            }

        });
    }
       public void afficherTable (){
    
        ObservableList<caracteristiquesportif> list = FXCollections.observableArrayList(cc.getCarac());
        System.out.println(list);
        taille_sportif.setCellValueFactory(new PropertyValueFactory<>("taille_sportif"));
        poid_sportif.setCellValueFactory(new PropertyValueFactory<>("poid_sportif"));
        age_sportif.setCellValueFactory(new PropertyValueFactory<>("age_sportif"));
        objectif_sportif.setCellValueFactory(new PropertyValueFactory<>("objectif_nutrition"));
        besoin_calories.setCellValueFactory(new PropertyValueFactory<>("besoin_calories"));
        besoin_protein.setCellValueFactory(new PropertyValueFactory<>("besoin_proteine"));
        besoin_carbs.setCellValueFactory(new PropertyValueFactory<>("besoin_carb"));
        Bmi_sportif.setCellValueFactory(new PropertyValueFactory<>("Bmi_sportif"));
         table.setItems(list);
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
    
        
        
          Parent tableViewParent = FXMLLoader.load(getClass().getResource("Insertion.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
        
     
    } 
        
       
       
    

    @FXML
    private void plats(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/market.fxml"));
        try {
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void ajouterCaract(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Aziz/Insertion.fxml"));
        try {
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerE(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Suppression");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {             
            caracteristiquesportif c = table.getSelectionModel().getSelectedItem();
            cc.supprimerE(c);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result.fxml"));
            try {
                Parent root = loader.load();
                table.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}



