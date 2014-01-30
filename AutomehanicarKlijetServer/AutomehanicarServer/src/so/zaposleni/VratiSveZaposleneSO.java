/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import domen.Zaposleni;
import komunikacija.DBBroker;
import so.OpstaSO;

/**
 *
 * @author Rus
 */
public class VratiSveZaposleneSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Zaposleni z = (Zaposleni) objekat;
        super.setObjekat(DBBroker.vratiInstancu().vratiListuObjekta(z));
    }
}
