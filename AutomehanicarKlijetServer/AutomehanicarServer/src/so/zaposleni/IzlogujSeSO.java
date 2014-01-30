/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import domen.Zaposleni;
import kolekcija.ListaUlogovanih;
import so.OpstaSO;

/**
 *
 * @author Rus
 */
public class IzlogujSeSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Zaposleni z = (Zaposleni) objekat;
        ListaUlogovanih.vratiInstancu().izbaciUlogovanog(z);
    }
    
}
