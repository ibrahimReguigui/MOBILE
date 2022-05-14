/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/*

id_evenement 

nom 

nbmax_participants 

date_evenement 

id_sponsor 

description 

image_event
/**
 *
 * @author farah
 */
public class Event {
    private int idevent;
    private int nbrplaces;
    private String nomevent;
    private String datedebut;
    private String datefin;
    private String location;
    private String img;

    public Event(String nomevent) {
        this.nomevent = nomevent;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public int getNbrplaces() {
        return nbrplaces;
    }

    public void setNbrplaces(int nbrplaces) {
        this.nbrplaces = nbrplaces;
    }

    public String getNomevent() {
        return nomevent;
    }

    public void setNomevent(String nomevent) {
        this.nomevent = nomevent;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getNdatefin() {
        return datefin;
    }

    public void setdatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Event{" + "idevent=" + idevent + ", nbrplaces=" + nbrplaces + ", nomevent=" + nomevent + ", datedebut=" + datedebut + ", datefin=" + datefin + ", location=" + location + ", img=" + img + '}';
    }

    public Event(int nbrplaces, String nomevent, String datedebut, String datefin, String location, String img) {
        this.nbrplaces = nbrplaces;
        this.nomevent = nomevent;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.location = location;
        this.img = img;
    }

    public Event() {
    }
    

   

   
}
