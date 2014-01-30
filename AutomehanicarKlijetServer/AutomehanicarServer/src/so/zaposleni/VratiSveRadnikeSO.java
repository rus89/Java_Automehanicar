/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import domen.Zaposleni;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class VratiSveRadnikeSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Zaposleni z = (Zaposleni) objekat;
        z.postaviUslov(Konstante.VRATI_SVE_RADNIKE);
        super.setObjekat(DBBroker.vratiInstancu().vratiObjektePoUslovu(z));
    }
}
