/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Asus
 */
public class caracForm extends Form{
    
     Form current;
    public caracForm(Form previous) {
     getTitleArea().setUIID("Container");
        setUIID("background9");
         setLayout(BoxLayout.y());
         add(new Label("Choose an option"));
        Button btnAddCaracteristiquesportif = new Button("Add Caracteristiquesportif");
        Button btnListCaracteristiquesportif = new Button("List Caracteristiquesportif");
        
        btnAddCaracteristiquesportif.addActionListener(e-> {new addcarac( previous).show();});
        btnListCaracteristiquesportif.addActionListener(e-> {new Listcarac( previous).show();});
        addAll(btnAddCaracteristiquesportif,btnListCaracteristiquesportif);
    }
}