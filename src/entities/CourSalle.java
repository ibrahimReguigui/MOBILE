/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.codename1.ui.TextField;





/**
 *
 * @author Asus
 */
public class CourSalle {
    private int salle,id,nbrActuel,nbrTotal;

   
    private String information,nomCour,tCour,date;

    public CourSalle() {
    }

    public CourSalle(String nomCour) {
        this.nomCour = nomCour;
    }
 public void setSalle(int salle) {
        this.salle = salle;
    }

    public int getSalle() {
        return salle;
    }

    public CourSalle(int salle, int id, int nbrActuel, int nbrTotal, String information, String nomCour, String tCour, String date) {
        this.salle = salle;
        this.id = id;
        this.nbrActuel = nbrActuel;
        this.nbrTotal = nbrTotal;
        this.information = information;
        this.nomCour = nomCour;
        this.tCour = tCour;
        this.date = date;
    }
    public CourSalle(int id, int nbrActuel, int nbrTotal, String information, String nomCour) {
        this.id = id;
        this.nbrActuel = nbrActuel;
        this.nbrTotal = nbrTotal;
        this.information = information;
        this.nomCour = nomCour;
    }

    public CourSalle(int nbrActuel, int nbrTotal, String information, String nomCour, String tCour, String date) {
        this.nbrActuel = nbrActuel;
        this.nbrTotal = nbrTotal;
        this.information = information;
        this.nomCour = nomCour;
        this.tCour = tCour;
        this.date = date;
    }

    public CourSalle(int id, int nbrActuel, int nbrTotal, String date, String tCour, String information, String nomCour) {
        this.id = id;
        this.nbrActuel = nbrActuel;
        this.nbrTotal = nbrTotal;
        this.date = date;
        this.tCour = tCour;
        this.information = information;
        this.nomCour = nomCour;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setNbrActuel(int nbrActuel) {
        this.nbrActuel = nbrActuel;
    }

    public void setNbrTotal(int nbrTotal) {
        this.nbrTotal = nbrTotal;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void settCour(String tCour) {
        this.tCour = tCour;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setNomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    public int getId() {
        return id;
    }

    public int getNbrActuel() {
        return nbrActuel;
    }

    public int getNbrTotal() {
        return nbrTotal;
    }

    public String getDate() {
        return date;
    }

    public String gettCour() {
        return tCour;
    }

    public String getInformation() {
        return information;
    }

    public String getNomCour() {
        return nomCour;
    }
    
    @Override
    public String toString() {
        return "CourSalle{" + "id=" + id + ", nbrActuel=" + nbrActuel + ", nbrTotal=" + nbrTotal + ", date=" + date + ", tCour=" + tCour + ", information=" + information + ", nomCour=" + nomCour + '}';
    }

    
}
