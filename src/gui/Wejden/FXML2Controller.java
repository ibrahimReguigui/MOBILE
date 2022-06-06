/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Wejden;

import entities.Category;
import entities.Produit;
import interfaces.Iproduit;
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
import services.ServiceProduit;
import util.maConnexion;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FXML2Controller implements Initializable {
 Iproduit sp= new ServiceProduit();
  Connection cnx = maConnexion.getInstance().getCnx();
            PreparedStatement ps ;
              int txt_cat;
    @FXML
    private TextField txt_searchId;
    @FXML
    private TextField txt_lib;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_desc;
    @FXML
    private TextField txt_mar;
    @FXML
    private TextField txt_quan;
    @FXML
    private TextField txt_not;
    @FXML
    private Label lab_url;
    @FXML
    private ComboBox catid;
    @FXML
    private TableView<Produit> table_produit;
    @FXML
    private TableColumn<Produit, Integer> id_pr;
    @FXML
    private TableColumn<Produit, String> cate_pro;
    @FXML
    private TableColumn<Produit, String> lib_pro;
    @FXML
    private TableColumn<Produit, String> mar_pro;
    @FXML
    private TableColumn<Produit, Float> prix_pro;
    @FXML
    private TableColumn<Produit, String> desc_pro;
    @FXML
    private TableColumn<Produit, String> disp_pro;
    @FXML
    private TableColumn<Produit, Float> note_pro;
    @FXML
    private TableColumn<Produit, Integer> qua_prod;
    @FXML
    private TableColumn<Produit, String> ima_prod;
    
    @FXML
    private ImageView imageuit;
//    @FXML
//    private TextField txt_cat;
    @FXML
    private TextField txt_desp;
    @FXML
    private TextField txt_img;
     List<String> listcat = new ArrayList<String>();
    @FXML
    private AnchorPane Paneproduit;
    @FXML
    private ImageView icon_imprt;
    
    //  final ObservableList categories= new FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
         // TODO
         afichier();
         
         remplircateg();
         
         // ObservableList<String> list = FXCollections.observableArrayList("Jeux","Hardware");
         // cd_catt.setItems(list);
     } catch (SQLException ex) {
         Logger.getLogger(FXML2Controller.class.getName()).log(Level.SEVERE, null, ex);
     }
    }  
    
    

   
