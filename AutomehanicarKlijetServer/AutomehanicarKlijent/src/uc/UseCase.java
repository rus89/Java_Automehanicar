/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uc;

import java.util.HashMap;

/**
 *
 * @author Rus
 */
public class UseCase {

    private static UseCase uc;
    private HashMap<String, Object> mapa;

    private UseCase() {
        mapa = new HashMap<String, Object>();
    }

    public static UseCase vratiUC() {
        if (uc == null) {
            uc = new UseCase();
        }
        return uc;
    }

    public void ubaciDetaljeUMapu(String kljuc, Object vrednost) {
        mapa.put(kljuc, vrednost);
    }

    public Object vratiVrednost(String string) {
        return mapa.get(string);
    }
}
