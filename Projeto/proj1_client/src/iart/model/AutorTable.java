package iart.model;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class AutorTable extends AbstractTable {
    public AutorTable(final ArrayList<String> paramQuery) {
        super(paramQuery);
    }

    @Override
    protected DefaultTableCellRenderer getRenderer() {
        return new AutorRenderer();
    }

    @Override
    protected AbstractTableModel getModel() {
        return new AutorModel(getResponse());
    }

    @Override
    protected void setWidth(JTable jTable) {
        jTable.getColumnModel().getColumn(0).setPreferredWidth(32);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(32);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(32);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(16);
    }

    @Override
    public String getName() {
        return "A U T O R E S";
    }
}