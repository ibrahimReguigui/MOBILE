/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/**
 *
 * @author rocky
 */


import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.entities.Exercice;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceExercice;
//import static java.awt.SystemColor.desktop;
import java.util.ArrayList;


public class PDF extends BaseForm {

    public PDF(Resources res) {
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des Exercices");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

       
   

        //this.theme=theme;
        SpanLabel sp = new SpanLabel();

        sp.setText(ServiceExercice.getInstance().affichageExercice().toString());
        add(sp);
        //// f twig 
        Button pdf = new Button("pdf");
        add(pdf);
        pdf.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {


                    String path = "/Users/rocky";
                    Document document = new Document();
                    try {

                   //  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + "exercice.pdf"));

                        document.open();
                        PdfPTable tb1 = new PdfPTable(10);
                        tb1.addCell("NomExercice");
                        tb1.addCell("DescriptionExercice");
                        tb1.addCell("CategorieExercice");

                        //tb1.addCell("img");
                        tb1.addCell("NbrRepetition");
                        tb1.addCell("NbrSerie");

                        ServiceExercice es = new ServiceExercice();
                        ArrayList<Exercice> list = es.affichageExercice();
                        for (Exercice m : list) {

                            String nom = String.valueOf(m.getNomExercice());
                            String description = String.valueOf(m.getDescriptionExercice());
                            
                            String categorie = String.valueOf(m.getCategorieExercice());

                            String nbrRepetition = String.valueOf(m.getNbrRepetition());
                            String nbrSerie = String.valueOf(m.getNbrSerie());

                            tb1.addCell(nom);
                            tb1.addCell(description);
                            tb1.addCell(categorie);
                            //tb1.addCell(image);
                            tb1.addCell(nbrRepetition);
                            tb1.addCell(nbrSerie);

                        }
                        document.add(new Paragraph("Reunion"));

                        document.add(tb1);
                        document.close();
         //writer.close();
                        com.codename1.io.File file = new com.codename1.io.File("exercice.pdf");
                        new ListExerciceForm(res).show();

 //desktop.open(file);
                    } 
                    catch (Exception e){
                        e.printStackTrace();
                    
                  
                   
      
              }}
                    //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);

                    //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);
                    //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

             


        });}
    
}
