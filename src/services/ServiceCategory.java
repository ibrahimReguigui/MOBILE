/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Category;
import interfaces.ICategory;
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
 * @author infoevo
 */
public class ServiceCategory implements ICategory{
    
     Connection cnx = maConnexion.getInstance().getCnx();
     
     
         public void ajouterCategory(Category s) {
         String req = "insert into Category(name) values(?)";

          try {
            PreparedStatement ps = cnx.prepareStatement(req);         
    
    
            ps.setString(1, s.getName());
            ps.execute();
            System.out.println("2 : Categorie ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
}
             
    public List<Category> afficherCategory() {
       List<Category> cat = new ArrayList<>();
        
        //String req = "SELECT * FROM produit";
        String req = "SELECT * FROM Category";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                cat.add(new Category(rs.getString("name")));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return cat;
    }

    
}

