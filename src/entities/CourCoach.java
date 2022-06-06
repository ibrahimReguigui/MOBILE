/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package entities;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Asus
 */
public class CourCoach {
    private int id,id_participant_id,id_coach_id;
    private String etat,nom_coach,nom_participant;
    private Date date;
    private Time time;

    public CourCoach() {
    }

    public void setNom_participant(String nom_participant) {
        this.nom_participant = nom_participant;
    }

    public CourCoach(int id, int id_coach_id, Date date, Time time) {
        this.id = id;
        this.id_coach_id = id_coach_id;
        this.date = date;
        this.time = time;
    }

    public CourCoach(int id_participant_id, int id_coach_id, String etat, String nom_coach, Date date, Time time) {
        this.id_participant_id = id_participant_id;
        this.id_coach_id = id_coach_id;
        this.etat = etat;
        this.nom_coach = nom_coach;
        this.date = date;
        this.time = time;
    }

    public CourCoach(int id, int id_participant_id, int id_coach_id, String etat, String nom_coach, Date date, Time time) {
        this.id = id;
        this.id_participant_id = id_participant_id;
        this.id_coach_id = id_coach_id;
        this.etat = etat;
        this.nom_coach = nom_coach;
        this.date = date;
        this.time = time;
    }

    public CourCoach(int id_participant_id, int id_coach_id, String etat, Date date, Time time) {
        this.id_participant_id = id_participant_id;
        this.id_coach_id = id_coach_id;
        this.etat = etat;
        this.date = date;
        this.time = time;
    }

    public CourCoach(int id, int id_participant_id, int id_coach_id, String etat, Date date, Time time) {
        this.id = id;
        this.id_participant_id = id_participant_id;
        this.id_coach_id = id_coach_id;
        this.etat = etat;
        this.date = date;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_participant_id(int id_participant_id) {
        this.id_participant_id = id_participant_id;
    }

    public void setId_coach_id(int id_coach_id) {
        this.id_coach_id = id_coach_id;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public String getNom_participant() {
        return nom_participant;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getId_participant_id() {
        return id_participant_id;
    }

    public int getId_coach_id() {
        return id_coach_id;
    }

    public String getEtat() {
        return etat;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "CourCoach{" + "id=" + id + ", id_participant_id=" + id_participant_id + ", id_coach_id=" + id_coach_id + ", etat=" + etat + ", date=" + date + ", time=" + time + '}';
    }
    
}
