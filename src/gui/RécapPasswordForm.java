package gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entities.User;
import services.UserService;

public class RécapPasswordForm extends Form{

	public static User user;
	public static String codegenerated;
	
	public RécapPasswordForm() {
		super(new BorderLayout());
		
		  TextField code = new TextField("", "Code de confirmation", 20, TextField.ANY);
		  TextField newpassword = new TextField("", "Nouveau mot de passe", 20, TextField.ANY);

	        Button btnnewpassword = new Button("Récupérer");
	        btnnewpassword.addActionListener(l->{
	        	user.setPassword(newpassword.getText());
	        	System.out.println("from récap , email:" + user.getMailAdress());
	        	System.out.println("from récap , password:" + user.getPassword());

	        	UserService.getInstance().edituser(user);
	        	
	        });

	        add(BorderLayout.NORTH,btnnewpassword);
	        
	        
	        Container content = BoxLayout.encloseY(
	                new FloatingHint(code),new FloatingHint(newpassword));
	        content.setScrollableY(true);
	        add(BorderLayout.SOUTH, content);

	}

	
}
