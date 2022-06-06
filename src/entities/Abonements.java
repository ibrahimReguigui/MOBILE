/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author infoevo
 */
public class Abonements {
    
    private int id;
    private String nom_sportif;
    private String prenom_sportif;
    private Date dated;
    private Date datef;

    @Override
    public String toString() {
        return "Abonements{" + "id=" + id + ", nom_sportif=" + nom_sportif + ", prenom_sportif=" + prenom_sportif + ", dated=" + dated + ", datef=" + datef + '}';
    }

    public Abonements(int id, String nom_sportif, String prenom_sportif, Date dated, Date datef) {
        this.id = id;
        this.nom_sportif = nom_sportif;
        this.prenom_sportif = prenom_sportif;
        this.dated = dated;
        this.datef = datef;
    }

    
    public Abonements(String nom_sportif, String prenom_sportif, Date dated, Date datef) {
        this.nom_sportif = nom_sportif;
        this.prenom_sportif = prenom_sportif;
        this.dated = dated;
        this.datef = datef;
        
    }

 

    public String getnom_sportif() {
        return nom_sportif;
    }

    public String getPrenom_sportif() {
        return prenom_sportif;
    }

    public Date getDated() {
        return dated;
    }

    public Date getDatef() {
        return datef;
    }



    public void setnom_sportif(String nom_sportif) {
        this.nom_sportif = nom_sportif;
    }

    public void setPrenom_sportif(String prenom_sportif) {
        this.prenom_sportif = prenom_sportif;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public void setDatef(Date datef) {
        this.datef = datef;
    }


   
    
    
    
    
}
