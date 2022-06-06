/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author rocky
 */
public class Exercice {
    
    public int id ;
    public String nomExercice,descriptionExercice,categorieExercice;
    public int nbrRepetition,nbrSerie;

    
    
    public Exercice() {
    }

    
    public Exercice(int id, String nomExercice, String descriptionExercice, String categorieExercice, int nbrRepetition, int nbrSerie) {
        this.id = id;
        this.nomExercice = nomExercice;
        this.descriptionExercice = descriptionExercice;
        this.categorieExercice = categorieExercice;
        this.nbrRepetition = nbrRepetition;
        this.nbrSerie = nbrSerie;
    }

    public Exercice(String nomExercice, String descriptionExercice, String categorieExercice, int nbrRepetition, int nbrSerie) {
        this.nomExercice = nomExercice;
        this.descriptionExercice = descriptionExercice;
        this.categorieExercice = categorieExercice;
        this.nbrRepetition = nbrRepetition;
        this.nbrSerie = nbrSerie;
    }

    public Exercice(String valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public String getDescriptionExercice() {
        return descriptionExercice;
    }

    public void setDescriptionExercice(String descriptionExercice) {
        this.descriptionExercice = descriptionExercice;
    }

    public String getCategorieExercice() {
        return categorieExercice;
    }

    public void setCategorieExercice(String categorieExercice) {
        this.categorieExercice = categorieExercice;
    }

    public int getNbrRepetition() {
        return nbrRepetition;
    }

    public void setNbrRepetition(int nbrRepetition) {
        this.nbrRepetition = nbrRepetition;
    }

    public int getNbrSerie() {
        return nbrSerie;
    }

    public void setNbrSerie(int nbrSerie) {
        this.nbrSerie = nbrSerie;
    }
    
    
    
}
