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
import entities.Produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author infoevo
 */
public class ServicesProduits {

  public ArrayList<Produit> tasks;
    
    public static ServicesProduits instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicesProduits() {
         req = new ConnectionRequest();
    }

    public static ServicesProduits getInstance() {
        if (instance == null) {
            instance = new ServicesProduits();
        }
        return instance;
    }

   public ArrayList<Produit> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit t = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               
                if (obj.get("description")==null)
                t.setDescription("null");
                else
                    t.setDescription(obj.get("description").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    

    public ArrayList<Produit> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"produit/afficheproduitmobil";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }

//
    //Delete 
    public boolean deleteProduit(int id ) {
        String url = Statics.BASE_URL +"produit/j/suppProd/"+id;
        
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




}
