/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dugovanja;

import domen.Dugovanja;
import java.util.ArrayList;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class UnosDugovanjaSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        OpstaSO o = new VratiSvaDugovanjaSO();
        o.izvrsiOperaciju(new Dugovanja());
        ArrayList<Dugovanja> ld = (ArrayList<Dugovanja>) o.getObjekat();
        if (ld.isEmpty()) {
            return ;
        }
        Dugovanja d = (Dugovanja) objekat;
        for (Dugovanja dugovanja : ld) {
            if (dugovanja.getSifraDugovanja()==d.getSifraDugovanja()) {
                throw new Exception("Dug sa tom sifrom već postoji!");
            }
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Dugovanja d = (Dugovanja) objekat;
        d.postaviUslov(Konstante.SACUVAJ_DUG);
        DBBroker.vratiInstancu().sacuvajObjekat(d);
    }
    
}
