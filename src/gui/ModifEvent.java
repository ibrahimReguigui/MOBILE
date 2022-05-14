/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Event;
import services.ServiceEvent;

/**
 *
 * @author ouss
 */
public class ModifEvent extends BaseForm {
    public ModifEvent(Resources res,Form previous,Event fi) {
        super("Modifier Evenement ", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
       
        super.addSideMenu(res);
       
        tb.addSearchCommand(e -> {});
       
       
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
       
                add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(2,
                            facebook, twitter
                    )
                )
        ));

        TextField nomevent = new TextField("", "entrer Nom");
        nomevent.setUIID("TextFieldBlack");
        addStringValue("nom",nomevent);
        
        TextField nbrplaces = new TextField("", "entrer le nombre max de participant");
        nbrplaces.setUIID("TextFieldBlack");
        addStringValue("nombre maximale de participants",nbrplaces);
        
        TextField datedebut = new TextField("", "entrer date evenement sous format yyyy-mm-dd");
        datedebut.setUIID("TextFieldBlack");
        addStringValue("numContact",datedebut);
        
        TextField datefin = new TextField("", "entrer description");
        datefin.setUIID("TextFieldBlack");
        addStringValue("description",datefin);
        
        TextField location = new TextField("", "entrer localisation");
        location.setUIID("TextFieldBlack");
        addStringValue("localisation",location);
        
        TextField imag= new TextField("", "entrer image");
        imag.setUIID("TextFieldBlack");
        addStringValue("image",imag);
  
               
        Button Edit = new Button("Edit");
        Edit.addActionListener((evt) -> {
            ServiceEvent sp = new ServiceEvent();
            fi.setNomevent(String.valueOf(nomevent.getText()));
            fi.setNbrplaces(Integer.valueOf(nbrplaces.getText()));
            fi.setDatedebut(String.valueOf(datedebut.getText()));
            fi.setdatefin(String.valueOf(datefin.getText()));
            fi.setLocation(String.valueOf(location.getText()));
            fi.setImg(String.valueOf(imag.getText()));
            sp.editEvent(fi);
            Dialog.show("Success","Sponsor modifier avec success",new Command("OK"));
            new EventForm(res).show();
           
        });
        addStringValue("", FlowLayout.encloseRightMiddle(Edit));
       
    }
   
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
 }
}

