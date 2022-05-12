package com.mycompany.gui;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.services.UserService;

public class ActivateForm extends BaseForm {

    TextField email;
    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("oublier.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );
        
        
        
         email = new TextField("","saisir votre email",20,TextField.ANY);
        email.setSingleLineTextArea(false);
        
        Button valider = new Button("Valider");
        Label haveAnAcount = new Label("Retour se connecter?");
        Button signIn = new Button("Renouveler votre mot de passe");
        signIn.addActionListener( e-> previous.showBack());//yarja3 lel window ely9ablha
        signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
        
                new FloatingHint(email),
                createLineSeparator(),
                valider,
                FlowLayout.encloseCenter(haveAnAcount),
                signIn
        );
        
        content.setScrollableY(true);
        
        add(BorderLayout.CENTER,content);
        
        valider.requestFocus();
        
        valider.addActionListener(e -> {
            
            InfiniteProgress ip = new InfiniteProgress();
            
            final Dialog ipDialog =  ip.showInfiniteBlocking();
            
            //houni bch nzido API SEND MAIL autrement bch n3ayto lel function ta3o mais 9bal njibo image oublier.png
            
            sendMail(res);
            ipDialog.dispose();
            Dialog.show("Mot de passe","Nous avons envoyé le mot de passe a votre e-mail. Veuillez vérifier votre boite de réception",new Command("OK"));
            new SignInForm(res).show();
            refreshTheme();
            
        });
        
        
        
    }
    
    //sendMail
    
    public void sendMail(Resources res) {

    	String to = email.getText(); 
        String myaccountEmail="skander.kefi@esprit.tn" ; 
        String password ="213JMT2132" ; 
         Properties properties = new Properties();    
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
      
        Session session = Session.getInstance(properties, new Authenticator() {
        
        
    protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(myaccountEmail, password);
          
       }

      });
        
          try {
        	  
        	  
              
        	
        	  
        	  
           Message message = new MimeMessage(session) ;
                      
           message.setFrom(new InternetAddress(myaccountEmail));
          
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(to) );
           
           message.setSubject("Récupérer votre mot de passe");
           
           //message.setText("Votre cours est ajoutée avec succ  ");
           
           String htmlCode = "<h2> Voici votre code confirmation </h2>" ; 
           message.setContent(htmlCode,"text/html");
           
           
           
           
           Transport.send(message);
              System.out.println("succes");
              
             
              
       } catch (Exception ex) {
    	   System.out.println("teb3athech");
    	   ex.printStackTrace();
    	   // Logger.getLogger(JavaMainUtil.class.getName()).log(Level.SEVERE, null, ex);
       
       }
    	}
    }