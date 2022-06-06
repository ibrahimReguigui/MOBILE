/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author Asus
 */
public class CourSalle {
    private int id,utilisateur_id,nbr_total,nbr_actuel ;
    private String nom_cour,information,nom_salle;
    private Date date;
    private Time t_cour;
    
    public CourSalle() {
    }

    public CourSalle(int utilisateur_id, int nbr_total, int nbr_actuel, String nom_cour, String information, String nom_salle, Date date, Time t_cour) {
        this.utilisateur_id = utilisateur_id;
        this.nbr_total = nbr_total;
        this.nbr_actuel = nbr_actuel;
        this.nom_cour = nom_cour;
        this.information = information;
        this.nom_salle = nom_salle;
        this.date = date;
        this.t_cour = t_cour;
    }

    public CourSalle(int nbr_total, int nbr_actuel, String nom_cour, String information, String nom_salle, Date date, Time t_cour) {
        this.nbr_total = nbr_total;
        this.nbr_actuel = nbr_actuel;
        this.nom_cour = nom_cour;
        this.information = information;
        this.nom_salle = nom_salle;
        this.date = date;
        this.t_cour = t_cour;
    }

    public CourSalle(int utilisateur_id, String nom_cour,Date date, Time t_cour, int nbr_total, String information) {
        this.utilisateur_id = utilisateur_id;
        this.nbr_total = nbr_total;
        this.nbr_actuel = nbr_actuel;
        this.nom_cour = nom_cour;
        this.information = information;
        this.date = date;
        this.t_cour = t_cour;
    }

    public CourSalle(int utilisateur_id,  int nbr_total, String nom_cour, String information, Date date, Time t_cour) {
        this.utilisateur_id = utilisateur_id;
        this.nbr_total = nbr_total;
        this.nom_cour = nom_cour;
        this.information = information;
        this.date = date;
        this.t_cour = t_cour;
    }

    @Override
    public String toString() {
        return "CourSalle{" + "id=" + id + ", utilisateur_id=" + utilisateur_id +", nbr_total=" + nbr_total + ", nbr_actuel=" + nbr_actuel + ", nom_cour=" + nom_cour + ", information=" + information + ", date=" + date + ", t_cour=" + t_cour + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }


    public void setNbr_total(int nbr_total) {
        this.nbr_total = nbr_total;
    }

    public void setNbr_actuel(int nbr_actuel) {
        this.nbr_actuel = nbr_actuel;
    }

    public void setNom_cour(String nom_cour) {
        this.nom_cour = nom_cour;
    }

    public void setNom_salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setT_cour(Time t_cour) {
        this.t_cour = t_cour;
    }

    public int getId() {
        return id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

  
    public int getNbr_total() {
        return nbr_total;
    }

    public int getNbr_actuel() {
        return nbr_actuel;
    }

    public String getNom_cour() {
        return nom_cour;
    }

    public String getInformation() {
        return information;
    }

    public Date getDate() {
        return date;
    }

    public Time getT_cour() {
        return t_cour;
    }
    
}
