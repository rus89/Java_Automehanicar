/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dugovanja;

import domen.Dugovanja;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class BrisanjeDugaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Dugovanja d = (Dugovanja) objekat;
        d.postaviUslov(Konstante.OBRISI_DUG);
        DBBroker.vratiInstancu().obrisiObjekat(d);
    }
}
