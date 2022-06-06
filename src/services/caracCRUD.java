/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.caracteristiquesportif;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author Asus
 */
public class caracCRUD {

    static Connection cnx;

    public caracCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public float CalculProtein(float poids) {
        float c = (float) (2 * poids);
        return c;

    }

    public float CalculCaloriesh(float poids, float taille, int age) {

        float c = (float) ((10 * poids) + 6.25 * taille - 5 * age + 5);

        return c;
    }

    public float CalculCaloriesf(float poids, float taille, int age) {

        float c = (float) ((10 * poids) + 6.25 * taille - 5 * age - 161);

        return c;
    }

    public float CalculFat(float poids) {

        float c = (float) (1 * poids);
        return c;
    }

    public float CalculBmi(float poids, float taille) {

        float c = (poids / ((taille * taille) / 100));
        return c;

    }

    public float CalculCarbsG(float poids, float taille, int age) {

        float c = (float) (6.3 * poids + 3.25 * taille - 10 * age + 2);
        return c;

    }

    public float CalculCarbsP(float poids, float taille, int age) {

        float c = (float) (4.2 * poids + 1.25 * taille - 15 * age + 2)*-1;
        return c;

    }

    public void ajouterCarac(caracteristiquesportif c) {
        String requete = "INSERT INTO caracteristiquesportif( id_sportif_id, taille_sportif, poid_sportif, age_sportif,"
                + " sexe, objectif_nutrition, bmi_sportif, besoin_proteine, besoin_carb, besoin_calories) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId_sportif_id());
            pst.setInt(2, c.getTaille_sportif());
            pst.setFloat(3, c.getPoid_sportif());
            pst.setFloat(4, c.getAge_sportif());
            pst.setString(5, c.getSexe());
            pst.setString(6, c.getObjectif_nutrition());
            pst.setFloat(7, CalculBmi(c.getPoid_sportif(), c.getTaille_sportif()));
            pst.setFloat(8, CalculProtein(c.getPoid_sportif()));
            if (c.getObjectif_nutrition() == "Gain_de_poid") {
                pst.setFloat(9, CalculCarbsG(c.getPoid_sportif(), c.getTaille_sportif(), c.getAge_sportif()));
            } else {
                pst.setFloat(9, CalculCarbsP(c.getPoid_sportif(), c.getTaille_sportif(), c.getAge_sportif()));
            }
            if (c.getSexe() == "Homme") {
                pst.setFloat(10, CalculCaloriesh(c.getPoid_sportif(), c.getTaille_sportif(), c.getAge_sportif()));
            } else {
                pst.setFloat(10, CalculCaloriesf(c.getPoid_sportif(), c.getTaille_sportif(), c.getAge_sportif()));
            }
            pst.executeUpdate();
            System.err.println("added successfully !!!!!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
     public List<caracteristiquesportif> getCarac() {
        List<caracteristiquesportif> cs = new ArrayList<>();
        try {
            String req = "SELECT * FROM caracteristiquesportif";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
             
            while (rs.next()) {
               caracteristiquesportif c = new caracteristiquesportif();
                c.setId(rs.getInt(1));
                c.setId_sportif_id(rs.getInt(2));
                c.setTaille_sportif(rs.getInt(3));
                c.setPoid_sportif(rs.getInt(4));
                c.setAge_sportif(rs.getInt(5));
                c.setSexe(rs.getString(6));
                c.setObjectif_nutrition(rs.getString(7));
                c.setBmi_sportif(rs.getFloat(8));               
                c.setBesoin_proteine(rs.getFloat(9));
                c.setBesoin_carb(rs.getFloat(10));
                c.setBesoin_calories(rs.getFloat(11));   
                cs.add(c);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage() + "fd");
        }
        return cs;
    }
      
                 public void supprimerE(caracteristiquesportif c) {
        try {
            String req = "DELETE FROM caracteristiquesportif where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
