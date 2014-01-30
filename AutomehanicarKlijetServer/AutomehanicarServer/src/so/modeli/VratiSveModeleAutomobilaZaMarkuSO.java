/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.modeli;

import domen.MarkaAutomobila;
import domen.ModelAutomobila;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class VratiSveModeleAutomobilaZaMarkuSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        MarkaAutomobila marka = (MarkaAutomobila) objekat;
        ModelAutomobila model = new ModelAutomobila();
        model.setMarkaAutomobila(marka);
        model.postaviUslov(Konstante.VRATI_SVE_MODELE_ZA_MARKU_AUTOMOBILA);
        super.setObjekat(DBBroker.vratiInstancu().vratiObjektePoUslovu(model));
    }
    
}
