/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.services.ServicesProduits;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Category;
import com.mycompany.entities.Produit;

/**
 *
 * @author infoevo
 */
public class listProduitForm extends Form{

 public listProduitForm() {
        setTitle("List tasks");
        setLayout(BoxLayout.y());
        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServicesCategories.getInstance().getAllTasks().toString());
        

for (Produit s : ServicesProduits.getInstance().getAllTasks()) {
            
            System.out.println(s);
            Container cpt = TableLayout.encloseIn(6,
                    new Label(s.getDescription()),
                    new Label(s.getMarque())
                   
            //                new Label(valueOf(s.getDate())),
            //                new Label(s.gettCour())
 );
            
            add(cpt);
//Button btnSupprimer = new Button("Supprimer");
//btnSupprimer.ServicesCategories.getInstance().deleteCategory(s.getId()); //event lissner

//btnSupprimer.deleteCategory(s.getId());
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());


  }
 // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new StatPieForm().showBack());


}

}
