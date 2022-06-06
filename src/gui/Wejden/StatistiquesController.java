/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Wejden;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.B;
import javafx.scene.input.MouseEvent;
import util.maConnexion;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class StatistiquesController implements Initializable {

    @FXML
    private LineChart<?, ?> linechart;
    @FXML
    private PieChart piechart;
    private Label nbremploye;
    @FXML
    private Label nbrabonnement;
    @FXML
    private Label nbremp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inilLinechart();
        initpiechart();
    }    
    @FXML
    private void inilLinechart(){
        try {
            //try {
            Connection cnx = maConnexion.getInstance().getCnx();
            XYChart.Series series = new XYChart.Series();
            
            String req = "SELECT COUNT(*) AS rowcount FROM  Abonements WHERE YEAR(dated) = 2021 AND MONTH(dated) = 1 ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.next();
            int nbr = rs.getInt(1);
            
            
            String req1 = "SELECT COUNT(*) AS rowcount FROM  Abonements WHERE YEAR(dated) = 2021 AND MONTH(dated) = 3 ";
            ResultSet rs1 = st.executeQuery(req1);
            rs1.next();
            int nbr1 = rs1.getInt(1);
            
            String req2 = "SELECT COUNT(*) AS rowcount FROM  Abonements WHERE YEAR(dated) = 2021 AND MONTH(dated) = 5 ";
            ResultSet rs2 = st.executeQuery(req2);
            rs2.next();
            int nbr2 = rs2.getInt(1);
            
            String req3 = "SELECT COUNT(*) AS rowcount FROM  Abonements WHERE YEAR(dated) = 2021 AND MONTH(dated) = 7 ";
            ResultSet rs3 = st.executeQuery(req3);
            rs3.next();
            int nbr3 = rs3.getInt(1);
            
            String req4 = "SELECT COUNT(*) AS rowcount FROM  Abonements WHERE YEAR(dated) = 2021 AND MONTH(dated) = 9 ";
            ResultSet rs4 = st.executeQuery(req4);
            rs4.next();
            int nbr4 = rs4.getInt(1);
            
            String req5 = "SELECT COUNT(*) AS rowcount FROM  Abonements WHERE YEAR(dated) = 2021 AND MONTH(dated) = 11 ";
            ResultSet rs5 = st.executeQuery(req5);
            rs5.next();
            int nbr5 = rs5.getInt(1);
            
            series.getData().add(new XYChart.Data( "Janvier", nbr ));
            series.getData().add(new XYChart.Data( "Mars", 10 ));
            series.getData().add(new XYChart.Data( "Mai", 20 ));
            series.getData().add(new XYChart.Data( "Juillet", 50 ));
            series.getData().add(new XYChart.Data( "Septembre", 20 ));
            series.getData().add(new XYChart.Data( "Novembre", 10 ));
            
            linechart.getData().addAll(series);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }

   
    }
 

    @FXML
    private void initpiechart() {
        try {
            
            Connection cnx = maConnexion.getInstance().getCnx();

            String req = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie like '%Vetements%' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.next();
            int nb = rs.getInt(1);
            
            String req1 = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie like '%chaussures%' ";
            
            ResultSet rs1 = st.executeQuery(req1);
            rs1.next();
            int nb1 = rs1.getInt(1);
            
            String req2 = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie like '%Accessoires%' ";
            
            ResultSet rs2 = st.executeQuery(req2);
            rs2.next();
            int nb2 = rs2.getInt(1);
            
            
            String req3 = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie like '%Materiel sportif%' ";
            
            ResultSet rs3 = st.executeQuery(req3);
            rs3.next();
            int nb3 = rs3.getInt(1);
            
            String req4 = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie like '%Produits nutritifs%' ";
            
            ResultSet rs4 = st.executeQuery(req2);
            rs4.next();
            int nb4 = rs4.getInt(1);

            
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data( "Vetements",nb ),
                    new PieChart.Data( "chaussures",nb1),
                    new PieChart.Data( "Accessoires",nb2),
                    new PieChart.Data( "Materiel sportif",nb3),
                    new PieChart.Data( "Produits nutritifs",nb4)
            );
            piechart.setData(pieChartData);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    
 
    }

    @FXML
    private int affichernombreemploye(MouseEvent event) throws SQLException {
     
            Connection cnx = maConnexion.getInstance().getCnx();
            String req = "SELECT COUNT(*) AS rowcount FROM  employe ";
            System.out.println("aaaaa");
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.next();
            int nb = rs.getInt(1);
            ;
            
            //System.out.println(nb);
        return nb;
   
    }

    @FXML
    private int affichernombreabonnement(MouseEvent event) throws SQLException {
         Connection cnx = maConnexion.getInstance().getCnx();
            String req = "SELECT COUNT(*) AS rowcount FROM  Abonements ";
            System.out.println("aaaaa");
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.next();
            int nb1 = rs.getInt(1);
        return nb1;
        
    }
    
}
