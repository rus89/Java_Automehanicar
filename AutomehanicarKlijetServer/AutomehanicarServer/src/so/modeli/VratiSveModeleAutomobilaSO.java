/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.modeli;

import domen.ModelAutomobila;
import komunikacija.DBBroker;
import so.OpstaSO;

/**
 *
 * @author Rus
 */
public class VratiSveModeleAutomobilaSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        ModelAutomobila moa = (ModelAutomobila) objekat;
        super.setObjekat(DBBroker.vratiInstancu().vratiListuObjekta(moa));
    }
    
}
