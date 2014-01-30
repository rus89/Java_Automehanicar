/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.intervencije;

import domen.Intervencija;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class BrisanjeIntervencijeSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Intervencija i = (Intervencija) objekat;
        i.postaviUslov(Konstante.OBRISI_INTERVENCIJU);
        DBBroker.vratiInstancu().obrisiObjekat(i);
    }
    
}
