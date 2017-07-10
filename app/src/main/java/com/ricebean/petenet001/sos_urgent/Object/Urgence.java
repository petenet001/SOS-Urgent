package com.ricebean.petenet001.sos_urgent.Object;

/**
 * Created by Petenet001 on 31/03/2017.
 */

public class Urgence {
    private String urgence_title, urgence_description, faire, nePasfaire;
    private int urgence_image;

    public Urgence(String urgence_title, String urgence_description, String faire, String nePasfaire, int urgence_image) {
        this.urgence_title = urgence_title;
        this.urgence_description = urgence_description;
        this.faire = faire;
        this.nePasfaire = nePasfaire;
        this.urgence_image = urgence_image;
    }

    public Urgence() {

    }

    public String getNePasfaire() {
        return nePasfaire;
    }

    public void setNePasfaire(String nePasfaire) {
        this.nePasfaire = nePasfaire;
    }

    public String getFaire() {
        return faire;
    }

    public void setFaire(String faire) {
        this.faire = faire;
    }

    public String getUrgence_title() {
        return urgence_title;
    }

    public void setUrgence_title(String urgence_title) {
        this.urgence_title = urgence_title;
    }

    public String getUrgence_description() {
        return urgence_description;
    }

    public void setUrgence_description(String urgence_description) {
        this.urgence_description = urgence_description;
    }

    public int getUrgence_image() {
        return urgence_image;
    }

    public void setUrgence_image(int urgence_image) {
        this.urgence_image = urgence_image;
    }
}
