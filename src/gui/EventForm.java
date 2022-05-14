/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import entities.Event;
import java.util.ArrayList;
import services.ServiceEvent;

/**
 *
 * @author Asus
 */
public class EventForm extends Form {
     Form current;
      public EventForm(Resources res) {
           getTitleArea().setUIID("Container");
        setUIID("background9");

        current = this; //Récupération de l'interface(Form) en cours
        
        setTitle("Event");
        setLayout(BoxLayout.y());

        add(new Label("Liste Events"));
                    Toolbar.setGlobalToolbar(true);
             add(new InfiniteProgress());

                    Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                    removeAll();
                    
                    ArrayList <Event> paniers = new ArrayList();
                        ServiceEvent sa =new ServiceEvent();
                    paniers=sa.getAllEvents();
                     /*   Button btnadd = new Button("Ajouter");
                     add(btnadd);
                     btnadd.addActionListener((evt) -> {
                         new AjouterEmplacement(res).show();
                    });*/
            for (Event s : ServiceEvent.getInstance().getAllEvents()) {
                            MultiButton m = new MultiButton();
                            Container c2=new Container(BoxLayout.y()
                            );
                            c2.add(res.getImage(s.getImg()));
                            m.setTextLine1(s.getNomevent());
                          //  m.setTextLine2(theme.getImage(fi.getImage()));
                            m.setTextLine2("Date de l'evenement :"+s.getDatedebut());
                           // m.setTextLine4(fi.getImage());
                           // m.setTextLine4("Description :"+fi.getDescription());
                           m.addActionListener(e -> {
                    
      

           Form f2 = new Form("Detail",BoxLayout.y());
           
            MultiButton a = new MultiButton();
            // MultiButton b = new MultiButton();
      Button b=new Button("list Evenement");
      Button map= new Button("Map");
      Container c1 = new Container(BoxLayout.y());
      c1.add(res.getImage(s.getImg()));
      a.setTextLine1(s.getNomevent());
      a.setTextLine2("aura lieu le "+s.getDatedebut());
       a.setTextLine3("à "+s.getLocation());
  //  a.setTextLine4("nb likes "+c.getNb_likes()+"  "+"nb commentaire "+c.getNb_commentaire());
     //b.setEmblem(theme.getImage(c.getImage()));
          SpanLabel description = new SpanLabel("» "+s.getNomevent());
       a.getAllStyles().setFgColor(0x000000);
     a.setVisible(true);
      Container cnt2 = new Container(BoxLayout.y());
      
       cnt2.add(c1);
       cnt2.add(a);
       Container cnt1 = new Container(new BorderLayout());
         
          cnt1.add(BorderLayout.NORTH,description);
    cnt1.add(BorderLayout.CENTER,cnt2);

     //genrer qrcode
      
    
//     MultiButton d = new MultiButton();
    
    
    
    
     f2.add(map);
     f2.add(b);        
     f2.add(cnt1);
     b.addActionListener(t-> new EventForm(res).show());
     map.addActionListener(r->new MapForm());

 f2.show();
 
 
 
        });
                            m.addLongPressListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {              
               if (Dialog.show("Confirmation", "Voulez vous Ajouter un Evenement?", "Oui", "Non")) {
                                   new AjoutEvent(res,current,s).show();
                                       }
               
                     if (Dialog.show("Confirmation", "Voulez vous Modifier cet Evenement?", "Oui", "Non")) {
                                   new ModifEvent(res,current,s).show();
                                       }

                     else {
                          if (Dialog.show("Confirmation", "Voulez vous Supprimer cet Evenement ?", "Supprimer", "Annuler")) {
                        if( ServiceEvent.getInstance().deletePanier(s)){
                            {
                                   Dialog.show("Success","supprimer",new Command("OK"));
                                   new EventForm(res).show();
                            }
                   
                }
                      
                          }
                }
            }
        });
add(c2);
                            add(m);
                             }
                     revalidate();
                       
                         });
                   
                });
                   
                
                
               
    getToolbar().addSearchCommand( e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
       for(Component cmp :  getContentPane()) {
            if(cmp instanceof MultiButton) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
//            String line3 = mb.getTextLine3();
//            String line4 = mb.getTextLine4();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
            line2 != null && line2.toLowerCase().indexOf(text) > -1 ;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);}
    }
}, 4);
   
//        getToolbar().addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
//        getToolbar().addMaterialCommandToSideMenu("Menu", FontImage.MATERIAL_UPDATE, e -> new ListEmpForm(res).show());
//    getToolbar().addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_UPDATE, e -> new ListEmpForm(res));
     //getToolbar().addMaterialCommandToSideMenu("Sponsor", FontImage.MATERIAL_UPDATE, e -> new ListSponsor(res).show());
      //getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show())
    
    }
                             
}