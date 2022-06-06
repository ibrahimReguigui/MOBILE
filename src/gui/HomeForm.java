package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.User;

public class HomeForm extends Form{
	
	
	 private Form current, hi, f2;
	    private Resources theme;
	    private Label lnom, lemail, lnum, lage;
	  //  private ImageViewer imgv;
	
	
	
	public HomeForm() {
		current = this;
		setTitle("Home");
		setLayout(BoxLayout.y());
		
		
		add(new Label("Bienvenue Mr l' "+SessionManager.getWhoami()+", Que voulais-vous faire ?"));
		Button btnAddUser = new Button("Ajouter utilisateur");
		Button btnListUser = new Button("Lister les Utilisateurs");
		btnAddUser.addActionListener(e->new AddUserForm(current).show());
		btnListUser.addActionListener(e->new ListUsersForm(current).show());
		addAll(btnAddUser,btnListUser);
		
		
//		    hi = new Form("Contacts", BoxLayout.y());
//	        f2 = new Form("Details", BoxLayout.y());
//	        lnom = new Label();
//	        lemail = new Label();
//	        lnum = new Label();
//	        lage  = new Label();
//	      //  imgv=new ImageViewer();
//	        addAll(sp,lnom,lemail,lnum,lage);
		
	}

	
	public Container addItem(User c) {
        Label labn = new Label(c.getNom());
        Button btn = new Button(c.getMailAdress());
        Label lbimg = new Label("go");

        Container cn1 = new Container(BoxLayout.y());
        cn1.addAll(labn, btn);

        Container cn2 = new Container(BoxLayout.x());
        cn2.addAll(cn1, lbimg);
        
        btn.addActionListener(e->{
           // imgv.setImage(theme.getImage(c.getImg()));
            lnom.setText("Nom :"+c.getNom());
            lemail.setText("Email :"+c.getMailAdress());
            lage.setText("age : "+c.getNumTel());
          //  lnum.setText("Num : "+c.getNum());
            f2.show();
        });
        f2.getToolbar().
                addCommandToLeftBar("back", theme.getImage("back-command.png"), ev->hi.show());
        cn2.setLeadComponent(btn);
        return cn2;
    }

	
	
	
}
