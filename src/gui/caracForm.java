/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
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
    public caracForm(Resources res) {
           current = this;
     getTitleArea().setUIID("Container");
        setUIID("background9");
         setLayout(BoxLayout.y());
         add(new Label("Choose an option"));
        Button btnAddCaracteristiquesportif = new Button("Add Caracteristiquesportif");
        Button btnListCaracteristiquesportif = new Button("List Caracteristiquesportif");
        
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        btnAddCaracteristiquesportif.addActionListener(e-> {new addcarac( current).show();});
        btnListCaracteristiquesportif.addActionListener(e-> {new Listcarac( current).show();});
        addAll(btnAddCaracteristiquesportif,btnListCaracteristiquesportif);
    }
}
