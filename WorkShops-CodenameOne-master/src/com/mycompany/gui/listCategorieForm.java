/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.services.ServicesCategories;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Category;



import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Dialog;

import com.mycompany.gui.listProduitForm;
import com.mycompany.gui.listCategorieForm;
import com.codename1.components.InfiniteProgress;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author infoevo
 */
public class listCategorieForm extends Form{

 public listCategorieForm() {


        getTitleArea().setUIID("container");
        setUIID("background");
        setTitle("Liste des Categories");
        setLayout(BoxLayout.y());
        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServicesCategories.getInstance().getAllTasks().toString());
        

for (Category s : ServicesCategories.getInstance().getAllTasks()) {
            
            System.out.println(s);
            Container cpt = TableLayout.encloseIn(6,
                    new Label(s.getName())
                   
            //                new Label(valueOf(s.getDate())),
            //                new Label(s.gettCour())

 );
            
            add(cpt);

Button btnSupprimer = new Button("Supprimer");
btnSupprimer.addPointerPressedListener(l->{
Dialog dig = new Dialog("suppression");
if(ServicesCategories.getInstance().deleteCategory(s.getId())){
System.out.println("supprimé");
}
});

add(btnSupprimer);
////btnSupprimer.deleteCategory(s.getId());
//        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());


  }

Button btnAddTask = new Button("Add Category");
btnAddTask.addActionListener(e-> new addCategorieForm().show());
addAll(btnAddTask);



 // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new StatPieForm().showBack());

}
  //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());


}
