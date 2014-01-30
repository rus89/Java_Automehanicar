/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.modeli.intervencija;

import domen.Intervencija;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rus
 */
public class IntervencijaPrikazTableModel extends AbstractTableModel{
    
    ArrayList<Intervencija> listaIntervencije;

    public IntervencijaPrikazTableModel(ArrayList<Intervencija> listaIntervencije) {
        this.listaIntervencije = listaIntervencije;        
    }  
    
    @Override
    public int getRowCount() {
        return listaIntervencije.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Intervencija i = listaIntervencije.get(rowIndex);
        switch (columnIndex) {
            case 0: return i.getSifraIntervencije();
            case 1: return i.getZaposleni();
            case 2: return i.getAutomobil();
            case 3: 
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                return sdf.format(i.getDatumIntervencije());          
            default: return "Greska";
        }        
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Sifra intervencije";
            case 1: return "Radnik";
            case 2: return "Automobil";
            case 3: return "Datum intervencije";
            default: return "Greska";
        }        
    }
}
