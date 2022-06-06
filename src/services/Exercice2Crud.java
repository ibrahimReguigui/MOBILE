/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Exercice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.MyConnection;

/**
 *
 * @author Asus
 */
public class Exercice2Crud {
      static Connection cnx;

    public Exercice2Crud() {
        cnx = MyConnection.getInstance().getCnx();
    }
     public void supprimerE(Exercice e) {
        try {
            String req = "DELETE FROM exercice where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, e.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
