/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vlasnikAutomobila;

import domen.VlasnikAutomobila;
import komunikacija.DBBroker;
import so.OpstaSO;

/**
 *
 * @author Rus
 */
public class VratiSveVlasnikeAutomobilaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        VlasnikAutomobila va = (VlasnikAutomobila) objekat;
        super.setObjekat(DBBroker.vratiInstancu().vratiListuObjekta(va));
    }
    
}
