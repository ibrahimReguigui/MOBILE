package gui;

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
import entities.User;
import services.UserService;


public class AddUserForm extends Form{

	
	Form current;
	Resources theme;;
	public AddUserForm(Form previous) {
		
		current = this;
		setTitle("Ajouter Utilisateur");
		getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
		
		
		
		
			TextField tfnom = new TextField("", "Nom:");
	        TextField tfprenom = new TextField("", "Prénom");
	        TextField tfadresse = new TextField("", "adresse");
	        TextField tfnumTel = new TextField("", "numTel");
	        TextField tfpassword = new TextField("", "password");
	        TextField tfmailAdress = new TextField("", "mailAdress");
	        TextField tfdateNaissance = new TextField("", "dateNaissance");
	        TextField tfwhoami = new TextField("", "whoami");

	        

	        Button btnValider = new Button("Ajouter Utilisateur");

	        btnValider.addActionListener(new ActionListener<ActionEvent>() {
	            @Override
	            public void actionPerformed(ActionEvent evt) {
	                
	                if ((tfnom.getText().length() == 0) || (tfprenom.getText().length() == 0) ||(tfadresse.getText().length() == 0) ||(tfnumTel.getText().length() == 0) ||(tfmailAdress.getText().length() == 0) || (tfdateNaissance.getText().length() == 0) || (tfwhoami.getText().length() == 0)) {
	                    Dialog.show("Alert", "Veuillez saisir ces champs?", new Command("OK"));
	                } else {
	                    try {
	                        //Integer.parseInt(tfTaille.getText())
	                         
	                    	
	                    

	                    	User user = new User(tfnom.getText(), tfprenom.getText(), tfadresse.getText(), tfnumTel.getText(), tfpassword.getText(), tfmailAdress.getText(), null, "Administrateur",
	                    			"", new Date(), "", false, 0);
	 
	                        if (UserService.getInstance().addUser(user)) {
	                            Dialog.show("Success", "Connexion acceptée ! ", new Command("OK"));
	                        } else {
	                            Dialog.show("ERROR", "erreur de serveur", new Command("OK"));
	                        }
	                    } catch (NumberFormatException e) {
	                        Dialog.show("ERROR", "Erreur !!", new Command("OK"));
	                    }

	                }

	            }
	        });
	 

	        addAll(tfnom,tfprenom,tfadresse,tfnumTel,tfpassword,tfmailAdress,tfdateNaissance,tfwhoami, btnValider);
	        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListUsersForm(current).show());

		
	}
	
}
