/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Event;

import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import entities.Commentaire;
import entities.Forum;
import entities.Utilisateur;
//import gui.Sportif.GestionSportifController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CommentaireCrud;
/**
 * FXML Controller class
 *
 * @author nahaw
 */
public class ForumController implements Initializable {
 public static Utilisateur connected ;
    @FXML
    private TableView<Commentaire> TableForum;
    @FXML
    private AnchorPane page;
    @FXML
    private Text titre;
    @FXML
    private TableColumn<Commentaire,String > col_categorie;
    @FXML
    private TableColumn<Commentaire, String> col_message;
    @FXML
    private Hyperlink eventhyp;

    /**
     * Initializes the controller class.
     */
    ObservableList <Commentaire> List= FXCollections.observableArrayList(); 
    @FXML
    private TableColumn<Commentaire, Integer> col_idCommentaire;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Commentaire c = new Commentaire(); 
        CommentaireCrud cc = new CommentaireCrud(); 
        System.out.println(cc.afficherCommentaireCrud()); 
        
        col_idCommentaire.setCellValueFactory(new PropertyValueFactory("idCommentaire")); 
        col_categorie.setCellValueFactory(new PropertyValueFactory("categorie")); 
        col_message.setCellValueFactory(new PropertyValueFactory("message")); 
        TableForum.setItems(cc.afficherCommentaireCrud()); 
      
    }    

    private void Gotoreview(ActionEvent event) throws IOException  {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("review.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();    
        window.setScene(tableViewScene);
        window.show();
    }

//    @FXML
//    private void Retour(ActionEvent event) throws IOException {
//        GestionSportifController.connected=connected;
//        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui/Sportif/GestionSportif.fxml"));
//        Scene tableViewScene = new Scene(tableViewParent);
//        
//        //This line gets the Stage information
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        window.setScene(tableViewScene);
//        window.show();
//    }

    private void review(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("review.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);       
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

//    @FXML
//    private void gestionsportif(ActionEvent event) throws IOException {
//        GestionSportifController.connected=connected;
//        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui/Sportif/GestionSportif.fxml"));
//        Scene tableViewScene = new Scene(tableViewParent);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(tableViewScene);
//        window.show();
//    }
//    
}
