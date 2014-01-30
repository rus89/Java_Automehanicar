/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class Zaposleni implements Serializable, OpstiDomenskiObjekat {

    private int sifraZaposlenog;
    private String jmbg;
    private String ime;
    private String prezime;
    private String telefon;
    private String adresa;
    private String username;
    private String password;
    private String status;
    private boolean ulogovan;
    private int uslov;

    public Zaposleni() {
    }

    public Zaposleni(int sifraZaposlenog, String jmbg, String ime, String prezime, String telefon, String adresa, String username, String password, String status, boolean ulogovan) {
        this.sifraZaposlenog = sifraZaposlenog;
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.adresa = adresa;
        this.username = username;
        this.password = password;
        this.status = status;
        this.ulogovan = ulogovan;
    }

    /**
     * @return the sifraZaposlenog
     */
    public int getSifraZaposlenog() {
        return sifraZaposlenog;
    }

    /**
     * @param sifraZaposlenog the sifraZaposlenog to set
     */
    public void setSifraZaposlenog(int sifraZaposlenog) {
        this.sifraZaposlenog = sifraZaposlenog;
    }

    /**
     * @return the jmbg
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * @param jmbg the jmbg to set
     */
    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    /**
     * @return the ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * @param ime the ime to set
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * @return the prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * @param prezime the prezime to set
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * @return the telefon
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * @param telefon the telefon to set
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * @return the adresa
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * @param adresa the adresa to set
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Zaposleni) {
            Zaposleni zaposleni = (Zaposleni) obj;
            if (sifraZaposlenog == zaposleni.sifraZaposlenog) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "Zaposleni";
    }

    @Override
    public ArrayList napuniListuObjekata(ResultSet rs) throws Exception {

        ArrayList<Zaposleni> listaZaposlenih = new ArrayList<Zaposleni>();

        while (rs.next()) {

            sifraZaposlenog = rs.getInt("SifraZaposlenog");
            jmbg = rs.getString("JMBG");
            ime = rs.getString("Ime");
            prezime = rs.getString("Prezime");
            telefon = rs.getString("Telefon");
            adresa = rs.getString("Adresa");
            username = rs.getString("Username");
            password = rs.getString("Password");
            status = rs.getString("Status");
            ulogovan = rs.getBoolean("Ulogovan");

            Zaposleni z = new Zaposleni(sifraZaposlenog, jmbg, ime, prezime, telefon, adresa, username, password, status, ulogovan);
            listaZaposlenih.add(z);

        }
        return listaZaposlenih;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {

        if (rs.next()) {

            sifraZaposlenog = rs.getInt("SifraZaposlenog");
            jmbg = rs.getString("JMBG");
            ime = rs.getString("Ime");
            prezime = rs.getString("Prezime");
            telefon = rs.getString("Telefon");
            adresa = rs.getString("Adresa");
            username = rs.getString("Username");
            password = rs.getString("Password");
            status = rs.getString("Status");
            ulogovan = rs.getBoolean("Ulogovan");
            Zaposleni z = new Zaposleni(sifraZaposlenog, jmbg, ime, prezime, telefon, adresa, username, password, status, ulogovan);
            return z;

        }
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
        return "SifraZaposlenog, JMBG, Ime, Prezime, Telefon, Adresa, Username, Password, Status, Ulogovan";
    }

    @Override
    public String vratiDeoZaINSERT(int i) {
        return "";
    }

    @Override
    public boolean primarniKljucJeAUTONUMBER() {
        return true;
    }

    @Override
    public String vratiVrednostiZaINSERT(int i) {
        return "";
    }

    @Override
    public String vratiVrednostiZaWHERE() {

        if (uslov == Konstante.VRATI_SVE_RADNIKE) {
            return "Zaposleni.Status='radnik'";
        }
        if (uslov == Konstante.PRONADJI_ZAPOSLENOG_NA_OSNOVU_KORISNICKOG_IMENA) {
            return "Zaposleni.Username='" + username + "'";
        }
        if (uslov == Konstante.ULOGUJ_ZAPOSLENOG) {
            return "Zaposleni.SifraZaposlenog=" + sifraZaposlenog;
        }
        return "";
    }

    @Override
    public String vratiVrednostiZaSET() {
        if (uslov == Konstante.ULOGUJ_ZAPOSLENOG) {
            return "Zaposleni.Ulogovan = " + ulogovan;
        }
        return "";
    }

    @Override
    public String vratiDeoZaFROM() {
        return "Zaposleni";
    }

    /**
     * @return the ulogovan
     */
    public boolean isUlogovan() {
        return ulogovan;
    }

    /**
     * @param ulogovan the ulogovan to set
     */
    public void setUlogovan(boolean ulogovan) {
        this.ulogovan = ulogovan;
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
