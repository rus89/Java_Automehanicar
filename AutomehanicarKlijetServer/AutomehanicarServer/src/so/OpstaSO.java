/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.sql.SQLException;
import komunikacija.DBBroker;

/**
 *
 * @author Rus
 */
public abstract class OpstaSO {
    
    private Object objekat;

    /**
     * @return the objekat
     */
    public Object getObjekat() {
        return objekat;
    }

    /**
     * @param objekat the objekat to set
     */
    protected void setObjekat(Object objekat) {
        this.objekat = objekat;
    }
    
    public void izvrsiOperaciju(Object objekat) throws Exception{
        
        try {
            
            pokreniTransakciju();
            proveriPreduslov(objekat);
            izvrsiKonkretnuOperaciju(objekat);
            potvrdiTransakciju();
            
        } catch (ClassNotFoundException ex) {
            ponistiTransakciju();
            throw new Exception(ex.getMessage());
        } catch (SQLException ex) {
            ponistiTransakciju();
            throw new Exception(ex.getMessage());
        }
        
    }

    private void pokreniTransakciju() throws Exception {        
        DBBroker.vratiInstancu().pokreniTransakciju();        
    }

    protected abstract void proveriPreduslov(Object objekat) throws Exception;

    protected abstract void izvrsiKonkretnuOperaciju(Object objekat) throws Exception;

    private void potvrdiTransakciju() throws SQLException, ClassNotFoundException {
        DBBroker.vratiInstancu().potvrdiKonekciju();
    }

    private void ponistiTransakciju() throws SQLException, ClassNotFoundException {
        DBBroker.vratiInstancu().ponistikonekciju();
    }
    
}
