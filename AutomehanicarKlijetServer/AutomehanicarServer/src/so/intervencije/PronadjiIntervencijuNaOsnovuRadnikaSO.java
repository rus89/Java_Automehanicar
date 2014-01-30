/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.intervencije;

import domen.Intervencija;
import domen.Zaposleni;
import java.util.ArrayList;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class PronadjiIntervencijuNaOsnovuRadnikaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Zaposleni z = (Zaposleni) objekat;
        Intervencija i = new Intervencija();
        i.setZaposleni(z);
        i.postaviUslov(Konstante.PRONADJI_INTERVENCIJU_NA_OSNOVU_RADNIKA);
        ArrayList<Intervencija> li = DBBroker.vratiInstancu().vratiObjektePoUslovu(i);
        if (li.isEmpty()) {
            throw new Exception("Ne postoji tražena intervencija!");
        }
        super.setObjekat(li);
    }
}
