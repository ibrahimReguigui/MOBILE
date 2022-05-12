package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class HomeForm extends Form{
	
	Form current;
	
	public HomeForm() {
		current = this;
		setTitle("Home");
		setLayout(BoxLayout.y());
		
		add(new Label("Que voulais-vous faire ?"));
		Button btnAddUser = new Button("Ajouter utilisateur");
		Button btnListUser = new Button("Lister les Utilisateurs");
		btnAddUser.addActionListener(e->new AddUserForm(current).show());
		btnListUser.addActionListener(e->new ListUsersForm(current).show());
		addAll(btnAddUser,btnListUser);
	}

}
