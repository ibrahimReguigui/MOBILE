/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Caracteristiquesportif;
import com.codename1.ui.ComboBox;
import com.codename1.ui.TextArea;
import com.codename1.ui.util.Resources;
import services.ServiceCourSalle;


/**
 *
 * @author Asus
 */
public class addcarac extends Form{
    Form current;
    public addcarac(Form previous) {
        
        
        
        
         
        getTitleArea().setUIID("Container");
        setUIID("background9");
     TextField tftaille = new TextField("","Taille",20,TextArea.NUMERIC);
        TextField tfpoid= new TextField("", "Poid",20,TextArea.NUMERIC);
        TextField tfage= new TextField("", "Age",20,TextArea.NUMERIC);
        ComboBox sexe = new ComboBox();
        ComboBox objectifNutrition= new ComboBox();
        sexe.addItem("Homme");
        sexe.addItem("Femme");
        objectifNutrition.addItem("Gain de poid");
        objectifNutrition.addItem("Perte de poid");
        
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
        Button btnValider = new Button("Consulter resultat");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tftaille.getText().length()==0)||(tfpoid.getText().length()==0)||(tfage.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                      
                        
                        Caracteristiquesportif c = new Caracteristiquesportif(Integer.parseInt(tftaille.getText()), Integer.parseInt(tfpoid.getText()), Integer.parseInt(tfage.getText()));
                        
                          if (sexe.getSelectedIndex() == 0) {
                            c.setSexe("Homme");
                        } else {
                            c.setSexe("Femme");
                        }
                        
                            if (objectifNutrition.getSelectedIndex() == 0) {
                            c.setObjectifNutrition("Gain_de_poid");
                        }   else {
                            c.setObjectifNutrition("Pert_de_poid");
                        }
                        
                        
                        
                        
                        
                        
                        if( ServiceCourSalle.getInstance().addCaracteristiquesportif(c))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Invalid input", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tftaille, tfpoid, tfage, sexe, objectifNutrition, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
                
    }}

