package com.mycompany.gui;

import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.Layout;

public class BaseForm extends Form{
	
	 public BaseForm() {
	    }

	    public BaseForm(Layout contentPaneLayout) {
	        super(contentPaneLayout);
	    }

	    public BaseForm(String title, Layout contentPaneLayout) {
	        super(title, contentPaneLayout);
	    }
	    
	
	
	public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

}
