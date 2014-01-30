/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Rus
 */
public class OpisIntervencije implements Serializable, OpstiDomenskiObjekat {

    private int sifraOpisa;
    private String opis;
    private int uslov;

    public OpisIntervencije() {
    }

    public OpisIntervencije(int sifraOpisa, String opis) {
        this.sifraOpisa = sifraOpisa;
        this.opis = opis;
    }

    /**
     * @return the sifraOpisa
     */
    public int getSifraOpisa() {
        return sifraOpisa;
    }

    /**
     * @param sifraOpisa the sifraOpisa to set
     */
    public void setSifraOpisa(int sifraOpisa) {
        this.sifraOpisa = sifraOpisa;
    }

    /**
     * @return the opis
     */
    public String getOpis() {
        return opis;
    }

    /**
     * @param opis the opis to set
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return opis;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OpisIntervencije) {
            OpisIntervencije oi = (OpisIntervencije) obj;
            if (getSifraOpisa() == oi.getSifraOpisa()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "OpisIntervencije";
    }

    @Override
    public ArrayList napuniListuObjekata(ResultSet rs) throws Exception {
        ArrayList<OpisIntervencije> listaOpisa = new ArrayList<OpisIntervencije>();
        while (rs.next()) {
            setSifraOpisa(rs.getInt("SifraOpisa"));
            opis = rs.getString("Opis");
            OpisIntervencije oi = new OpisIntervencije(getSifraOpisa(), opis);
            listaOpisa.add(oi);
        }
        return listaOpisa;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public void postaviUslov(int uslov) {
        this.uslov = uslov;
    }

    @Override
    public int vratiUslov() {
        return uslov;
    }

    @Override
    public String vratiDeoZaINSERT(int i) {
        return "";
    }

    @Override
    public String vratiVrednostiZaINSERT(int i) {
        return "";
    }

    @Override
    public String vratiDeoZaSELECT() {
        return "OpisIntervencije.SifraOpisa, OpisIntervencije.Opis";
    }

    @Override
    public String vratiDeoZaFROM() {
        return "OpisIntervencije";
    }

    @Override
    public String vratiVrednostiZaWHERE() {
        return "";
    }

    @Override
    public String vratiVrednostiZaSET() {
        return "";
    }

    @Override
    public boolean primarniKljucJeAUTONUMBER() {
        return true;
    }

    @Override
    public boolean daLiImaSlabeObjekte() {
        return false;
    }

    @Override
    public int brojSlabihObjekata() {
        return 0;
    }

    @Override
    public void napuniObjekatSlabimObjektima(ResultSet tabela) throws Exception {
        
    }
}
