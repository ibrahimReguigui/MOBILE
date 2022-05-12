package com.mycompany.entities;

import java.util.Date;

public class User {

	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String numTel;
	private String password;
	private String mailAdress;
	private Date dateNaissance;
	private String whoami;
	private String blocRaison;
	private Date unbloc;
	private String image;
	private boolean isconnected;
	private Integer nbsignal;
	public User(int id, String nom, String prenom, String adresse, String numTel, String password,
			String mailAdress, Date dateNaissance, String whoami, String blocRaison, Date unbloc, String image,
			boolean isconnected, Integer nbsignal) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTel = numTel;
		this.password = password;
		this.mailAdress = mailAdress;
		this.dateNaissance = dateNaissance;
		this.whoami = whoami;
		this.blocRaison = blocRaison;
		this.unbloc = unbloc;
		this.image = image;
		this.isconnected = isconnected;
		this.nbsignal = nbsignal;
	}
	
	public User() {
		super();
		
	}

	public User(String nom, String prenom, String adresse, String numTel, String password, String mailAdress,
			Date dateNaissance, String whoami, String blocRaison, Date unbloc, String image, boolean isconnected,
			Integer nbsignal) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTel = numTel;
		this.password = password;
		this.mailAdress = mailAdress;
		this.dateNaissance = dateNaissance;
		this.whoami = whoami;
		this.blocRaison = blocRaison;
		this.unbloc = unbloc;
		this.image = image;
		this.isconnected = isconnected;
		this.nbsignal = nbsignal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailAdress() {
		return mailAdress;
	}

	public void setMailAdress(String mailAdress) {
		this.mailAdress = mailAdress;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getWhoami() {
		return whoami;
	}

	public void setWhoami(String whoami) {
		this.whoami = whoami;
	}

	public String getBlocRaison() {
		return blocRaison;
	}

	public void setBlocRaison(String blocRaison) {
		this.blocRaison = blocRaison;
	}

	public Date getUnbloc() {
		return unbloc;
	}

	public void setUnbloc(Date unbloc) {
		this.unbloc = unbloc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isIsconnected() {
		return isconnected;
	}

	public void setIsconnected(boolean isconnected) {
		this.isconnected = isconnected;
	}

	public Integer getNbsignal() {
		return nbsignal;
	}

	public void setNbsignal(Integer nbsignal) {
		this.nbsignal = nbsignal;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", numTel=" + numTel
				+ ", password=" + password + ", mailAdress=" + mailAdress + ", dateNaissance=" + dateNaissance
				+ ", whoami=" + whoami + ", blocRaison=" + blocRaison + ", unbloc=" + unbloc + ", image=" + image
				+ ", isconnected=" + isconnected + ", nbsignal=" + nbsignal + "]";
	}
	
	
	
	
	
}
