package gui;

import java.util.Random;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import services.UserService;

public class ForgottentPassword extends Form{
	
	public ForgottentPassword() {
		
		super(new BorderLayout());
		
		  TextField sendmail = new TextField("", "sendmail", 20, TextField.ANY);
	        
	        Button btnsendmail = new Button("send");
	        btnsendmail.addActionListener(l->{
	        	UserService us = new UserService();
	        	us.sendMail(sendmail.getText());
	        //	new RécapPasswordForm().show();
	        });

	        add(BorderLayout.NORTH,btnsendmail);
	        
	        
	        Container content = BoxLayout.encloseY(
	                new FloatingHint(sendmail));
	        content.setScrollableY(true);
	        add(BorderLayout.SOUTH, content);

	}
	
	
	
	
	

	public static String generateCodeToResetPwd() {
		// create a string of uppercase and lowercase characters and numbers
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";

		// combine all strings
		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 10;

		for(int i = 0; i < length; i++) {

		  // generate random index number
		  int index = random.nextInt(alphaNumeric.length());

		  // get character specified by index
		  // from the string
		  char randomChar = alphaNumeric.charAt(index);

		  // append the character to string builder
		  sb.append(randomChar);
		}

		String randomString = sb.toString();
		return randomString;
		}




}
