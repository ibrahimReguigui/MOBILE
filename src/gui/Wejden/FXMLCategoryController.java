/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Wejden;

import entities.Category;
import interfaces.ICategory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import services.ServiceCategory;
import util.maConnexion;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import services.ServiceCategory;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class FXMLCategoryController implements Initializable {
   // Iproduit sp= new ServiceProduit();
    ICategory sp=new ServiceCategory();
  Connection cnx = maConnexion.getInstance().getCnx();
            PreparedStatement ps ;
              
       @FXML
    private TextField txt_name;
        @FXML
    private TableView<Category> table_categories;
    @FXML
    private TableColumn<Category, String> cate_pro;
    
    @FXML
    private AnchorPane Paneproduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


        ObservableList<Category> listcat = FXCollections.observableArrayList();


    


    @FXML
    private void addajout1(ActionEvent event) {
                 String nom=txt_name.getText();
        
           
         if (!nom.equals("")) {
             
            sp.ajouterCategory(new Category(txt_name.getText()));
                     txt_name.setText("");
             
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION," abonnement ajouter  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
         //  aficher();
        } else {
           Alert alert = new Alert(Alert.AlertType.ERROR ,"Erreur de saisie  =", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
        }
    }

    @FXML
    private void afichier1(ActionEvent event) {
           Connection cnx = maConnexion.getInstance().getCnx();
           table_categories.getItems().clear();
           String req1 = "SELECT name FROM Category";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req1);
            while (rs.next()) {                
                listcat.add(new Category( rs.getString(1)));
          
            }
        
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
       cate_pro.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
       
              
        table_categories.setItems(listcat); 
    }

    @FXML
    private void Selected(MouseEvent event) {
    }
    
}

