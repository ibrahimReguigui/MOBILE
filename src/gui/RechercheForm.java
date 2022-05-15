///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gui;
//
//import com.codename1.ui.Button;
//import com.codename1.ui.Container;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.Image;
//import com.codename1.ui.Label;
//import com.codename1.ui.TextField;
//import com.codename1.ui.Toolbar;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.table.TableLayout;
//import com.codename1.ui.util.Resources;
//import entities.CourSalle;
//import static java.lang.String.valueOf;
////import services.ServiceCourSalle;
//
///**
// *
// * @author Asus
// */
//public class RechercheForm extends Form {
//    
//    Form current;
//
//    /*Garder traçe de la Form en cours pour la passer en paramètres 
//    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
//    la méthode showBack*/
//    public RechercheForm(Form previous,Resources res,String data) {
//
//        getTitleArea().setUIID("Container");
//        setUIID("background9");
//
//        current = this; //Récupération de l'interface(Form) en cours
//        current.repaint();
//        current.refreshTheme();
//        setTitle("Salle");
//        setLayout(BoxLayout.y());
//
//        add(new Label("Resultat Recherche"));
//
//        for (CourSalle s : ServiceCourSalle.getInstance().recherche(data)) {
//            System.out.println(s);
//            Container cpt = TableLayout.encloseIn(6,
//                    new Label(s.getNomCour()),
//                    new Label(s.getInformation()),
//                    new Label(valueOf(s.getNbrActuel()) + '/' + valueOf(s.getNbrTotal()))
//            //                new Label(valueOf(s.getDate())),
//            //                new Label(s.gettCour())
//
//            );
//            add(cpt);
//            Container cpt2 = TableLayout.encloseIn(6,
//                    new Label(s.getDate().substring(0, 9)),
//                    new Label(s.gettCour().substring(11, 19))
//            );
//            add(cpt2);
//            Button btnSupprimer = new Button("Supprimer");
//            add(btnSupprimer);
//            btnSupprimer.addActionListener(l -> {
//
//                Dialog dig = new Dialog("Suppression");
//                if (dig.show("Suppression", "Vous voulez supprimer ce cour ?", "Confirmer", "Retour")) {
//                    ServiceCourSalle.getInstance().supprimerCourSalle(s);
//                    current.show();
//                } else {
//                    dig.dispose();
//                }
//            });
//
//        }
//
//   Button btnRecherche = new Button("Chercher");
//        
//        TextField tbRecherche= new TextField("", "Recherche");
//        Toolbar tb = current.getToolbar();
//        Image icon = res.getImage("icon.png");
//        Container topBar = BorderLayout.east(new Label(icon));
//        topBar.add(BorderLayout.NORTH, new Label("Menu", "SidemenuTagline"));
//        topBar.add(BorderLayout.CENTER, tbRecherche);
//        topBar.add(BorderLayout.SOUTH, btnRecherche);
//        topBar.setUIID("SideCommand");
//        tb.addComponentToSideMenu(topBar);
//        
//        tb.addMaterialCommandToSideMenu("Liste Cours", FontImage.MATERIAL_HOME,e-> new ListeCourSalleForm(res).show());
//        tb.addMaterialCommandToSideMenu("Ajouter", FontImage.MATERIAL_WEB, e -> {new AjouterCourForm(current, res).show();
//        });
//        
//        btnRecherche.addActionListener(e -> new RechercheForm(current, res,tbRecherche.getText().toString()).show());
//    }
//}
