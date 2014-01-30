/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.opisIntervencije;

import domen.OpisIntervencije;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class VratiSveOpiseIntervencijaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        OpisIntervencije oi = (OpisIntervencije) objekat;
        oi.postaviUslov(Konstante.VRATI_SVE_OPISE_INTERVENCIJE);
        super.setObjekat(DBBroker.vratiInstancu().vratiListuObjekta(oi));
    }
}
