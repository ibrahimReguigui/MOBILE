/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import interfaces.Iproduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.maConnexion;

/**
 *
 * @author MSI
 */
public class ServiceProduit implements Iproduit {
 Connection cnx = maConnexion.getInstance().getCnx();

    @Override
    public void ajouterproduit(Produit s) {
         String req = "insert into produit(id,categorie,libelle,marque,prix,description,image,disponibilite,note,quantite,category_id) values(?,?,?,?,?,?,?,?,?,?,?)";

          try {
            PreparedStatement ps = cnx.prepareStatement(req);         
    
    
            ps.setInt(1, s.getid());
            ps.setString(2, s.getcategorie());
            ps.setString(3, s.getLibelle());
            ps.setString(4, s.getMarque());
            ps.setFloat(5, s.getPrix());
             ps.setString(6, s.getdescription());
            ps.setString(7, s.getimage());
            ps.setString(8, s.getDisponibilite());
            ps.setFloat(9, s.getNote());
            ps.setInt(10, s.getQuantite());
           ps.setInt(11, s.getCategory_id());
            ps.execute();
            System.out.println("2 : produit ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    

    }

    @Override
    public List<Produit> afficherproduit() {
       List<Produit> prod = new ArrayList<>();
        
        //String req = "SELECT * FROM produit";
        String req = "SELECT * FROM produit";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                prod.add(new Produit(rs.getInt("id"),rs.getInt("category_id"),rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getInt(10)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return prod; }
    
    
    


    @Override
    public void modifierproduit(Produit s) {
          try{
              //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        String req = "UPDATE produit SET   `categorie`='" +s.getcategorie()
                                             +"',`category_id`='" +s.getCategory_id()
                                             + "',`libelle`='" +s.getLibelle()
                                              + "',`marque`='" + s.getMarque()
                                            + "',`prix`='" +  +s.getPrix()
                                               + "',`description`='" + s.getdescription()
                                                + "',`image`='" + s.getimage()
                                              + "',`disponibilite`='" + s.getDisponibilite()
                                                 + "',`note`='" + s.getNote()
                                                   + "',`quantite`='" + s.getQuantite()
                                                   + "' where id=" + s.getid() ;  
        // System.out.println("bbbbbbbbbbbbbbbbbbbb");
          Statement ps = cnx.createStatement();
          System.out.println("aaaaaaaaaaaaaaaaaaaa");
    ps.executeUpdate(req);
    System.out.println("bbbbbbbbbbbbbbbbbbbb");
    
        System.out.println("Done. STOCK bien modifier ");
        }catch(SQLException e){
            System.out.println("ccccccccccccccccccccccc");
        System.out.println(e.getMessage());
         System.out.println("dddddddddddddddd");
    
    }       }

    @Override
    public void suppproduit(int s) {

//        try (PreparedStatement ps = cnx.prepareStatement("DELETE FROM `produit` WHERE  `id` = ?")
//) {
//             
//    ps.setInt(1,s.getid());
// 
//    if (ps.executeUpdate() > 0)
//        System.out.println("stock with id = "+s+" deleted successfully.");
//    else
//        System.out.println("Record not found.");
//      
//}       catch (SQLException ex) {
//          ex.printStackTrace();        }   
        try {
            String req="delete from produit where id ="+s;
            Statement pst = cnx.createStatement();
            pst.executeUpdate(req);
            System.out.println("mriguel!!");
        } catch (SQLException ex) {
            System.out.println("eee");
            System.out.println(ex.getMessage());
        }

}
   
    public void DeleteProduit(Produit variable) {
        try {
            String requete;
            requete = "delete from produit where id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, variable.getid());
            pst.executeUpdate();
            pst.executeUpdate(); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterproduit1(Produit s) {
        // String req = "insert into produit (categorie,libelle,marque,prix,description,disponibilite,note,quantite) values(?,?,?,?,?,?,?,?)";
         
        
         String req = "insert into produit (categorie,libelle,marque,prix,description,disponibilite,note,quantite,category_id) values(?,?,?,?,?,?,?,?,?)";

          try {
            PreparedStatement ps = cnx.prepareStatement(req);         
    
    
           
            ps.setString(1, s.getcategorie());
            ps.setString(2, s.getLibelle());
            ps.setString(3, s.getMarque());
            ps.setFloat(4, s.getPrix());
             ps.setString(5, s.getdescription());
            ps.setString(6, s.getDisponibilite());
            ps.setFloat(7, s.getNote());
            ps.setInt(8, s.getQuantite());
            ps.setInt(9, s.getCategory_id());

            ps.execute();
            System.out.println("2 : produit ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}

    @Override
    public List<Produit> filtrerproduit() {
         List<Produit> prod = new ArrayList<>();
        
        String req = "SELECT * FROM produit where  quantite > 100 ";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                prod.add(new Produit(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getInt(10)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return prod; }
    
}