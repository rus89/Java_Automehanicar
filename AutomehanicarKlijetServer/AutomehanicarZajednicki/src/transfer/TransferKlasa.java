/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Rus
 */
public class TransferKlasa implements Serializable {

    private int operacija;
    private Object klijentObjekat;
    private Object serverObjekatOdgovor;
    private String serverObjekatPoruka;

    public TransferKlasa() {
        serverObjekatPoruka = "";
    }

    /**
     * @return the operacija
     */
    public int getOperacija() {
        return operacija;
    }

    /**
     * @param operacija the operacija to set
     */
    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    /**
     * @return the klijentObjekat
     */
    public Object getKlijentObjekat() {
        return klijentObjekat;
    }

    /**
     * @param klijentObjekat the klijentObjekat to set
     */
    public void setKlijentObjekat(Object klijentObjekat) {
        this.klijentObjekat = klijentObjekat;
    }

    /**
     * @return the serverObjekatOdgovor
     */
    public Object getServerObjekatOdgovor() {
        return serverObjekatOdgovor;
    }

    /**
     * @param serverObjekatOdgovor the serverObjekatOdgovor to set
     */
    public void setServerObjekatOdgovor(Object serverObjekatOdgovor) {
        this.serverObjekatOdgovor = serverObjekatOdgovor;
    }

    /**
     * @return the serverObjekatPoruka
     */
    public String getServerObjekatPoruka() {
        return serverObjekatPoruka;
    }

    /**
     * @param serverObjekatPoruka the serverObjekatPoruka to set
     */
    public void setServerObjekatPoruka(String serverObjekatPoruka) {
        this.serverObjekatPoruka = serverObjekatPoruka;
    }
}
