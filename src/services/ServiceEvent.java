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
import entities.Event;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author ouss
 */
public class ServiceEvent {

    public ArrayList<Event> emp;
   
    public static ServiceEvent instance=null;
    public boolean resultOK;
    private final ConnectionRequest req;
    public ServiceEvent() {
         req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }
   


 public ArrayList<Event> parsePanier(String jsonText){
                try {
                    char firstChar = jsonText.charAt(0);
                    if(firstChar != '[')
                    {
                    jsonText="["+jsonText+"]";
                    }
                    System.out.println(jsonText);
            emp =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
                    System.out.println("mapp***********"+tasksListJson);
            for(Map<String,Object> obj : list){
                Event a = new Event();
                
                float id = Float.parseFloat(obj.get("idevent").toString());
//                float nbmax_participant = Float.parseFloat(obj.get("nbmax_participant").toString());
                     System.out.println("mapp***********"+list);
                
                a.setIdevent((int)id);
                a.setNomevent(obj.get("nomevent").toString());
               // a.setNbmax_participant((int)nbmax_participant);
                a.setDatedebut(obj.get("datedebut").toString());
                a.setdatefin(obj.get("datefin").toString());
                a.setLocation(obj.get("location").toString());
                a.setImg(obj.get("img").toString());
                emp.add(a);

            }
        } catch (IOException ex) {
            
        }
        return emp;
    }
 
 public ArrayList<Event> getAllEvents(){  
        emp =new ArrayList<>();     
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/allEvent");  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                emp = ser.parsePanier(new String(con.getResponseData()));
                System.out.println(emp.toString());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("last list");
        System.out.println(emp.toString());
        return emp;
    }
 
 public void ajoutEvent(Event a) {
        
        String url=Statics.BASE_URL+"addEventJSON/new?nomevent="+a.getNomevent()+"&nbrplaces="+a.getNbrplaces()+"&datedebut="+a.getDatedebut()+"&datefin="+a.getNdatefin()+"&location="+a.getLocation()+"&img="+a.getImg(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    

        public boolean editEvent(Event u) {
        String url = Statics.BASE_URL + "updateEventJSON/"+u.getIdevent()+
                "?nomevent="+u.getNomevent()+"&nbmrplaces="+u.getNbrplaces()+"&datedebut="+u.getDatedebut()+"&datefin="+u.getNdatefin()+"&location="+u.getLocation()+"&img="+u.getImg();
               req.setUrl(url);
               System.out.println(url);
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



    public boolean deletePanier(Event fi) {
        String url = Statics.BASE_URL + "deleteEventJSON/" + fi.getIdevent();
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
