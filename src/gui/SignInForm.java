package gui;

import com.codename1.components.FloatingHint; 
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import services.UserService;

public class SignInForm extends BaseForm{
	
	public static Form current;

	
	 public SignInForm(Resources res) {
		 
		 
	        super(new BorderLayout());
			 current = this;

	        
	        setTitle("connectez-vous pour continuer !");
	        
	        if(!Display.getInstance().isTablet()) {
	            BorderLayout bl = (BorderLayout)getLayout();
	            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
	            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
	        }
	        getTitleArea().setUIID("Container");
	        setUIID("SignIn");
	        
	        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
	        
	     
	        TextField username = new TextField("", "Username", 20, TextField.ANY);
	        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
	        username.setSingleLineTextArea(false);
	        password.setSingleLineTextArea(false);
	        Button signIn = new Button("Sign In");
	        Button signUp = new Button("Vous n'avez aucune compte?");
	        
	        //mp oublié
	        Button  mp = new Button("oublier mot de passe?","CenterLabel");
	        
	        
	        signUp.addActionListener(e -> new signUpForm(res).show());
	        signUp.setUIID("Link");
	       // Label doneHaveAnAccount = new Label("Vous n'avez aucune compte?");
	        
	        
	        
	        signUp.addActionListener(l->{
	        	new signUpForm(res).show();
	        });
	        
	        
	        Container content = BoxLayout.encloseY(
	                new FloatingHint(username),
	                createLineSeparator(),
	                new FloatingHint(password),
	                createLineSeparator(),
	                signIn,
	                FlowLayout.encloseCenter(signUp),mp
	        );
	        content.setScrollableY(true);
	        add(BorderLayout.SOUTH, content);
	        signIn.requestFocus();
	        
	        signIn.addActionListener(e -> 
	        {
	               UserService.getInstance().signin(username, password, res);

	           
	        });
	        
	        
	        
	        //Mp oublie event
	        
	        mp.addActionListener((e) -> {
	           
	            new ActivateForm(res).show();
	            
	            
	        });
	        
	    }
}
