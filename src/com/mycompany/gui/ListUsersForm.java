package com.mycompany.gui;

import com.codename1.components.MultiButton; 
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycomany.services.UserService;
import com.mycompany.entities.User;

public class ListUsersForm extends Form{
	
	Form current;
	public ListUsersForm(Form previous) {
        super(BoxLayout.y());

		current = this;

		
		setTitle("Liste des utilisateurs");
		
	//	SpanLabel sp = new SpanLabel();
	//	sp.setText(UserService.getInstance().getAllUsers().toString());
		getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
		System.out.println("--------------"+UserService.getInstance().getAllUsers().toString());
		
		
//		   Button btnSup = new Button("Supprimer");
//           btnSup.setUIID("LoginButton");
//
//           //click delete icon
//           btnSup.addPointerPressedListener(l -> {
//
//               Dialog dig = new Dialog("Suppression");
//
//               if (dig.show("Suppression", "Vous voulez supprimer ce reclamation ?", "Annuler", "Oui")) {
//                   dig.dispose();
//               } else {
//                   dig.dispose();
//               }
//               if (UserService.getInstance().deleteUser(5)) {
//                   System.out.println("supprimé");
//               }
//               current.show();
//
//
//           });
		
           
           
           java.util.ArrayList<User> list = UserService.getInstance().getAllUsers();
           for (User c : list) {
               MultiButton fourLinesIcon = new MultiButton("nom");
               fourLinesIcon.setTextLine1("prenom : " + c.getPrenom()+ "\n");
               fourLinesIcon.setTextLine2(" Adresse : " + c.getAdresse()+ "\n");
              
               
               //modif button
               Button btnModifCarac = new Button("Modifier");
               btnModifCarac.setUIID("LoginButton");

               //supprimer button
               Button btnModifPlat = new Button("Modifier");
               btnModifPlat.setUIID("LoginButton");
               Button btnSup = new Button("Supprimer");
               btnSup.setUIID("LoginButton");

               //click delete icon
               btnSup.addPointerPressedListener(l -> {

                   Dialog dig = new Dialog("Suppression");

                   if (dig.show("Suppression", "Vous voulez supprimer cet utilisateur ?", "Annuler", "Oui")) {
                       dig.dispose();
                   } else {
                       dig.dispose();
                   }
                   if (UserService.getInstance().deleteUser(c.getId())) {
                       System.out.println("supprimé");
                   }
                   current.show();


               });
               addAll(btnModifCarac, fourLinesIcon,btnSup);
           }
           
           
           
           
           
	
	}
	
	

}
