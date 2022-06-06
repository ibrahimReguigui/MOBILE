/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Exercice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.lang.Iterable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import utils.Statics;


/**
 *
 * @author rocky
 */
public class ServiceExercice {
 
        //singleton 

    public static ServiceExercice instance = null ;
    
    public static boolean resultOk = true;

        //initilisation connection request 
    private ConnectionRequest req;
    
    
    
      public static ServiceExercice getInstance() {
        if(instance == null )
            instance = new ServiceExercice();
        return instance ;
    }

    
        public ServiceExercice() {
        req = new ConnectionRequest();
        
    }
        
         //ajout 
    public void ajoutExercice(Exercice exercice) {
        //http://127.0.0.1:8000/exercice/addExercice?nomExercice=exercice5&descriptionExercice=fairecacaca&categorieExercice=poitrine&nbrRepetition=15&nbrSerie=5

        String url =Statics.BASE_URL+"exercice/addExercice?nomExercice="+exercice.getNomExercice()+"&descriptionExercice="+exercice.getDescriptionExercice()+"&categorieExercice="+exercice.getCategorieExercice()+"&nbrRepetition="+exercice.getNbrRepetition()+"&nbrSerie="+exercice.getNbrSerie(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //Affichage 
    
    public ArrayList<Exercice>affichageExercice() {
        ArrayList<Exercice> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"exercice/exercicejson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Exercice re = new Exercice();
                        
//                   a.setId((int) id);
//                a.setIntitule(obj.get("intitule").toString());
//                a.setPays(obj.get("paysevent").toString());
//                a.setAdresse(obj.get("adresse").toString());
//                a.setType(obj.get("typeevent").toString());
//                a.setPhoto(obj.get("photo").toString());
                        //dima id fi codename one float 5outhouha
                              float id = Float.parseFloat(obj.get("id").toString());

                                               re.setId((int)id);

                       re.setNomExercice(obj.get("nomExercice").toString());
                       re.setDescriptionExercice(obj.get("descriptionExercice").toString());
                       re.setCategorieExercice(obj.get("categorieExercice").toString());
                       re.setNbrRepetition((int) Float.parseFloat(obj.get("nbrRepetition").toString()));
                       re.setNbrSerie((int) Float.parseFloat(obj.get("nbrSerie").toString()));
                      
                      // re.setId((int)id);
                   
                       
                 
                      

//                        float etat = Float.parseFloat(obj.get("etat").toString());
                        
                       
                        //re.setNomExercice(objet);
//                        re.setDescription(description);
//                        re.setEtat((int)etat);
                        
                       
                     
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
        public Exercice DetailRecalamation( int id , Exercice exercice) {
        
            String url = Statics.BASE_URL+"exercice/detailExercice?"+id;
            req.setUrl(url);
        
        String str  = new String(req.getResponseData());
             req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                exercice.setNomExercice(obj.get("obj").toString());
            
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return exercice;
        
        
    }
    
    
    
    
   
   
      //Delete 
    public boolean deleteExercice(int id ) {
        String url = Statics.BASE_URL +"exercice/deleteExercice?id="+id;
            
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
        
}
