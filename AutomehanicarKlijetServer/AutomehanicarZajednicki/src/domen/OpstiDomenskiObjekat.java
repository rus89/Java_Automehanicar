/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Rus
 */
public interface OpstiDomenskiObjekat {
    
    public String vratiNazivTabele();
    public ArrayList napuniListuObjekata(ResultSet rs) throws Exception;
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception;
    public void postaviUslov(int uslov);
    public int vratiUslov();
    public String vratiDeoZaINSERT(int i);
    public String vratiVrednostiZaINSERT(int i);
    public String vratiDeoZaSELECT();
    public String vratiDeoZaFROM();
    public String vratiVrednostiZaWHERE();
    public String vratiVrednostiZaSET();
    public boolean primarniKljucJeAUTONUMBER();
    public boolean daLiImaSlabeObjekte();
    public int brojSlabihObjekata();
    public void napuniObjekatSlabimObjektima(ResultSet rs) throws Exception;
        
}
