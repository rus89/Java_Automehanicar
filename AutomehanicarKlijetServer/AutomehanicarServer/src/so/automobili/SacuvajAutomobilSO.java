/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.automobili;

import domen.Automobil;
import java.util.ArrayList;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class SacuvajAutomobilSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        OpstaSO o = new VratiSveAutomobileSO();
        o.izvrsiOperaciju(new Automobil());
        ArrayList<Automobil> la = (ArrayList<Automobil>) o.getObjekat();
        if (la.isEmpty()) {
            return ;
        }
        
        Automobil a = (Automobil) objekat;
        for (Automobil automobil : la) {
            if (automobil.getBrojRegistracije().equals(a.getBrojRegistracije())) {
                throw new Exception("Automobil sa tim registarskim brojem već postoji!");
            }
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Automobil a = (Automobil) objekat;
        a.postaviUslov(Konstante.SACUVAJ_AUTOMOBIL);
        DBBroker.vratiInstancu().sacuvajObjekat(a);
    }
    
}
