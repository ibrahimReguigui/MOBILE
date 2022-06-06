/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Wejden;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class FXMLhomegerantController implements Initializable {
    private Parent fxml;
    @FXML
    private AnchorPane root1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gestioninterne(ActionEvent event) {
         try {
            fxml= FXMLLoader.load(getClass().getResource("FXML1.fxml"));
            root1.getChildren ().removeAll ();
            root1.getChildren ().setAll (fxml);
            
            } catch (IOException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }
    
}
