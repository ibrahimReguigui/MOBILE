/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Asus
 */
public class caracteristiquesportif {
    private int id,id_sportif_id,taille_sportif,poid_sportif,age_sportif;
    private String sexe,objectif_nutrition;
    private Float bmi_sportif,besoin_sportif,besoin_proteine,besoin_carb,besoin_calories;

    public caracteristiquesportif() {
    }

    public caracteristiquesportif(int id, int id_sportif_id, int taille_sportif, int poid_sportif, int age_sportif, String sexe, String objectif_nutrition, Float bmi_sportif, Float besoin_sportif, Float besoin_proteine, Float besoin_carb, Float besoin_calories) {
        this.id = id;
        this.id_sportif_id = id_sportif_id;
        this.taille_sportif = taille_sportif;
        this.poid_sportif = poid_sportif;
        this.age_sportif = age_sportif;
        this.sexe = sexe;
        this.objectif_nutrition = objectif_nutrition;
        this.bmi_sportif = bmi_sportif;
        this.besoin_sportif = besoin_sportif;
        this.besoin_proteine = besoin_proteine;
        this.besoin_carb = besoin_carb;
        this.besoin_calories = besoin_calories;
    }

    @Override
    public String toString() {
        return "caracteristiquesportif{" + "id=" + id + ", id_sportif_id=" + id_sportif_id + ", taille_sportif=" + taille_sportif + ", poid_sportif=" + poid_sportif + ", age_sportif=" + age_sportif + ", sexe=" + sexe + ", objectif_nutrition=" + objectif_nutrition + ", bmi_sportif=" + bmi_sportif + ", besoin_sportif=" + besoin_sportif + ", besoin_proteine=" + besoin_proteine + ", besoin_carb=" + besoin_carb + ", besoin_calories=" + besoin_calories + '}';
    }

    public caracteristiquesportif(int id_sportif_id, int taille_sportif, int poid_sportif, int age_sportif, String sexe, String objectif_nutrition) {
        this.id_sportif_id = id_sportif_id;
        this.taille_sportif = taille_sportif;
        this.poid_sportif = poid_sportif;
        this.age_sportif = age_sportif;
        this.sexe = sexe;
        this.objectif_nutrition = objectif_nutrition;
    }

    public int getId() {
        return id;
    }

    public int getId_sportif_id() {
        return id_sportif_id;
    }

    public int getTaille_sportif() {
        return taille_sportif;
    }

    public int getPoid_sportif() {
        return poid_sportif;
    }

    public int getAge_sportif() {
        return age_sportif;
    }

    public String getSexe() {
        return sexe;
    }

    public String getObjectif_nutrition() {
        return objectif_nutrition;
    }

    public Float getBmi_sportif() {
        return bmi_sportif;
    }

    public Float getBesoin_sportif() {
        return besoin_sportif;
    }

    public Float getBesoin_proteine() {
        return besoin_proteine;
    }

    public Float getBesoin_carb() {
        return besoin_carb;
    }

    public Float getBesoin_calories() {
        return besoin_calories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_sportif_id(int id_sportif_id) {
        this.id_sportif_id = id_sportif_id;
    }

    public void setTaille_sportif(int taille_sportif) {
        this.taille_sportif = taille_sportif;
    }

    public void setPoid_sportif(int poid_sportif) {
        this.poid_sportif = poid_sportif;
    }

    public void setAge_sportif(int age_sportif) {
        this.age_sportif = age_sportif;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setObjectif_nutrition(String objectif_nutrition) {
        this.objectif_nutrition = objectif_nutrition;
    }

    public void setBmi_sportif(Float bmi_sportif) {
        this.bmi_sportif = bmi_sportif;
    }

    public void setBesoin_sportif(Float besoin_sportif) {
        this.besoin_sportif = besoin_sportif;
    }

    public void setBesoin_proteine(Float besoin_proteine) {
        this.besoin_proteine = besoin_proteine;
    }

    public void setBesoin_carb(Float besoin_carb) {
        this.besoin_carb = besoin_carb;
    }

    public void setBesoin_calories(Float besoin_calories) {
        this.besoin_calories = besoin_calories;
    }
    
    
}
