/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Wejden;

import entities.Abonements;
import interfaces.Iproduit;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceAbonnement;
import services.ServiceProduit;
import util.maConnexion;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class AbonnementController implements Initializable {

    ServiceAbonnement sp = new ServiceAbonnement();
    Connection cnx = maConnexion.getInstance().getCnx();
    PreparedStatement ps;
    @FXML
    private TableView<Abonements> table_abn;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private TableColumn<Abonements, String> nompro;
    @FXML
    private TableColumn<Abonements, String> prenompro;
    @FXML
    private TableColumn<Abonements, Date> datedpro;
    @FXML
    private TableColumn<Abonements, Date> datefinpro;
    private TableColumn<Abonements, String> idpro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aficher();
    }

    @FXML
    private void addanb(ActionEvent event) {
        String nom = txtnom.getText();
        String prenom = txtprenom.getText();
        Date ddate = Date.valueOf(dated.getValue());
        Date fdate = Date.valueOf(datef.getValue());

        if (!nom.equals("") && !prenom.equals("") && !ddate.equals("") && !fdate.equals("")) {

            sp.ajouterabonnement(new Abonements(txtnom.getText(), txtprenom.getText(), Date.valueOf(dated.getValue()), Date.valueOf(datef.getValue())));
            txtnom.setText("");
            txtprenom.setText("");

            dated.setValue(ddate.toLocalDate());
            dated.setValue(ddate.toLocalDate());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " abonnement ajouter  avec succes", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            aficher();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur de saisie  =", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }

    }

    ObservableList<Abonements> listprod = FXCollections.observableArrayList();

    @FXML
    private void aficher() {
        Connection cnx = maConnexion.getInstance().getCnx();
        table_abn.getItems().clear();
        String req = "SELECT * FROM Abonements";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println();
                listprod.add(new Abonements(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5)));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        nompro.setCellValueFactory(new PropertyValueFactory<Abonements, String>("nom_sportif"));
        prenompro.setCellValueFactory(new PropertyValueFactory<Abonements, String>("prenom_sportif"));
        datedpro.setCellValueFactory(new PropertyValueFactory<Abonements, Date>("dated"));
        datefinpro.setCellValueFactory(new PropertyValueFactory<Abonements, Date>("datef"));
        System.out.println(listprod);
        table_abn.setItems(listprod);

    }
}
