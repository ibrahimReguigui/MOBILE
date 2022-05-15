package com.mycompany.gui;

import java.util.Date;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycomany.services.UserService;
import com.mycompany.entities.User;

public class EditUserForm extends Form{
	
	Form current;
	Resources theme;;
	public EditUserForm(Form previous, User user) {
		
		current = this;
		setTitle("Modifier Utilisateur");
		getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
		
		
			TextField tfnom = new TextField("", "Nom:");
	        TextField tfprenom = new TextField("", "Prénom");
	        TextField tfadresse = new TextField("", "adresse");
	        TextField tfnumTel = new TextField("", "numTel");
	        TextField tfpassword = new TextField("", "password");
	        TextField tfmailAdress = new TextField("", "mailAdress");
	        TextField tfdateNaissance = new TextField("", "dateNaissance");
	        TextField tfwhoami = new TextField("", "whoami");
	        
	        tfnom.setText(user.getNom());
	        tfprenom.setText(user.getPrenom());
	        tfadresse.setText(user.getAdresse());
	        tfnumTel.setText(user.getNumTel());
	        tfpassword.setText(user.getPassword());
	        tfmailAdress.setText(user.getMailAdress());
	    //    tfdateNaissance.setText(user.getDateNaissance());
	        tfwhoami.setText(user.getWhoami());


        	System.out.println("user outside actio method:"+user);


	        Button btnValider = new Button("Modifier Utilisateur");

	        btnValider.addActionListener(new ActionListener<ActionEvent>() {
	        	
	        	
	        	
	            @Override
	            public void actionPerformed(ActionEvent evt) {
	            	
	            	
	                
	                if ((tfnom.getText().length() == 0) || (tfprenom.getText().length() == 0) ||(tfadresse.getText().length() == 0) ||(tfnumTel.getText().length() == 0) ||(tfmailAdress.getText().length() == 0) || (tfdateNaissance.getText().length() == 0) || (tfwhoami.getText().length() == 0)) {
	                    Dialog.show("Alert", "Veuillez saisir ces champs?", new Command("OK"));
	                } else {
	                    try {
	                        //Integer.parseInt(tfTaille.getText())
	                         
	                    	user.setNom(tfnom.getText());
	                    	user.setPrenom(tfprenom.getText());
	                    	user.setAdresse(tfadresse.getText());
	                    	user.setWhoami(tfwhoami.getText());
	                    	user.setNumTel(tfnumTel.getText());
	                    	user.setMailAdress(tfmailAdress.getText());
	                    	user.setPassword(tfpassword.getText());
	                    	
	                    	System.out.println("user inside actio method:"+user);
	                    

	 
	                        if (UserService.getInstance().edituser(user)) {
	                            Dialog.show("Success", "Connexion acceptée ! ", new Command("OK"));
	                            new ListUsersForm(current).show();
	                        } else {
	                            Dialog.show("ERROR", "erreur de serveur", new Command("OK"));
	                        }
	                    } catch (NumberFormatException e) {
	                        Dialog.show("ERROR", "Erreur !!", new Command("OK"));
	                    }
	                    Button btnAnnuler = new Button("Annuler");
	                    btnAnnuler.addActionListener(e -> {
                            new ListUsersForm(current).show();
	                    });

	                }

	            }
	        });
	 

	        addAll(tfnom,tfprenom,tfadresse,tfnumTel,tfpassword,tfmailAdress,tfdateNaissance,tfwhoami, btnValider);
	        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListUsersForm(current).show());

		
	}
	

}
