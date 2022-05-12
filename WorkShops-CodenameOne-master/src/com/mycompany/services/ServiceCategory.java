
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
package com.mycompany.services;

//import java.util.logging.Logger;
//import java.util.logging.Level;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Category;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;


import java.io.IOException;
*/


/**
 *
 * @author makre
 */
/*
public class ServiceCategory {

    boolean resultOK;


   

   
  public static ServiceCategory instance = null;
  
  //cnx//
  private ConnectionRequest req;

  
    public static  ServiceCategory getInstance(){
     if (instance==null)
        instance = new ServiceCategory();
      return instance ;
    }   
    private boolean resultOk;
    
    public ServiceCategory(){
        req = new ConnectionRequest();
    }

*/
    // ajouter  
   /* public void ajouterCategory(Category categoery){

    
      String url = Static.BASE_URL + "category/j/addCat?name=" +categoery.getName();

      req.setUrl(url); 

      req.addResponseCodeListener((e) ->{

          String str = new String (req.getResponseData()) ;
          System.out.println("data=="+str);
      System.out.println("bbbbbbbb");
      } );
System.out.println("aaaaaaa");
        NetworkManager.getInstance().addToQueueAndWait(req);
System.out.println("ccccccccccccc");
    }
*/


  
  /**
     *
     * @param id
     * @return
     */
/*
     public boolean deleteCategory (int id){
        String url = Statics.BASE_URL +"/category/id="+id+"/edit";
        
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
    
       public boolean modifierCategory( Category category) {
        String url = Statics.BASE_URL +"/category/1/edit?id="+category.getId()+"name="+category.getName();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
       
           //affichage
    
    public ArrayList<Category>affichageCategory() {
        ArrayList<Category> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/category/affichecategorymobil";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                   for(Map<String, Object> obj : listOfMaps){
                        Category re = new Category();
                        
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String name = obj.get("name").toString();
                        
                        
                        re.setId((int)id);
                        re.setName("name");
                        
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
        
        
    }
*/
       
  
   
 

