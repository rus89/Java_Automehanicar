/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.automobili;

import domen.Automobil;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class BrisanjeAutomobilaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Automobil a = (Automobil) objekat;
        a.postaviUslov(Konstante.OBRISI_AUTOMOBIL);
        DBBroker.vratiInstancu().obrisiObjekat(a);
    }
    
}
