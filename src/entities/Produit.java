/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI
 */
public class Produit {
    private int id;
    private int category_id;
    
    private String categorie;
     private String libelle ;
     private String marque ;
     private float prix	;
     private String description;
     private String image;
     private String disponibilite;	
      private float note;
      private int quantite;	
/*      id
     categorie
     libelle 
      marque 
     prix	
     description
     image
     disponibilite	
       note
      quantite	

      */
    public Produit() {
    }

    public Produit(int id, int category_id, String categorie, String libelle, String marque, float prix, String description, String image, String disponibilite, float note, int quantite) {
        this.id = id;
        this.category_id = category_id;
        this.categorie = categorie;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }

    public Produit(int category_id, String categorie, String libelle, String marque, float prix, String description, String image, String disponibilite, float note, int quantite) {
        this.category_id = category_id;
        this.categorie = categorie;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }

    public Produit(String categorie, String libelle, String marque, float prix, String description, String image, String disponibilite, float note, int quantite) {
        this.categorie = categorie;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }
    
    //Constr affichage

    public Produit(String categorie, String libelle, String marque, float prix, String description, String disponibilite, float note, int quantite) {
        this.categorie = categorie;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.disponibilite = disponibilite;
        this.note = note;
        this.quantite = quantite;
    }

    public Produit(int id) {
        this.id = id;
    }
    

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }





    public Produit(String categorie) {
        this.categorie = categorie;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getcategorie() {
        return categorie;
    }

    public void setcategorie(String categorie) {
        this.categorie = categorie;
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

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
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
        return "Produit{" + "id=" + id + ", categorie=" + categorie + ", libelle=" + libelle + ", marque=" + marque + ", prix=" + prix + ", description=" + description + ", image=" + image + ", disponibilite=" + disponibilite + ", note=" + note + ", quantite=" + quantite + '}';
    }

    
}
