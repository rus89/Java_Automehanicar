/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.marke;

import domen.MarkaAutomobila;
import komunikacija.DBBroker;
import so.OpstaSO;

/**
 *
 * @author Rus
 */
public class VratiSveMarkeAutomobilaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        MarkaAutomobila ma = (MarkaAutomobila) objekat;
        super.setObjekat(DBBroker.vratiInstancu().vratiListuObjekta(ma));
    }
    
}
