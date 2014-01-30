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
public class VratiSveAutomobileSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Automobil a = (Automobil) objekat;
        a.postaviUslov(Konstante.VRATI_SVE_AUTOMOBILE);
        super.setObjekat(DBBroker.vratiInstancu().vratiListuObjekta(a));
    }
}
