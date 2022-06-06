/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author infoevo
 */
public class Produit {

  private int id;
     private String categorie_prod;
     private String libelle ;
     private String marque ;
     private float prix	;
     private String description;
     private String image_prod;
     private String disponibilite;	
      private float note;
      private int quantite;	
/*      id_prod
     categorie_prod
     libelle 
      marque 
     prix	
     description_prod
     image_prod
     disponibilite	
       note
      quantite	

      */
    public Produit() {
    }

    public Produit(int id, String categorie_prod, String libelle, String marque, float prix, String description, String image_prod, String disponibilite, float note, int quantite) {
        this.id = id;
        this.categorie_prod = categorie_prod;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.image_prod = image_prod;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }

    public Produit(String categorie_prod, String libelle, String marque, float prix, String description, String image_prod, String disponibilite, float note, int quantite) {
        this.categorie_prod = categorie_prod;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.image_prod = image_prod;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }

    public Produit( String categorie_prod, String libelle, String marque, float prix, String description, String disponibilite, float note, int quantite) {
        
        this.categorie_prod = categorie_prod;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }

    public Produit(String categorie_prod) {
        this.categorie_prod = categorie_prod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id_prod) {
        this.id = id;
    }

    public String getCategorie_prod() {
        return categorie_prod;
    }

    public void setCategorie_prod(String categorie_prod) {
        this.categorie_prod = categorie_prod;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_prod() {
        return image_prod;
    }

    public void setImage_prod(String image_prod) {
        this.image_prod = image_prod;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_prod=" + id + ", categorie_prod=" + categorie_prod + ", libelle=" + libelle + ", marque=" + marque + ", prix=" + prix + ", description_prod=" + description + ", image_prod=" + image_prod + ", disponibilite=" + disponibilite + ", note=" + note + ", quantite=" + quantite + '}';
    }

    
}



