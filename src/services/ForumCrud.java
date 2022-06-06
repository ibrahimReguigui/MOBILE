/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
//table commentaire contient comentaire et id sportif et reliée à un id Forum 
import entities.Forum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author nahaw
 */
public class ForumCrud {
  Connection cnx; 
   public ForumCrud() {
        
         cnx = MyConnection.getInstance().getCnx();
    
    }
   
    public void ajouterForum() {
        try {
            String requete ="INSERT INTO forum (idForum,Sujet,id) VALUES (2,'les avantages',12)";

            Statement st = cnx.createStatement(); 
            st.executeUpdate(requete); //update pour les requete qui mettent à jour la base et on a aussi querry
            System.out.println("forum ajoutée avec succès");
            
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
        }
    }
    
    public void ajouterForum2(Forum f) {
        try {
            String sql = "INSERT INTO forum (Sujet, id)"
                    + "VALUES (?, ?) ";
            PreparedStatement pst = cnx.prepareStatement(sql); 
            //Statement st = new MyConnection().getcnx().createStatement(); 
            //st = cnx.createStatement(); 
            //ResultSet rs = st.executeQuery(sql);
            pst.setString(1,f.getSujet());
            pst.setInt(2,f.getId());
            int rows = pst.executeUpdate(); 
            if (rows > 0) {
                System.out.println("a new row has been successfuly added to the database ");
            }
            //System.out.println ("Votre forum a été ajoutée"); 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     /**
     *
     * @return 
     */
public ObservableList<Forum> afficherForumCrud (){
       
        ObservableList<Forum> myList = FXCollections.observableArrayList ();
        try {
            String requete3 = "SELECT * FROM forum ";
            //Statement st = new MyConnection().getcnx().createStatement();
            Statement st = cnx.createStatement(); 
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()){
                Forum f = new Forum(); 
                f.setIdForum(rs.getInt(1)); 
                f.setSujet(rs.getString("Sujet")); 
                f.setId(rs.getInt(1));  
                myList.add(f);     
            } 
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
         return myList;
    }

public List<Forum> afficherForum() {
        
            List<Forum> myList = new ArrayList<>();
        try {
            String requete3 ="SELECT * FROM forum";
            //Statement st = new MyConnection().getcnx().createStatement(); 
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3); 
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
       return myList;              
    } 
    
 public void modifierForum(Forum f) {
        try{    
        String req = "UPDATE forum SET Sujet = ?, id=? WHERE idForum= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,f.getSujet());
        ps.setInt(2,f.getId());
        ps.setInt(3,f.getIdForum());
        System.out.println("Modification réalisée");
        ps.executeUpdate();
        System.out.println("Une ligne modifiée dans la table");
       }
       catch(SQLException ex){
            System.err.println(ex.getMessage());
       }           
    }
 
public void supprimerForum(int idForum) {
      
             try{
            String reqs = "DELETE FROM forum WHERE idForum = ?";
            PreparedStatement ps = cnx.prepareStatement(reqs);
            System.out.println("Suppression faite");
            ps.setInt(1,idForum);
            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table");
       }
       catch(SQLException ex){
           System.err.println(ex.getMessage());
       }
             
    }  
    }
