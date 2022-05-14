/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import entities.CourSalle;
import static java.lang.String.valueOf;
import services.ServiceCourSalle;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.smtp.SMTPTransport;
import java.io.File;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import utils.Statics;

/**
 *
 * @author Asus
 */
public class ListeCourSalleForm extends Form {

    TextField email;
    Form current;

    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    public ListeCourSalleForm(Resources res) {

        getTitleArea().setUIID("Container");
        setUIID("background9");

        current = this; //Récupération de l'interface(Form) en cours
        
        setTitle("Salle");
        setLayout(BoxLayout.y());

        add(new Label("Liste Cours"));

        for (CourSalle s : ServiceCourSalle.getInstance().getCourSalle()) {

            System.out.println(s);
            Container cpt = new Container(new BorderLayout());
            Label l1 = new Label(s.getNomCour());
            Label l2 = new Label(s.getInformation());
            Label l3 = new Label(valueOf(s.getNbrActuel()) + '/' + valueOf(s.getNbrTotal()));
            cpt.add(BorderLayout.CENTER, l3);
            cpt.add(BorderLayout.WEST, l1);
            cpt.add(BorderLayout.EAST, l2);

            add(cpt);
            Container cpt2 = new Container(new FlowLayout());
            Label l4 = new Label(s.getDate().substring(0, 9));
            Label l5 = new Label(s.gettCour().substring(11, 19));
            cpt2.add(l4);
            cpt2.add(l5);
            add(cpt2);
            Button btnSupprimer = new Button("Supprimer");
            add(btnSupprimer);
            btnSupprimer.addActionListener(l -> {

                Dialog dig = new Dialog("Suppression");
                if (dig.show("Suppression", "Vous voulez supprimer ce cour ?", "Confirmer", "Retour")) {
                    ServiceCourSalle.getInstance().supprimerCourSalle(s);
                    new ListeCourSalleForm(res).show();
                } else {
                    dig.dispose();
                }
            });
            
        }

        Button btnAjouter = new Button("Ajouter");
        Button btnRecherche = new Button("Chercher");
        add(btnAjouter);

        btnAjouter.addActionListener(e -> new AjouterCourForm(current, res).show());
        TextField tbRecherche = new TextField("", "Recherche");tbRecherche.setUIID("TextFieldBlack");
        Toolbar tb = current.getToolbar();
        Image icon = res.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.NORTH, new Label("Menu", "SidemenuTagline"));
        topBar.add(BorderLayout.CENTER, tbRecherche);
        topBar.add(BorderLayout.SOUTH, btnRecherche);
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Liste Cours", FontImage.MATERIAL_HOME, e -> new ListeCourSalleForm(res).show());
        tb.addMaterialCommandToSideMenu("Ajouter Cour", FontImage.MATERIAL_WEB, e -> {
            new AjouterCourForm(current, res).show();
        });
tb.addMaterialCommandToSideMenu(" Caracteristiques", FontImage.MATERIAL_HOME, e -> new caracForm(current).show());
        btnRecherche.addActionListener(e -> new RechercheForm(current, res, tbRecherche.getText().toString()).show());
        Button btnMail = new Button("Recevoir Par Mail");
        add(btnMail);

        btnMail.addActionListener(e -> {
            ServiceCourSalle.getInstance().sendMail(res, 3);
            System.out.println("*******************");
        });
        
        
        
         
    }

       
}
