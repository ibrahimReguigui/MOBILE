/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import services.ServiceCourSalle;
import static services.ServiceCourSalle.instance;

/**
 *
 * @author Asus
 */
public class Listcarac extends Form{
     Form current;
     
    public Listcarac(Form previous) {
          
        getTitleArea().setUIID("Container");
        setUIID("background9");
                SpanLabel sp = new SpanLabel();
        sp.setText(ServiceCourSalle.getInstance().getAllCaracteristiquesportif().toString());
        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
