/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CourSalle;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author Asus
 */
public class CourSalleCRUD {

    static Connection cnx;

    public CourSalleCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public List<CourSalle> afficherCoursSalle(User salle) {
        List<CourSalle> planning = new ArrayList<>();
        try {
            String req = "SELECT * FROM cour_salle WHERE utilisateur_id= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, salle.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourSalle cour = new CourSalle();
                cour.setId(rs.getInt(1));
                cour.setUtilisateur_id(rs.getInt(2));
                cour.setNom_cour(rs.getString(4));
                cour.setDate(rs.getDate(8));
                cour.setT_cour(rs.getTime(9));
                cour.setNbr_total(rs.getInt(7));
                cour.setNbr_actuel(rs.getInt(6));
                cour.setInformation(rs.getString(5));
                planning.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return planning;
    }

    public void supprimerCour(CourSalle cour) {
        try {
            String req = "DELETE FROM cour_salle where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getId());
            pst.executeUpdate();
            String req2 = "DELETE FROM cour_salle WHERE id=?";
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            pst2.setInt(1, cour.getId());
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void ajouterCour(CourSalle cour) {
        try {
            String req = "INSERT INTO cour_salle(Utilisateur_id,Nom_cour,date,T_cour,Nbr_total,Information,nbr_actuel) VALUES (?,?,?,?,?,?,0) ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getUtilisateur_id());
            pst.setString(2, cour.getNom_cour());
            pst.setDate(3, cour.getDate());
            pst.setTime(4, cour.getT_cour());
            pst.setInt(5, cour.getNbr_total());
            pst.setString(6, cour.getInformation());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public boolean testCourExsitant(CourSalle cour) {
        try {
            String req = "SELECT * FROM cour_salle WHERE utilisateur_id= ? AND nom_cour= ? AND date=? AND t_cour=?";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getUtilisateur_id());
            pst.setString(2, cour.getNom_cour());
            pst.setDate(3, cour.getDate());
            pst.setTime(4, cour.getT_cour());
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                return true;
            }
            else
            {
            return false;}
        } catch (SQLException ex) {
            Logger.getLogger(CourSalleCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public void supprimerReservationCourSalle(User sportif, CourSalle cour) {
        try {
            String req = "DELETE FROM reservation_cour_salle WHERE id_cour_id= ? AND id_sportif_id= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getId());
            pst.setInt(2, sportif.getId());
            pst.executeUpdate();
            String req1 = "UPDATE cour_salle SET nbr_actuel=nbr_actuel-1 WHERE id= ?";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, cour.getId());;
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
      public List<CourSalle> affichageReservationCourSalleEffectue(User sportif) {
        List<CourSalle> reservationPossible = new ArrayList<>();
        try {
            String req = "SELECT * FROM cour_salle WHERE id IN (SELECT id_cour_id from reservation_cour_salle WHERE id_sportif_id= ? )";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, sportif.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourSalle cour = new CourSalle();
                cour.setId(rs.getInt(1));
                cour.setUtilisateur_id(rs.getInt(2));
                cour.setNom_cour(rs.getString(4));
                cour.setDate(rs.getDate(8));
                cour.setT_cour(rs.getTime(9));
                cour.setNbr_total(rs.getInt(7));
                cour.setInformation(rs.getString(5));
                cour.setNbr_actuel(rs.getInt(6));
                String req1 = "SELECT concat(concat(name,\" \"),lastname) as nom_salle FROM User where id=?";
                PreparedStatement pst1 = cnx.prepareStatement(req1);
                pst1.setInt(1, cour.getUtilisateur_id());
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    System.out.println("**********"+rs1.getString(1));
                    cour.setNom_salle(rs1.getString(1));
                }
                reservationPossible.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reservationPossible;
    }
       public List<CourSalle> afficherReservationPossible(User sportif) {
        List<CourSalle> reservationPossible = new ArrayList<>();
            try {
                String req = "SELECT * FROM cour_salle ";
                PreparedStatement pst = cnx.prepareStatement(req);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    CourSalle cour = new CourSalle();
                    cour.setId(rs.getInt(1));
                    cour.setUtilisateur_id(rs.getInt(2));
                    cour.setNom_cour(rs.getString(4));
                    cour.setDate(rs.getDate(8));
                    cour.setT_cour(rs.getTime(9));
                    cour.setNbr_total(rs.getInt(7));
                    cour.setInformation(rs.getString(5));
                    cour.setNbr_actuel(rs.getInt(6));
                    cour.setNom_salle(getNomSalleById(cour.getUtilisateur_id()));
                    reservationPossible.add(cour);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        
        return reservationPossible;
    }
        public String getNomSalleById(int id) {
        String nom = "";
        try {
            String req = "SELECT concat(concat(name,\" \"),lastname) as name FROM User where id=?";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourSalleCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }
    public boolean testCourComplet(CourSalle cour) {
        String req = "SELECT nbr_actuel FROM cour_salle WHERE id=?";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) >= cour.getNbr_total()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
     public boolean testDejaParticipant(User sportif, CourSalle cour) {
        try {
            String req = "SELECT * FROM reservation_cour_salle WHERE id_salle_id= ? ";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getUtilisateur_id());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(3) == sportif.getId()) {
                    System.out.println("truuuee");
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("falsseee");
        return false;
    }
    public void reserverCourSalle(User sportif, CourSalle cour) {
        try {
            String req = "UPDATE cour_salle SET nbr_actuel= ? WHERE id= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            cour.setNbr_actuel(cour.getNbr_actuel() + 1);
            pst.setInt(1, cour.getNbr_actuel());
            pst.setInt(2, cour.getId());
            pst.executeUpdate();
            String req1 = "INSERT INTO  reservation_cour_salle (id_salle_id,id_cour_id,id_sportif_id)  VALUES (?,?,?)";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, cour.getUtilisateur_id());
            pst1.setInt(2, cour.getId());
            pst1.setInt(3, sportif.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
