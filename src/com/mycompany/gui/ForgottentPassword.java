package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycomany.services.UserService;

public class ForgottentPassword extends Form{
	
	public ForgottentPassword() {
		
		super(new BorderLayout());
		
		  TextField sendmail = new TextField("", "sendmail", 20, TextField.ANY);
	        
	        Button btnsendmail = new Button("send");
	        btnsendmail.addActionListener(l->{
	        	UserService us = new UserService();
	        	us.sendMail("kefiskander99@gmail.com");
	        });

	        add(BorderLayout.NORTH,btnsendmail);
	        
	        
	        Container content = BoxLayout.encloseY(
	                new FloatingHint(sendmail));
	        content.setScrollableY(true);
	        add(BorderLayout.SOUTH, content);

	}

}
