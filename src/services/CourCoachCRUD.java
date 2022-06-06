/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CourCoach;
import entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author Asus
 */
public class CourCoachCRUD {

    static Connection cnx;

    public CourCoachCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterDisponibilite(User coach, Date date, Time time) {
        try {
            String req = "INSERT INTO disponibilite_coach (id_coach_id,date,time) VALUES (?,?,?) ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setDate(2, date);
            pst.setTime(3, time);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean testReservationExistante(User coach, Date date, Time time) {
        try {
            String req = "SELECT * FROM disponibilite_coach WHERE id_coach_id= ? AND Date= ? AND Time=?";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setDate(2, date);
            pst.setTime(3, time);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                return true;
            }
            else
            {
            return false;}
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public List<CourCoach> afficherDisponibilite(User coach) {
        List<CourCoach> disponibilite = new ArrayList<>();
        try {
            String req = "SELECT * FROM disponibilite_coach WHERE id_coach_id= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourCoach cour = new CourCoach();
                cour.setId(rs.getInt(1));
                cour.setId_coach_id(rs.getInt(2));
                cour.setDate(rs.getDate(3));
                cour.setTime(rs.getTime(4));
                disponibilite.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return disponibilite;
    }

    public void supprimerDisponibilite(User coach, CourCoach cour) {
        try {
            String req = "DELETE FROM disponibilite_coach where date=? AND time=? AND id_coach_id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setDate(1, cour.getDate());
            pst.setTime(2, cour.getTime());
            pst.setInt(3, coach.getId());
            pst.executeUpdate();
            String req1 = "DELETE FROM reservation_Coach WHERE id_coach_id= ? AND date= ? AND time= ?  ";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, coach.getId());
            pst1.setDate(2, cour.getDate());
            pst1.setTime(3, cour.getTime());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<CourCoach> afficherToutesLesDisponibilite() {
        List<CourCoach> disponibilite = new ArrayList<>();
        try {
            String req = "SELECT * FROM disponibilite_coach ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                CourCoach cour = new CourCoach();
                cour.setId(rs.getInt(1));
                cour.setId_coach_id(rs.getInt(2));
                cour.setDate(rs.getDate(3));
                cour.setTime(rs.getTime(4));
                String req1 = "SELECT concat(concat(name,\" \"),lastname) as name FROM User where id=?";
                PreparedStatement pst1 = cnx.prepareStatement(req1);
                pst1.setInt(1, cour.getId_coach_id());
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    cour.setNom_coach(rs1.getString(1));
                }
                disponibilite.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return disponibilite;
    }

    public void demandeReservation(User sportif, CourCoach cour) {

        try {

            String req = "INSERT INTO reservation_Coach(id_coach_id,date,time,id_participant_id,etat) VALUES (?,?,?,?,? )";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getId_coach_id());
            pst.setDate(2, cour.getDate());
            pst.setTime(3, cour.getTime());
            pst.setInt(4, sportif.getId());
            pst.setString(5, "En Attente");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourCoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int findIdUserByName(String s) {
        try {
            String[] split = s.split(" ", 0);
            String req = "SELECT id FROM User where name=? AND lastname=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, split[0]);
            pst.setString(2, split[1]);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            };
        } catch (SQLException ex) {
            Logger.getLogger(CourCoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<CourCoach> afficherReservationEffectue(User sportif) {
        List<CourCoach> disponibilite = new ArrayList<>();
        try {
            String req = "SELECT * FROM reservation_coach WHERE id_participant_id= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, sportif.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourCoach cour = new CourCoach();
                cour.setId(rs.getInt(1));
                cour.setId_coach_id(rs.getInt(3));
                cour.setDate(rs.getDate(5));
                cour.setTime(rs.getTime(6));
                cour.setEtat(rs.getString(4));
                cour.setId_participant_id(rs.getInt(2));
                String req1 = "SELECT concat(concat(name,\" \"),lastname) as nom_coach FROM User where id=?";
                PreparedStatement pst1 = cnx.prepareStatement(req1);
                pst1.setInt(1, cour.getId_coach_id());
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    System.out.println("**********"+rs1.getString(1));
                    cour.setNom_coach(rs1.getString(1));
                }
                disponibilite.add(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return disponibilite;
    }

    public boolean testCourExsitant(User sportif, CourCoach cour) {
        try {
            String req = "SELECT * FROM reservation_coach WHERE id_coach_id= ? AND id_participant_id= ? AND Date=? AND time=?";
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, cour.getId_coach_id());
            pst.setInt(2, sportif.getId());
            pst.setDate(3, cour.getDate());
            pst.setTime(4, cour.getTime());
            ResultSet rs = pst.executeQuery();;
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
public List<CourCoach> getDemandes2(User coach,String s) {
        List<CourCoach> demandes = new ArrayList<>();
        try {
            String req = "SELECT * FROM reservation_coach WHERE id_coach_id=? AND etat=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setString(2, s);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourCoach cour = new CourCoach();
                cour.setId(rs.getInt(1));
                cour.setId_coach_id(rs.getInt(3));
                cour.setDate(rs.getDate(5));
                cour.setTime(rs.getTime(6));
                cour.setEtat(rs.getString(4));
                cour.setId_participant_id(rs.getInt(2));
                String req1 = "SELECT concat(concat(name,\" \"),lastname) as nom_participant FROM User where id=?";
                PreparedStatement pst1 = cnx.prepareStatement(req1);
                pst1.setInt(1, cour.getId_participant_id());
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    System.out.println("**********"+rs1.getString(1));
                    cour.setNom_participant(rs1.getString(1));
                }
                demandes.add(cour);
                System.out.println(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return demandes;
    }
    public List<CourCoach> getDemandes(User coach,String s) {
        List<CourCoach> demandes = new ArrayList<>();
        try {
            String req = "SELECT * FROM reservation_coach WHERE id_coach_id=? AND etat=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setString(2, s);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CourCoach cour = new CourCoach();
                cour.setId(rs.getInt(1));
                cour.setId_coach_id(rs.getInt(3));
                cour.setDate(rs.getDate(5));
                cour.setTime(rs.getTime(6));
                cour.setEtat(rs.getString(4));
                cour.setId_participant_id(rs.getInt(2));
                String req1 = "SELECT concat(concat(name,\" \"),lastname) as nom_participant FROM User where id=?";
                PreparedStatement pst1 = cnx.prepareStatement(req1);
                pst1.setInt(1, cour.getId_coach_id());
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    System.out.println("**********"+rs1.getString(1));
                    cour.setNom_participant(rs1.getString(1));
                }
                demandes.add(cour);
                System.out.println(cour);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return demandes;
    }

    public void annulerreservation_coach(User coach, Date date, Time time, int id) {
        try {
            String req = "DELETE FROM reservation_coach WHERE id_coach_id= ? AND date= ? AND time= ?  AND id_participant_id= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setDate(2, date);
            pst.setTime(3, time);
            pst.setInt(4, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void accepterReservation(User coach, Date date, Time time, int id) {
        try {
            String req = "UPDATE reservation_coach SET etat='Acceptée' WHERE id_coach_id= ? AND date= ? AND time= ?  AND id_participant_id= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, coach.getId());
            pst.setDate(2, date);
            pst.setTime(3, time);
            pst.setInt(4, id);
            pst.executeUpdate();
            String req1 = "DELETE FROM disponibilite_coach WHERE id_coach_id= ? AND date= ? AND time= ? ";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, coach.getId());
            pst1.setDate(2, date);
            pst1.setTime(3, time);
            pst1.executeUpdate();
            String req2 = "DELETE FROM reservation_coach WHERE id_coach_id= ? AND date= ? AND time= ? AND id_participant_id<>?";
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            pst2.setInt(1, coach.getId());
            pst2.setDate(2, date);
            pst2.setTime(3, time);
            pst2.setInt(4, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public void annulerReservation(User sportif, Date date, Time time, int id) {
        try {
            String req = "DELETE FROM reservation_coach WHERE id_coach_id= ? AND date= ? AND time= ? AND id_participant_id= ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
                    pst.setInt(1, id);
                    pst.setDate(2, date);
                    pst.setTime(3, time);
                    pst.setInt(4, sportif.getId());
                    pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   public String getEmailById(int id){
        try {
            String req = "SELECT eMail FROM User WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                return rs.getString(1).toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourCoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
   }
   public void annulerReservationCoach(int id, Date date, Time time) {
        try {
            String req = "DELETE FROM reservation_coach WHERE id_coach_id= ? AND date= ? AND time= ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.setDate(2, date);
            pst.setTime(3, time);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
