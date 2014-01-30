/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class Dugovanja implements Serializable, OpstiDomenskiObjekat {

    private int sifraDugovanja;
    private double vrednost;
    private VlasnikAutomobila vlasnik;
    private int uslov;

    public Dugovanja() {
    }

    public Dugovanja(int sifraDugovanja, double vrednost, VlasnikAutomobila vlasnik) {
        this.sifraDugovanja = sifraDugovanja;
        this.vrednost = vrednost;
        this.vlasnik = vlasnik;
    }

    /**
     * @return the sifraDugovanja
     */
    public int getSifraDugovanja() {
        return sifraDugovanja;
    }

    /**
     * @param sifraDugovanja the sifraDugovanja to set
     */
    public void setSifraDugovanja(int sifraDugovanja) {
        this.sifraDugovanja = sifraDugovanja;
    }

    /**
     * @return the vrednost
     */
    public double getVrednost() {
        return vrednost;
    }

    /**
     * @param vrednost the vrednost to set
     */
    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    /**
     * @return the vlasnik
     */
    public VlasnikAutomobila getVlasnik() {
        return vlasnik;
    }

    /**
     * @param vlasnik the vlasnik to set
     */
    public void setVlasnik(VlasnikAutomobila vlasnik) {
        this.vlasnik = vlasnik;
    }

    @Override
    public String toString() {
        return vrednost + " " + vlasnik;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dugovanja) {
            Dugovanja d = (Dugovanja) obj;
            if (sifraDugovanja == d.sifraDugovanja) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "Dugovanje";
    }

    @Override
    public ArrayList napuniListuObjekata(ResultSet rs) throws Exception {

        ArrayList<Dugovanja> listaDugovanja = new ArrayList<Dugovanja>();

        while (rs.next()) {

            sifraDugovanja = rs.getInt("SifraDugovanja");
            vrednost = rs.getDouble("VrednostDugovanja");

            String jmbg = rs.getString("JMBG");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            vlasnik = new VlasnikAutomobila(jmbg, ime, prezime);
            Dugovanja d = new Dugovanja(sifraDugovanja, vrednost, vlasnik);
            listaDugovanja.add(d);

        }
        return listaDugovanja;
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
    public String vratiDeoZaSELECT() {
        if (uslov == Konstante.PRONADJI_DUG) {
            return "Dugovanje.SifraDugovanja, Dugovanje.VrednostDugovanja, VlasnikAutomobila.JMBG, VlasnikAutomobila.Ime, VlasnikAutomobila.Prezime";
        }
        return "Dugovanje.SifraDugovanja, Dugovanje.VrednostDugovanja, VlasnikAutomobila.JMBG, VlasnikAutomobila.Ime, VlasnikAutomobila.Prezime";
    }

    @Override
    public String vratiDeoZaINSERT(int i) {
        if (uslov == Konstante.SACUVAJ_DUG) {
            return "Dugovanje ( SifraDugovanja, VrednostDugovanja, JMBGVlasnika )";
        }
        return "";
    }

    @Override
    public boolean primarniKljucJeAUTONUMBER() {
        return true;
    }

    @Override
    public String vratiVrednostiZaINSERT(int i) {
        if (uslov == Konstante.SACUVAJ_DUG) {
            return "( " + sifraDugovanja + ", " + vrednost + ", '" + vlasnik.getJmbg() + "' )";
        }
        return "";
    }

    @Override
    public String vratiDeoZaFROM() {
        if (uslov == Konstante.PRONADJI_DUG) {
            return "VlasnikAutomobila INNER JOIN Dugovanje ON VlasnikAutomobila.JMBG = Dugovanje.JMBGVlasnika";
        }
        return "VlasnikAutomobila INNER JOIN Dugovanje ON VlasnikAutomobila.JMBG = Dugovanje.JMBGVlasnika";
    }

    @Override
    public String vratiVrednostiZaWHERE() {
        if (uslov == Konstante.PRONADJI_DUG) {
            return "VlasnikAutomobila.JMBG='" + vlasnik.getJmbg() + "'";
        }
        if (uslov == Konstante.OBRISI_DUG) {
            return "Dugovanje.SifraDugovanja=" + sifraDugovanja;
        }
        if (uslov == Konstante.OBRISI_SVE_DUGOVE_NEKOG_KORISNIKA) {
            return "Dugovanje.JMBGVlasnika='" + vlasnik.getJmbg() + "'";
        }
        return "";
    }

    @Override
    public String vratiVrednostiZaSET() {
        return "";
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
