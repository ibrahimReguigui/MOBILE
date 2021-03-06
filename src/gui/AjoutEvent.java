package gui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Event;
import services.ServiceEvent;

/**
 *
 * @author ouss
 */
public class AjoutEvent extends BaseForm {
    Form current;
    public AjoutEvent(Resources res,Form previous,Event fi) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Evenement");
        getContentPane().setScrollVisible(false);
        
       
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {

        });
       Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe,s1,res.getImage("logo.png"),"","",res);

        // Design

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
       
       TextField nom = new TextField("", "entrer Nom");
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom",nom);
        
        TextField nbmax_participant = new TextField("", "entrer le nombre max de participant");
        nbmax_participant.setUIID("TextFieldBlack");
        addStringValue("nbr max participants",nbmax_participant);
        
        TextField date_evenement = new TextField("", "entrer date evenement sous format yyyy-mm-dd");
        date_evenement.setUIID("TextFieldBlack");
        addStringValue("Date event",date_evenement);
        
        TextField description = new TextField("", "entrer description");
        description.setUIID("TextFieldBlack");
        addStringValue("description",description);
        
        TextField localisation = new TextField("", "entrer localisation");
        localisation.setUIID("TextFieldBlack");
        addStringValue("localisation",localisation);
        
        TextField image = new TextField("", "entrer image");
        image.setUIID("TextFieldBlack");
        addStringValue("image",image);
        
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
      
      btnAjouter.addActionListener((e) -> {
            try {
                
                if(nom.getText().equals("") || nom.getText().equals("")) {
                    Dialog.show("Veuillez v??rifier les donn??es","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                     Event r = new Event(Integer.valueOf(nbmax_participant.getText()),String.valueOf(date_evenement.getText()).toString(),String.valueOf(nom.getText()).toString(),String.valueOf(description.getText()).toString(),String.valueOf(localisation.getText()).toString(),String.valueOf(image.getText()).toString());
                    
                    System.out.println("data  reclamation == "+r);
           
                     //appelle methode ajouterReclamation mt3 service Reclamation bch nzido donn??es ta3na fi base 
                    ServiceEvent.getInstance().ajoutEvent(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                    new EventForm(res).show();
                    
                    
                    refreshTheme();//Actualisation
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
      });
            
      


    }
      private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));

    }
    
       public void bindButtonSelection(Button btn , Label l)
    {
        btn.addActionListener(e -> {

            if(btn.isSelected())
            {
                updateArrowPosition(btn,l);
            }

        });
    }

    private void updateArrowPosition(Button btn, Label l) {

        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }


    
      private void addTab(Tabs swipe, Label spacer ,Image image, String string, String text, Resources res) {

        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size)
        {
            image = image.scaledHeight(size);
        }

        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2)
        {
             image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("","ImageOverlay");

        Container page1 = LayeredLayout.encloseIn(
            imageScale,
            overLay,
            BorderLayout.south(
                BoxLayout.encloseY(
                    new SpanLabel(text, "LargeWhiteText"),
                    spacer
                )
            )
        );

        swipe.addTab("",res.getImage("resv1.png"), page1);


    }
  
    
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

      
      
   }
    
  
}
