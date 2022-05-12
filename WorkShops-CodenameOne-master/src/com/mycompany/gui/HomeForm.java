/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;


import com.codename1.ui.Form;


import com.codename1.ui.Button;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

import com.mycompany.gui.StatPieForm;



/**
 *
 * @author infoevo
 */
public class HomeForm extends Form {

   Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home Gerant");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Category");
        Button btnListTasks = new Button("List Category");

        Button btnListProduit = new Button("List Produit");
        
        Button btnStat = new Button("Stat");
       
        btnAddTask.addActionListener(e-> new addCategorieForm().show());
        btnListTasks.addActionListener(e-> new listCategorieForm().show());

        btnListProduit.addActionListener(e-> new listProduitForm().show());

       // btnStat.addActionListener(e-> new StatPieForm(res).show());
        addAll(btnAddTask,btnListTasks,btnListProduit);
        
        
    }
}


