/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Caracteristiquesportif {
    private int id,tailleSportif,poidSportif,ageSportif;
    private String sexe,objectifNutrition;
    private float bmiSportif,besoinProteine,besoinCarb,besoinCalories;

    public Caracteristiquesportif(int id, int tailleSportif, int poidSportif, int ageSportif, String sexe, String objectifNutrition, float bmiSportif, float besoinProteine, float besoinCarb, float besoinCalories) {
        this.id = id;
        this.tailleSportif = tailleSportif;
        this.poidSportif = poidSportif;
        this.ageSportif = ageSportif;
        this.sexe = sexe;
        this.objectifNutrition = objectifNutrition;
        this.bmiSportif = bmiSportif;
        this.besoinProteine = besoinProteine;
        this.besoinCarb = besoinCarb;
        this.besoinCalories = besoinCalories;
    }

    public Caracteristiquesportif(int tailleSportif, int poidSportif, int ageSportif, String sexe, String objectifNutrition, float bmiSportif, float besoinProteine, float besoinCarb, float besoinCalories) {
        this.tailleSportif = tailleSportif;
        this.poidSportif = poidSportif;
        this.ageSportif = ageSportif;
        this.sexe = sexe;
        this.objectifNutrition = objectifNutrition;
        this.bmiSportif = bmiSportif;
        this.besoinProteine = besoinProteine;
        this.besoinCarb = besoinCarb;
        this.besoinCalories = besoinCalories;
    }

    public Caracteristiquesportif(int tailleSportif, int poidSportif, int ageSportif, String sexe, String objectifNutrition) {
        this.tailleSportif = tailleSportif;
        this.poidSportif = poidSportif;
        this.ageSportif = ageSportif;
        this.sexe = sexe;
        this.objectifNutrition = objectifNutrition;
    }

    public Caracteristiquesportif(int tailleSportif, int poidSportif, int ageSportif) {
        this.tailleSportif = tailleSportif;
        this.poidSportif = poidSportif;
        this.ageSportif = ageSportif;
    }

    public Caracteristiquesportif() {
    }
    
    
    
    
    
    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTailleSportif() {
        return tailleSportif;
    }

    public void setTailleSportif(int tailleSportif) {
        this.tailleSportif = tailleSportif;
    }

    public int getPoidSportif() {
        return poidSportif;
    }

    public void setPoidSportif(int poidSportif) {
        this.poidSportif = poidSportif;
    }

    public int getAgeSportif() {
        return ageSportif;
    }

    public void setAgeSportif(int ageSportif) {
        this.ageSportif = ageSportif;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getObjectifNutrition() {
        return objectifNutrition;
    }

    public void setObjectifNutrition(String objectifNutrition) {
        this.objectifNutrition = objectifNutrition;
    }

    public float getBmiSportif() {
        return bmiSportif;
    }

    public void setBmiSportif(float bmiSportif) {
        this.bmiSportif = bmiSportif;
    }

    public float getBesoinProteine() {
        return besoinProteine;
    }

    public void setBesoinProteine(float besoinProteine) {
        this.besoinProteine = besoinProteine;
    }

    public float getBesoinCarb() {
        return besoinCarb;
    }

    public void setBesoinCarb(float besoinCarb) {
        this.besoinCarb = besoinCarb;
    }

    public float getBesoinCalories() {
        return besoinCalories;
    }

    public void setBesoinCalories(float besoinCalories) {
        this.besoinCalories = besoinCalories;
    }

    @Override
    public String toString() {
        return "Caracteristiquesportif{" + "id=" + id + ", tailleSportif=" + tailleSportif + ", poidSportif=" + poidSportif + ", ageSportif=" + ageSportif + ", sexe=" + sexe + ", objectifNutrition=" + objectifNutrition + ", bmiSportif=" + bmiSportif + ", besoinProteine=" + besoinProteine + ", besoinCarb=" + besoinCarb + ", besoinCalories=" + besoinCalories + '}';
    }
    
    
  
}