ObservableList<Produit> listprod = FXCollections.observableArrayList();
    @FXML
    private void afichier() {
 Connection cnx = maConnexion.getInstance().getCnx();
           table_produit.getItems().clear();
     String req = "SELECT * FROM produit";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {  

                listprod.add(new Produit(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getInt(10)));
          
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        id_pr.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id")); //1
        cate_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie")); //2 
        lib_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("libelle")); //3
        mar_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("marque")); //4
        prix_pro.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix")); //5
        desc_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("description")); //6
        ima_prod.setCellValueFactory(new PropertyValueFactory<Produit, String>("image")); //7
        disp_pro.setCellValueFactory(new PropertyValueFactory<Produit, String>("disponibilite")); //8
                 
        note_pro.setCellValueFactory(new PropertyValueFactory<Produit, Float>("note")); //9
        qua_prod.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite")); //10
        table_produit.setItems(listprod);
    }

    
    private void remplircateg() throws SQLException {
       
        ObservableList<Category> categories = FXCollections.observableArrayList();
     
        ObservableList<String> list = (ObservableList<String>) FXCollections.observableArrayList("");
        list.remove("");
       Connection cnx = maConnexion.getInstance().getCnx();
       
       ComboBox comboBox = new ComboBox(categories);
       comboBox.setMaxHeight(30);
       
        String req1 = "SELECT `name` FROM `category` ";
     
                 try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req1);
            while (rs.next()) {
                
                list.add(rs.getString("Name"));
                 listcat= list;
                catid.setItems(list);
               // categories.add((rs.getString("nom"));
                //catid.setItems(categories);
                //categories.add(rs.getString("Name"));
 }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       

       
        
   // String s =  cd_catt.getSelectionModel().getSelectedItem().toString();
     
    }

    private void remplirdispon() {
        
        Connection cnx = maConnexion.getInstance().getCnx();
        String req = "SELECT `disponibilite` FROM `produit` ";
        List<String> prom = new ArrayList<String>();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                prom.add(rs.getString("disponibilite"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //cd_disp.setItems(FXCollections.observableArrayList(prom));
        //cd_disp.setItems(prom);
    }

    @FXML
    private void addajout(ActionEvent event) {
       List<String> pro = new ArrayList<String>();
          List<String> pro1 = new ArrayList<String>(); 
     try {
         String     categoryid=(String) catid.getValue();
         // String     catt=txt_cat.getText();
         String    libb=      txt_lib.getText();
         String    marc=     txt_mar.getText();
         String   pri=   txt_prix.getText();
         String   des= txt_desc.getText();
         String   desp=  txt_desp.getText();
         String   nt=  txt_not.getText();
         String quan = txt_quan.getText();
         
          
         
    int k=listcat.indexOf(categoryid);
   System.out.println("poscataaaaaaaaaaaaaa"+k);
     
      //   String req4 = "SELECT `name` FROM `category`";
        
         
         //    list1.add(rs1.getString("name"));
        // System.out.println(listcat.get(1));
                 
                 
         
    String req1="select `id` from `category`";
          
         Statement st = cnx.createStatement();
         ResultSet rs = st.executeQuery(req1);
        while (rs.next()) {                
                pro.add(rs.getString("id"));
              //System.out.println( pro.add(rs.getString("id")));
            }
       
//          System.out.println("aaaaaaaaaaa");
          //System.out.println(pro.get(1));
          
          String a=pro.get(0);
         int i=Integer.parseInt(a);
         i=k+1;
         if (!libb.equals("")&&!marc.equals("")&&!pri.equals("")&&!des.equals("")&&!desp.equals("")&&! nt.equals("")&&! quan.equals("")) {
             
             sp.ajouterproduit(new Produit(i,(String)catid.getValue(),txt_lib.getText(),txt_mar.getText(),Float.parseFloat(txt_prix.getText()), txt_desc.getText(),txt_img.getText(),txt_desp.getText(),Float.parseFloat(txt_not.getText()),Integer.parseInt(txt_quan.getText())));

             //txt_cat.setText("");
             txt_lib.setText("");
             txt_mar.setText("");
             txt_prix.setText("");
             txt_desc.setText("");
             txt_img.setText("");
             txt_desp.setText("");
             txt_not.setText("");
             txt_quan.setText("");
             Alert alert = new Alert(AlertType.CONFIRMATION," produit ajouter  avec succes", javafx.scene.control.ButtonType.OK);
             alert.showAndWait();
             afichier();
         } else {
             Alert alert = new Alert(AlertType.ERROR ,"Erreur de saisie  ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
             alert.showAndWait();
         }
     } catch (SQLException ex) {
         Logger.getLogger(FXML2Controller.class.getName()).log(Level.SEVERE, null, ex);
     }

    }
        
        
        
        
        
    

    @FXML
    private void Selected(MouseEvent event) {
    }

    @FXML
    private void searchproduit() {
    
       Connection cnx = maConnexion.getInstance().getCnx();
        String req = "SELECT * FROM produit where  id ='"+txt_searchId.getText()+"'";
       int m=0;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {                
          // txt_cat.setText(rs.getString("categorie"));
          
             txt_lib.setText(rs.getString("libelle"));
              txt_mar.setText(rs.getString("marque"));
               txt_prix.setText(rs.getString("prix"));
               txt_desc.setText(rs.getString("description"));
                 txt_desp.setText(rs.getString("disponibilite"));
                 txt_not.setText(rs.getString("note"));
                  txt_quan.setText(rs.getString("quantite"));
                  m=1;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       if (m == 0) {
            Alert alert = new Alert(AlertType.ERROR ,"Aucun produit avec id ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
           
        
    
    
}}

    @FXML
    private void editproduit(MouseEvent event) {
        
        System.out.println("on est la");
          String     catt=(String) catid.getValue();
         String    libb=      txt_lib.getText();
        String    marc=     txt_mar.getText();
          String   pri=   txt_prix.getText();
        String   des= txt_desc.getText();
        String   desp=  txt_desp.getText();
         String   nt=  txt_not.getText();
           String quan = txt_quan.getText();       
        // if (!catt.equals("")&&!libb.equals("")&&!marc.equals("")&&!pri.equals("")&&!des.equals("")&&!desp.equals("")&&! nt.equals("")&&! quan.equals("")) {
          if (!libb.equals("")&&!marc.equals("")&&!pri.equals("")&&!des.equals("")&&!desp.equals("")&&! nt.equals("")&&! quan.equals("")) {
                System.out.println("on est la11111111111");
             sp.modifierproduit(new Produit(Integer.parseInt(txt_searchId.getText()),catt, txt_lib.getText(),txt_mar.getText(),Float.parseFloat(txt_prix.getText()), txt_desc.getText(),txt_img.getText(),txt_desp.getText(),Float.parseFloat(txt_not.getText()),Integer.parseInt(txt_quan.getText() )));
                     System.out.println("on est la2222222222222");
                   //   txt_cat.setText("");
                      txt_lib.setText("");
                      txt_mar.setText("");
                      txt_prix.setText("");
                      txt_desc.setText("");
                      txt_img.setText("");
                      txt_desp.setText("");
                      txt_not.setText("");
                      txt_quan.setText("");
                      System.out.println("on est la12354789952");
           Alert alert = new Alert(AlertType.CONFIRMATION," produit modifier  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
          System.out.println("on est la3333333333333");
            afichier();
            System.out.println("on est la44444444444444");
        } else {
           Alert alert = new Alert(AlertType.ERROR ,"Erreur de modifier  ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
        }

    }

    @FXML
    private void Suppproduit(MouseEvent event) {
        
        
//        System.out.println("aaaaaaaaaaaaaaaaa");
//       
//       String  searchId=      txt_searchId.getText();
//       
//       String     catt=(String) catid.getValue();
//         String    libb=      txt_lib.getText();
//        String    marc=     txt_mar.getText();
//          String   pri=   txt_prix.getText();
//        String   des= txt_desc.getText();
//        String   desp=  txt_desp.getText();
//         String   nt=  txt_not.getText();
//           String quan = txt_quan.getText();    
//     int abc = Integer.parseInt(txt_searchId.getText());
//        System.out.println(abc);
//        if (catt!=""&&!libb.equals("")&&!marc.equals("")&&!pri.equals("")&&!des.equals("")&&!desp.equals("")&&! nt.equals("")&&! quan.equals("")) {
//             
//              //sp.suppproduit(new Produit(Integer.parseInt(txt_searchId.getText()),(String) catid.getValue(), txt_lib.getText(),txt_mar.getText(),Float.parseFloat(txt_prix.getText()), txt_desc.getText(),txt_img.getText(),txt_desp.getText(),Float.parseFloat(txt_not.getText()),Integer.parseInt(txt_quan.getText() )));
//       sp.suppproduit(new Produit(Integer.parseInt(txt_searchId.getText()),(String) catid.getValue(), txt_lib.getText(),txt_mar.getText(),Float.parseFloat(txt_prix.getText()), txt_desc.getText(),txt_img.getText(),txt_desp.getText(),Float.parseFloat(txt_not.getText()),Integer.parseInt(txt_quan.getText() )));
//                  
//              
//             System.out.println(txt_searchId.getText());
//              
//                     // txt_cat.setText("");
//                      catid.getValue();
//                      txt_lib.setText("");
//                      txt_mar.setText("");
//                      txt_prix.setText("");
//                      txt_desc.setText("");
//                      txt_img.setText("");
//                      txt_desp.setText("");
//                      txt_not.setText("");
//                      txt_quan.setText("");
//                        System.out.println("ddddddddddd");
//           Alert alert = new Alert(AlertType.CONFIRMATION," produit suprimé  avec succes", javafx.scene.control.ButtonType.OK);
//          alert.showAndWait();
//            System.out.println("eeeeeeeeeee");
//            afichier();
//        } else {
//               System.out.println("ffffffffffffffffff");
//           Alert alert = new Alert(AlertType.ERROR ,"Erreur de suprission  ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
//          alert.showAndWait();
//        }



         String     catt=(String) catid.getValue();
         String    libb=      txt_lib.getText();
        String    marc=     txt_mar.getText();
          String   pri=   txt_prix.getText();
        String   des= txt_desc.getText();
        String   desp=  txt_desp.getText();
         String   nt=  txt_not.getText();
           String quan = txt_quan.getText();       
        if (!libb.equals("")&&!marc.equals("")&&!pri.equals("")&&!des.equals("")&&!desp.equals("")&&! nt.equals("")&&! quan.equals("")) {
              sp.suppproduit(Integer.parseInt(txt_searchId.getText()));
                     
            
                      catid.setValue("");
                      txt_lib.setText("");
                      txt_mar.setText("");
                      txt_prix.setText("");
                      txt_desc.setText("");
                      txt_img.setText("");
                      txt_desp.setText("");
                      txt_not.setText("");
                      txt_quan.setText("");
           Alert alert = new Alert(AlertType.CONFIRMATION," produit suprimé  avec succes", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
            afichier();
        } else {
           Alert alert = new Alert(AlertType.ERROR ,"Erreur de suprission  ="+txt_searchId.getText()+"", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
        }

    }
 /* public void showimage(){
     
         Connection cnx = maConnexion.getInstance().getCnx();
         String req = "SELECT image FROM produit";
         
         byte byteImg[];
         Blob blob;
         try {
         Statement st = cnx.createStatement();
         ResultSet rs = st.executeQuery(req);
         blob=rs.getBlob("image");
         byteImg=blob.getBytes (1, (int) blob.length ());
          Image img =new Image (new ByteArrayInputStream (byteImg),imageuit.getFitWidth(),imageuit.getFitHeight(),true,true);
         imageuit.setImage(img); 
     } catch (SQLException ex) {
          ex.printStackTrace();
              }
}*/
  
    @FXML
    private void importimage() {
         byte byteImg[];
        Blob blob;
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Images files","*.png","*.jpg"));
        File f= fc.showOpenDialog(null);
        if(f!=null){
            lab_url.setText(f.getAbsolutePath());
            Image imag = new Image(f.toURI().toString(),imageuit.getFitWidth(),imageuit.getFitHeight(),true,true);
            
             imageuit.setImage(imag);
            
        }
        
        
        
        
    }
}
        
        
        
        
    

