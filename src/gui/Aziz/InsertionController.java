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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.caracCRUD;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class InsertionController implements Initializable {

    @FXML
    private TextField tfTaille;
    @FXML
    private TextField tfPoid;
    @FXML
    private TextField tfAge;
    @FXML
    private Button btnValider;
    @FXML
    private ChoiceBox<String> choice= new ChoiceBox<>();
    private String[] sexe = {"Femme","Homme"};
    @FXML
    private ChoiceBox<String> Obj= new ChoiceBox<>();
    private String[] Objectif = {"Perte de poids", "Gain de poids"};
    
    User connected = new User(8, "SportifTest", "reguigui","brahim.br244@gmail.com");
    caracCRUD cc=new caracCRUD();
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choice.getItems().addAll(sexe);
        Obj.getItems().addAll(Objectif);
        
    }    

   

    @FXML
    private void RetourL(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Aziz/result.fxml"));
        try {
            Parent root = loader.load();
            tfTaille.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterCarac(ActionEvent event) {
         int taille = Integer.parseInt(tfTaille.getText());
        int poid = Integer.parseInt(tfPoid.getText());
        int age = Integer.parseInt(tfAge.getText());
        String sexe =(choice.getValue());
        String Objectif =(Obj.getValue());
        caracteristiquesportif c = new caracteristiquesportif(connected.getId(),taille, poid, age, sexe, Objectif);
        cc.ajouterCarac(c);  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Aziz/result.fxml"));
            try {
                Parent root = loader.load();
                tfTaille.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
  
       
    
}
