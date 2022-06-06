/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;


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


import com.codename1.components.InfiniteProgress;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author infoevo
 */
public class HomeStat extends BaseForm {

 private boolean drawOnMutableImage;

 private double nbr_feedback = 4;
 private double nbr_reclamation = 3;
   
   
    BaseForm form;
    Form current;
   public HomeStat(Resources res) {
        current=this; //Back 
        setTitle("Home Gerant");
        setLayout(BoxLayout.y());
        
createPieChartForm();
}



 private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img,String title) {
          int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

  ;       
      
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
               ));
       
//       image.addActionListener(e -> {
//           try{
//           new AddCategoryForm(Resources.getGlobalResources()).show();
//           }catch(Exception exx) {
//               
//           }
//               });
//        add(cnt);
//        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    

    //Statistique :
    //fontion : bch n7adhro size ta3 labels ta3 stat w margin w colors ba3d chn3aytoulha methode hethi.
    public DefaultRenderer buildCatRendrer(int []colors) {
        
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[] {20, 30, 15, 0});
        
        for(int color : colors) {
            SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
            
            simpleSeriesRenderer.setColor(color);
            renderer.addSeriesRenderer(simpleSeriesRenderer);
        }
        return renderer;
     }  
    
    
    public void createPieChartForm() {
        
        //chna3ml stat feedback par rapport l reclamation 
        double total = nbr_feedback + nbr_reclamation;
        
        //values
        double prcntFeed = (nbr_feedback *100)/total;
        
        double prcntRec = (nbr_reclamation * 100)/total;
        
        //colors set:
        int[]colors = new int[]{0xf4b342, 0x52b29a};
        
        DefaultRenderer renderer = buildCatRendrer(colors);
        renderer.setLabelsColor(0x000000); // black color for labels.
        
        renderer.setZoomButtonsVisible(true);//zoom
        renderer.setLabelsTextSize(40);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setHighlighted(true);
        
        //CREATe the chart ...
        PieChart chart = new PieChart(buildDataset("title",Math.round(prcntFeed),Math.round(prcntRec)), renderer);
        
        // n7oto chart fi component
        ChartComponent c  = new ChartComponent(chart);
        
        String []messages = {
            "Les Statistiques des Ventes "
        };
        
        SpanLabel message = new SpanLabel(messages[0], "WelcomeMessage");
        
        Container cnt = BorderLayout.center(message);
        cnt.setUIID("Container");
        add(cnt);
        add(c);

//
Button btnAddTask = new Button("Ma Position");
btnAddTask.addActionListener(e-> new MapForm());
addAll(btnAddTask);
//
                
                
    }

    private CategorySeries buildDataset(String title, double prcntFeed, double prcntRec) {
        
        CategorySeries series = new CategorySeries(title);
        
        series.add("Vetements",prcntRec);
        series.add("Materiels",prcntFeed);
        
        return series;
    }
        
    }