/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.modeli.intervencija;

import domen.Intervencija;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rus
 */
public class UradjeneIntervencijeTableModel extends AbstractTableModel{

    private Intervencija i;

    public UradjeneIntervencijeTableModel(Intervencija i) {
        this.i = i;
    }
    
    @Override
    public int getRowCount() {
        return i.getListaUradjenih().size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return i.getListaUradjenih().get(rowIndex);
            default: return "Greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Opis";
            default: return "Greska";                
        }
    }
    
}
