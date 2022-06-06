/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author rocky
 */
public class Exercice {

    private int id;
    private String nom_exercice;
    private int nbr_serie;
    private int nbr_repetition;
    private String description_exercice;
    private String categorie_exercice;

    public Exercice(String string, String string0, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public String toString() {
        return "Exercice{" + "id=" + id + ", nom_exercice=" + nom_exercice + ", nbr_serie=" + nbr_serie + ", nbr_repetition=" + nbr_repetition + ", description_exercice=" + description_exercice + ", categorie_exercice=" + categorie_exercice + '}';
    }

    public Exercice() {
    }

    public Exercice(int i) {

    }

    public int getIdExercice() {
        return id;
    }

    public void setIdExercice(int id) {
        this.id = id;
    }

    public String getNomExercice() {
        return nom_exercice;
    }

    public void setNomExercice(String nom_exercice) {
        this.nom_exercice = nom_exercice;
    }

    public int getNbrSerie() {
        return nbr_serie;
    }

    public void setNbrSerie(int nbr_serie) {
        this.nbr_serie = nbr_serie;
    }

    public int getNbrRepetition() {
        return nbr_repetition;
    }

    public void setNbrRepetition(int nbr_repetition) {
        this.nbr_repetition = nbr_repetition;
    }

    public String getDescriptionExercice() {
        return description_exercice;
    }

    public void setDescriptionExercice(String description_exercice) {
        this.description_exercice = description_exercice;
    }

    public String getCategorieExercice() {
        return categorie_exercice;
    }

    public void setCategorieExercice(String categorie_exercice) {
        this.categorie_exercice = categorie_exercice;
    }

    public Exercice(String nom_exercice, int nbr_serie, int nbr_repetition, String description_exercice, String categorie_exercice) {
        this.nom_exercice = nom_exercice;
        this.nbr_serie = nbr_serie;
        this.nbr_repetition = nbr_repetition;
        this.description_exercice = description_exercice;
        this.categorie_exercice = categorie_exercice;
    }

    public int getId() {
        return id;
    }

    public String getNom_exercice() {
        return nom_exercice;
    }

    public int getNbr_serie() {
        return nbr_serie;
    }

    public int getNbr_repetition() {
        return nbr_repetition;
    }

    public String getDescription_exercice() {
        return description_exercice;
    }

    public String getCategorie_exercice() {
        return categorie_exercice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_exercice(String nom_exercice) {
        this.nom_exercice = nom_exercice;
    }

    public void setNbr_serie(int nbr_serie) {
        this.nbr_serie = nbr_serie;
    }

    public void setNbr_repetition(int nbr_repetition) {
        this.nbr_repetition = nbr_repetition;
    }

    public void setDescription_exercice(String description_exercice) {
        this.description_exercice = description_exercice;
    }

    public void setCategorie_exercice(String categorie_exercice) {
        this.categorie_exercice = categorie_exercice;
    }

    public Exercice(int id, String nom_exercice, int nbr_serie, int nbr_repetition, String description_exercice, String categorie_exercice) {
        this.id = id;
        this.nom_exercice = nom_exercice;
        this.nbr_serie = nbr_serie;
        this.nbr_repetition = nbr_repetition;
        this.description_exercice = description_exercice;
        this.categorie_exercice = categorie_exercice;
    }

}
