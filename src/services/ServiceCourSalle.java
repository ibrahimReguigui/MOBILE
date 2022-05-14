/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Caracteristiquesportif;
import com.sun.mail.smtp.SMTPTransport;
import entities.CourSalle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import utils.Statics;

/**
 *
 * @author Asus
 */
public class ServiceCourSalle {

    public ArrayList<CourSalle> CoursSalle;
public ArrayList<Caracteristiquesportif> caracteristiquesportifs;
    
   
    public static ServiceCourSalle instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCourSalle() {
        req = new ConnectionRequest();
    }

    public static ServiceCourSalle getInstance() {
        if (instance == null) {
            instance = new ServiceCourSalle();
        }
        return instance;
    }

    public ArrayList<CourSalle> parseCourSalle(String jsonText) {
        try {
            CoursSalle = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
             */
            Map<String, Object> CourSalleListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) CourSalleListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                CourSalle c = new CourSalle();
                c.setNomCour(obj.get("nomCour").toString());
                float nbrActuel = Float.parseFloat(obj.get("nbrActuel").toString());
                c.setNbrActuel((int) nbrActuel);
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                float nbrTotal = Float.parseFloat(obj.get("nbrTotal").toString());
                c.setNbrTotal((int) nbrTotal);
                c.setInformation(obj.get("information").toString());
                c.setDate(obj.get("date").toString());
                c.settCour(obj.get("tCour").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                CoursSalle.add(c);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return CoursSalle;
    }

    public boolean addCourSalle(CourSalle c) {
        String url = Statics.BASE_URL + "salle/cour/MOBILE/new?nom=" + c.getNomCour() + "&info=" + c.getInformation() + "&nbr=" + c.getNbrTotal() + "&date=" + c.getDate() + "&time=" + c.gettCour() + "&idSalle=" + c.getId(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<CourSalle> getCourSalle() {
        String url = Statics.BASE_URL + "salle/cour/MOBILE/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CoursSalle = parseCourSalle(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CoursSalle;
    }

    public boolean supprimerCourSalle(CourSalle c) {
        String url = Statics.BASE_URL + "salle/cour/MOBILE/delete?id=" + c.getId(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<CourSalle> recherche(String data) {
        String url = Statics.BASE_URL + "salle/cour/MOBILE/recherche?data=" + data; //création de l'URL
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CoursSalle = parseCourSalle(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CoursSalle;
    }

    public void sendMail(Resources res,int id) {
        try {
            String url = Statics.BASE_URL + "salle/cour/MOBILE/listePdfDownloadMobile?id="+id; //création de l'URL
            req.setUrl(url);// Insertion de l'URL de notre demande de connexion
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this); //Supprimer cet actionListener
                    /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp"); //SMTP protocol
            props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtps.auth", "true"); //enable authentication

            Session session = Session.getInstance(props, null); // aleh 9rahach 5ater mazlna masabinach javax.mail .jar

            MimeMessage msg = new MimeMessage(session);

            msg.setRecipients(Message.RecipientType.TO, "ibrahim.reguigui@esprit.tn");
            msg.setSubject("Fitnessni ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<body style=\"\n"
                    + "background-image:url(https://www.hdwallpaper.nu/wp-content/uploads/2018/02/weightlifting-20-1024x661.jpg);\n"
                    + " background-repeat: no-repeat; background-position: top center;\n"
                    + "background-size: auto 120%; text-align: center; \">\n"
                    + "\n"
                    + "<img style=\"width: 100%;display: block;\n"
                    + "  margin-left: auto;\n"
                    + "  margin-right: auto;\n"
                    + "  width: 80%;\" src=\"https://i.ibb.co/RvTSH0y/logo.png\">\n"
                    + "\n"
                    + "<p style=\"text-align: center;font-weight: 800;color:white;font-family: cursive;font-style: italic;font-size: 2.5em;\">Bienvenue </p>\n"
                    + "<p style=\"text-align: center;font-weight: 800;color:white;font-family: cursive;font-style: italic;font-size: 2.5em;\">Vous trouverez ci-joint la liste des cours demandé</p>\n"
                    + "<p style=\"text-align: center;font-weight: 800;color:white;font-family: cursive;font-style: italic;font-size: 2.5em;\">Merci d'avoir utilisé notre application</p>\n"
                    + "</body>", "text/html; charset=utf-8");
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File("C:\\Users\\Asus\\Desktop\\PIWeb\\PIWeb\\public\\mypdf.pdf"));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            msg.setContent(multipart);
            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");

            st.connect("smtp.gmail.com", 465, "ibrahim.reguigui@esprit.tn", "213JMT1808");

            st.sendMessage(msg, msg.getAllRecipients());

            System.out.println("server response : " + st.getLastServerResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public ArrayList<Caracteristiquesportif> parseCaracteristiquesportif(String jsonText) {
        try {
            caracteristiquesportifs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> caracteristiquesportifListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) caracteristiquesportifListJson.get("root");
                
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                Caracteristiquesportif cs = new Caracteristiquesportif();
               // cs.setId((int) Float.parseFloat(obj.get("id").toString()));

               
                cs.setTailleSportif(((int) Float.parseFloat(obj.get("tailleSportif").toString())));
              

               cs.setAgeSportif(((int) Float.parseFloat(obj.get("ageSportif").toString())));
                
               
               cs.setSexe(obj.get("sexe").toString());
               
               cs.setObjectifNutrition(obj.get("objectifNutrition").toString());
                 
                 

               cs.setBesoinProteine(((int) Float.parseFloat(obj.get("besoinProteine").toString())));
              
               cs.setPoidSportif(((int) Float.parseFloat(obj.get("poidSportif").toString())));
               
               cs.setBmiSportif(((int) Float.parseFloat(obj.get("bmiSportif").toString())));

               cs.setBesoinCarb(((int) Float.parseFloat(obj.get("besoinCarb").toString())));
                
               cs.setBesoinCalories(((int) Float.parseFloat(obj.get("besoinCalories").toString())));
               
               
                              

                System.out.println(cs);
               
              
                
                
                
                caracteristiquesportifs.add(cs);
            }

        } catch (IOException ex) {

        }
        return caracteristiquesportifs;
    }

    public ArrayList<Caracteristiquesportif> getAllCaracteristiquesportif() {

        String url = Statics.BASE_URL + "AllCarac";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                caracteristiquesportifs = parseCaracteristiquesportif(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return caracteristiquesportifs;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    
public boolean addCaracteristiquesportif(Caracteristiquesportif c) {
        System.out.println(c);
        System.out.println("******");
       //String url = Statics.BASE_URL + "create?name=" + t.getTailleSportif() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "AddCaracteristiquesportif?taille="+c.getTailleSportif() +"&poid=" +c.getPoidSportif()+"&age=" +c.getAgeSportif()+"&sexe=" +c.getSexe()+"&objectifNutrition=" +c.getObjectifNutrition();
    
       req.setUrl(url);
       
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
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
      public boolean deleteCaracteristiquesportif(int id) {
        String url = Statics.BASE_URL + "j/suppCaracteristiquesportif/" + id;

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

    //////////////////////////////////////////////////////////////////////////////////////////
      
      
      
       public boolean modifierCaracteristiquesportif(Caracteristiquesportif c) {
        String url = Statics.BASE_URL + "/j/modifCaracteristiquesportif?id=" + c.getId() + "&age=" + c.getAgeSportif() + "&taille=" + c.getTailleSportif() + "&poids=" + c.getPoidSportif() + "&genre=" + c.getSexe();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request makenech yet3ada chy dima nal9awha
        return resultOK;

    }
}
