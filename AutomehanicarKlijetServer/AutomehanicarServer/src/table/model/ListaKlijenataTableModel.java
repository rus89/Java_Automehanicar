/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.Zaposleni;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rus
 */
public class ListaKlijenataTableModel extends AbstractTableModel {

    ArrayList<Zaposleni> listaKlijenata;

    public ListaKlijenataTableModel(ArrayList<Zaposleni> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
    }   
    
    @Override
    public int getRowCount() {
        return listaKlijenata.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaposleni z = listaKlijenata.get(rowIndex);
        switch (columnIndex) {
            case 0: return z.getUsername();
            case 1: return z.getIme();
            case 2: return z.getPrezime();
            case 3: if (z.isUlogovan()) {
                    return new ImageIcon("slike/ok16.png");
                }else{
                return new ImageIcon("slike/no16.png");
            }
            default:return "Greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Korisničko ime";
            case 1: return "Ime";
            case 2: return "Prezime";
            case 3: return "Ulogovan";
            default: return "Greska";                
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return getValueAt(3, columnIndex).getClass();
            default:
                throw new AssertionError();
        }
    }
    
}
