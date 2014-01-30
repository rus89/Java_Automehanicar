/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.intervencije;

import domen.Intervencija;
import java.util.ArrayList;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class SacuvajIntervencijuSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        OpstaSO o = new VratiSveIntervencijeSO();
        o.izvrsiOperaciju(new Intervencija());
        ArrayList<Intervencija> li = (ArrayList<Intervencija>) o.getObjekat();
        if (li.isEmpty()) {
            return ;
        }
        
        Intervencija i = (Intervencija) objekat;
        for (Intervencija intervencija : li) {
            if (intervencija.getSifraIntervencije()==i.getSifraIntervencije()) {
                throw new Exception("Intervencija sa zadatom šiform već postoji!");
            }
            if (intervencija.getAutomobil().getBrojRegistracije().equals(i.getAutomobil().getBrojRegistracije())) {
                throw new Exception("Intervencija nad zadatim automobilom je već definisana!");
            }
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Intervencija i = (Intervencija) objekat;
        i.postaviUslov(Konstante.SACUVAJ_INTERVENCIJU);
        DBBroker.vratiInstancu().sacuvajObjekat(i);
    }
    
}
