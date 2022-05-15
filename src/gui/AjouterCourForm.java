///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gui;
//
//import com.codename1.l10n.DateFormat;
//import com.codename1.l10n.SimpleDateFormat;
//import static com.codename1.push.PushContent.setTitle;
//import com.codename1.ui.Button;
//import com.codename1.ui.Command;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.Display;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.TextArea;
//import com.codename1.ui.TextField;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.spinner.Picker;
//import com.codename1.ui.util.Resources;
//import entities.CourSalle;
//import services.ServiceCourSalle;
//
///**
// *
// * @author Asus
// */
//public class AjouterCourForm extends Form {
//    Form current;
//    public AjouterCourForm(Form previous,Resources res) {
//        
//        getTitleArea().setUIID("Container");
//        setUIID("background9");
//        DateFormat formatter = new SimpleDateFormat();
//        /*
//        Le paramètre previous définit l'interface(Form) précédente.
//        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
//        en utilisant le bouton back
//        */
//        setTitle("Ajouter Cour");
//        setLayout(BoxLayout.y());
//        
//        TextField tfNom = new TextField("","Nom");tfNom.setUIID("TextField");
//        TextField tfInformation= new TextField("", "Information");
//        TextField tfNbrTotal= new TextField("", "Nombre",20,TextArea.NUMERIC);
//        Picker datePicker = new Picker();
//        datePicker.setType(Display.PICKER_TYPE_DATE);
//        Picker datePicker2 = new Picker();
//        datePicker2.setType(Display.PICKER_TYPE_TIME);
//        Button btnValider = new Button("Ajouter");
//        
//        btnValider.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//               
//                    try {
//                        CourSalle c = new CourSalle(3,0,Integer.parseInt(tfNbrTotal.getText()),datePicker.getText(),datePicker2.getText(),tfInformation.getText(), tfNom.getText());
//                        if( ServiceCourSalle.getInstance().addCourSalle(c)){
//                            Dialog.show("Success","Cour ajoutée",new Command("OK"));
//                            new ListeCourSalleForm(res).show();
//                        }
//                        else
//                            Dialog.show("ERROR", "Server error", new Command("OK"));
//                    } catch (NumberFormatException e) {
//                        Dialog.show("ERROR", "Nombre incorrect", new Command("OK"));
//                    }
//                    
//                
//                
//                
//            }
//        });
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
//        addAll(tfNom,tfInformation,tfNbrTotal,datePicker,datePicker2,btnValider);
////        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
////                , e-> previous.showBack()); // Revenir vers l'interface précédente
////                
//    }
//}
