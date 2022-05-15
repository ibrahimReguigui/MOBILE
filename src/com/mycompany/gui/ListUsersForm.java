package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;  
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.services.UserService;
import com.mycompany.entities.User;

public class ListUsersForm extends Form{
	
	Form current;
    private Label lnom, lemail, lnum, lage;
    private Form hi, f2;

	public ListUsersForm(Form previous) {
		

		super(BoxLayout.y());
		
		

		current = this;
		
		Button btnajout = new Button("Nouveau");
		btnajout.addActionListener(l->{
			new AddUserForm(current).show();
		});

		
		setTitle("Liste des utilisateurs");
		
	
		getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
		
     
           
           java.util.ArrayList<User> list = UserService.getInstance().getAllUsers();
           for (User c : list) {
               MultiButton fourLinesIcon = new MultiButton("nom");
               fourLinesIcon.setTextLine1("prenom : " + c.getPrenom()+ "\n");
               fourLinesIcon.setTextLine2(" Adresse : " + c.getAdresse()+ "\n");
              
               
//               //modif button
               Button btnModifCarac = new Button("Modifier");
               btnModifCarac.setUIID("LoginButton");

               //supprimer button
              // Button btnModifPlat = new Button("Modifier");
              // btnModifPlat.setUIID("LoginButton");
               Button btnSup = new Button("Supprimer");
               btnSup.setUIID("LoginButton");
               
               
               Button btnsignal = new Button("Signaler");
               btnSup.setUIID("LoginButton");
               
               btnsignal.addActionListener(l->{
            	   Dialog dig = new Dialog("Signal");

                   if (dig.show("Signal", "Vous voulez signaler cet utilisateur ?", "Annuler", "Oui")) {
                	   UserService.getInstance().signaluser(c.getId());

                       dig.dispose();
                   } else {
                       dig.dispose();
                   }
               });
               
               
               Button btnblock = new Button("Bloquer");
               btnSup.setUIID("LoginButton");
               
               btnblock.addActionListener(l->{
            	   Dialog dig = new Dialog("Bloquer");

                   if (dig.show("Bloquer", "Vous voulez bloquer cet utilisateur ?", "Annuler", "Oui")) {
                	  

                       dig.dispose();
                   } else {
                       dig.dispose();
                   }
                   c.setBlocRaison("h");
            	   
            	   System.out.println("from block form:"+c);
            	   UserService.getInstance().edituser(c);
               });
               
               
               
               
               System.out.println("from liiiiist c="+c);
               btnModifCarac.addActionListener(l->{
            	   new EditUserForm(current, c).show();
               });

               //click delete icon
               btnSup.addPointerPressedListener(l -> {

                   Dialog dig = new Dialog("Suppression");

                   if (dig.show("Suppression", "Vous voulez supprimer cet utilisateur ?", "Annuler", "Oui")) {
                	   
                       dig.dispose();
                       if (UserService.getInstance().deleteUser(c.getId())) {
                           System.out.println("supprimé");
                       }
                   } else {
                       dig.dispose();
                   }
                  
                   current.show();


               });
               addAll(btnblock,btnsignal,btnModifCarac, fourLinesIcon,btnSup);
           }
           
           
	}
	

}
