package com.mycomany.services;
 
import java.util.ArrayList;    
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mycompany.entities.User;
import com.mycompany.gui.HomeForm;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.utils.Statics;
import java.util.Properties;

public class UserService {
	
	public ArrayList<User> users;

	public boolean resultOK;
	
	public static UserService instance =null;
	
	
	private ConnectionRequest req;
	
	
	public static UserService getInstance() {
		if (instance == null) 
			instance = new UserService();
		return instance;
	}
	
	
	public UserService() {
		req = new ConnectionRequest();
	}
	
	
	public void ajoutUser(User user) {
		
		
		
		
	}
	
	
	public ArrayList<User> parseUsers(String jsonText){
		
				try {
					users = new ArrayList<>();
					JSONParser j = new JSONParser();
					Map<String,Object> mapusers = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
					System.out.println(mapusers+"------------------------------map users");
					List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapusers.get("root");
					System.out.println("-------------------listofmaps"+listOfMaps);
					for(Map<String,Object> obj : listOfMaps) {
						User user  ;  
						float id = Float.parseFloat(obj.get("id").toString());
						String nom = obj.get("nom").toString();
						String prenom = obj.get("prenom").toString();
						String adresse = obj.get("adresse").toString();
						String numTel = obj.get("numTel").toString();
						String password = obj.get("password").toString();
						String mailAdress = obj.get("mailAdress").toString();
						//Date dateNaissance = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj.get("dateNaissance").toString()); 
						String whoami = obj.get("whoami").toString();
					//	String blocRaison = obj.get("blocRaison").toString();
					//	Date unbloc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj.get("unbloc").toString()); 
						String image = obj.get("image").toString();
					//	Boolean isconnected = Boolean.valueOf(obj.get("isconnected").toString());
					//	String nbsignal = obj.get("nbsignal").toString();
						user = new User((int)id, nom, prenom, adresse, numTel, password, mailAdress, null, whoami, null, null, image, false,null);
						users.add(user);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
		return users;
	}
	
	
	
	
	
	public ArrayList<User> getAllUsers(){
		
		req.setUrl(Statics.BASE_URL+"/user/alljson");
		req.setPost(false);
		req.addResponseListener(new ActionListener<NetworkEvent>() {

			@Override
			public void actionPerformed(NetworkEvent evt) {
				users = parseUsers(new String(req.getResponseData()));
				req.removeResponseListener(this);
				
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(req);

		return users;
	}
	
	
	
	
	
	
	
	
	public User detailUser(int id, User user) {
		
		String url = Statics.BASE_URL+"/user/"+id+"/edit";
		req.setUrl(url);
		String str = new String(req.getResponseData());
		req.addResponseListener((evt)->{
			JSONParser jsonp = new JSONParser();
			try {
				Map<String,Object> mapusers = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
				Float idd = Float.parseFloat(mapusers.get("id").toString()) ;
				user.setId(id);
				user.setNom(mapusers.get("nom").toString());
				user.setPrenom(mapusers.get("prenom").toString());
				user.setAdresse(mapusers.get("adresse").toString());
				

				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			System.out.println("date===="+str);

			
		});
		NetworkManager.getInstance().addToQueueAndWait(req);

		
		return user;
	}
	
	
	
	
	  public boolean addUser(User p) {
	        System.out.println(p.toString());
	        System.out.println("********");
	        String url = Statics.BASE_URL + "/user/new";

	        req.setUrl(url);

	        req.addArgument("nom", p.getNom()+ "");
	        req.addArgument("prenom", p.getPrenom() + "");
	        req.addArgument("adresse", p.getAdresse() + "");
	        req.addArgument("numTel", p.getNumTel()+"");
	        req.addArgument("password", p.getPassword()+"");
	        req.addArgument("mailAdress", p.getMailAdress()+"");
	        req.addArgument("dateNaissance", p.getDateNaissance().toString());
	        req.addArgument("whoami", p.getWhoami());
	        req.addArgument("blocRaison", p.getBlocRaison() );
	        req.addArgument("unbloc", p.getUnbloc().toString()) ;
	        req.addArgument("image", p.getImage() );
	       // req.addArgument("isconnected", p.getis );
	        req.addArgument("nbsignal", p.getNbsignal().toString() );



	        req.addResponseListener(new ActionListener<NetworkEvent>() {
	            @Override
	            public void actionPerformed(NetworkEvent evt) {
	                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
	                req.removeResponseListener(this);
	            }
	        });
	        NetworkManager.getInstance().addToQueueAndWait(req);
	        return resultOK;
	    }
	
	
	
	  
	  
	  
	  public boolean deleteUser(int id) {
	        String url = Statics.BASE_URL + "/user/deletejson/"+id  ;

	        req.setUrl(url);

	        req.addResponseListener(new ActionListener<NetworkEvent>() {
	            @Override
	            public void actionPerformed(NetworkEvent evt) {

	                req.removeResponseCodeListener(this);
	            }
	        });

	        NetworkManager.getInstance().addToQueueAndWait(req);
	        return resultOK;
	    }
	  
	  
	  
	  
	  
	  
	  
	  
	  //SignIn
	    
	    public void signin(TextField username,TextField password, Resources rs ) {
			users = new ArrayList<>();

	    	
	        String url = Statics.BASE_URL+"/user/signin?mailAdress="+username.getText()+"&password="+password.getText();
	        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
	        req.setUrl(url);
	        
	        req.addResponseListener((e) ->{
	            
	            JSONParser j = new JSONParser();
	            
	            String json = new String(req.getResponseData()) + "";
	            
	            
	            try {
	            
	            if(json.equals("failed")) {
	                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);

	            	         
	            }
	            else {

	            	 System.out.println("data =="+json);
		                
		                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
		                if(user.size() >0 ) // l9a user
		                	System.out.println("usserrrr "+user);
		    		
				               // new ListReclamationForm(rs).show();//yemchi lel list reclamation
			                    new HomeForm().show();
			                    
			                  
//	                //Session 
//	                float id = Float.parseFloat(user.get("id").toString());
//	                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
//	                
//	                SessionManager.setPassowrd(user.get("password").toString());
//	                SessionManager.setUserName(user.get("username").toString());
//	                SessionManager.setEmail(user.get("email").toString());
//	                
//	                //photo 
//	                
//	                if(user.get("photo") != null)
//	                    SessionManager.setPhoto(user.get("photo").toString());
//	                
//	                
	              
	            }
	            }catch(Exception ex) {
	                ex.printStackTrace();
	            }
	            
	            
	            
	        });
	    
	         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
	        NetworkManager.getInstance().addToQueueAndWait(req);
	    }
	    
	    
	    
	    
	    
	    
	    
	    //Signup
	    public void signup(TextField name,TextField prenom,TextField adresse,TextField numTel,
	    		TextField password,TextField mailAdress,TextField whoami, Resources res ) {
	        
	     
	        
	        String url = Statics.BASE_URL+"/user/signup?nom="+name.getText().toString()+"&prenom="+prenom.getText().toString()+
	                "&adresse="+adresse.getText().toString()+"&numTel="+numTel.getText().toString()+"&mailAdress="+mailAdress.getText().toString()
	                +"&whoami="+whoami.getText().toString();
	        
	        req.setUrl(url);
	       
	        //Control saisi
	        if(name.getText().equals(" ") && password.getText().equals(" ") && mailAdress.getText().equals(" ")) {
	            
	            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
	            
	        }
	        
	        //hethi wa9t tsir execution ta3 url 
	        req.addResponseListener((e)-> {
	         
	            //njib data ly7atithom fi form 
	            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
	            String responseData = new String(data);//ba3dika na5o content 
	            
	            System.out.println("data ===>"+responseData);
	        }
	        );
	        
	        
	        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
	        NetworkManager.getInstance().addToQueueAndWait(req);
	        
	            
	        
	    }
	    
	    
	    
	    
	    
	    
	    

	  //heki 5dmtha taw nhabtha ala description
//	    public String getPasswordByEmail(String email, Resources rs ) {
//	        
//	        
//	        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
//	        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
//	        req.setUrl(url);
//	        
//	        req.addResponseListener((e) ->{
//	            
//	            JSONParser j = new JSONParser();
//	            
//	             json = new String(req.getResponseData()) + "";
//	            
//	            
//	            try {
//	            
//	          
//	                System.out.println("data =="+json);
//	                
//	                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
//	                
//	                
//	            
//	            
//	            }catch(Exception ex) {
//	                ex.printStackTrace();
//	            }
//	            
//	            
//	            
//	        });
//	    
//	         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
//	        NetworkManager.getInstance().addToQueueAndWait(req);
//	    return json;
//	    }
//	  
//	  
//	  
	  
	  
	  
	  

public void sendMail(String mailadress) {
	
	
	String to = mailadress; 
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

   //email.clear();  
   }
	  
	  
	  
	  


