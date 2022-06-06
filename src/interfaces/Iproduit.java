/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Produit;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface Iproduit {
    public void ajouterproduit(Produit s);
    public void ajouterproduit1(Produit s);
      public List<Produit> afficherproduit();
      public void modifierproduit(Produit s);
      public void suppproduit(int s);
       public List<Produit> filtrerproduit();
       
       //
             //
      }
