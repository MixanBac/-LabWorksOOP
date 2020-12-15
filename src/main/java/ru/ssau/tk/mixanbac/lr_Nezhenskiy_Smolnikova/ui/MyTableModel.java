package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTableModel extends AbstractTableModel {
    private static final int INDEX_COLUMN_NUMBER = 0;
    private static final int X_COLUMN_NUMBER = 1;
    private static final int Y_COLUMN_NUMBER = 2;
    private List<String> xValues;
    private List<String> yValues;

    public MyTableModel(List<String> xValues, List<String> yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    @Override
    public int getRowCount() {
        return xValues.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return rowIndex;
            case X_COLUMN_NUMBER:
                return xValues.get(rowIndex);
            case Y_COLUMN_NUMBER:
                return yValues.get(rowIndex);
        }
        throw new UnsupportedOperationException();
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == X_COLUMN_NUMBER) {
            xValues.set(rowIndex, String.valueOf(aValue));
        } else if (columnIndex == Y_COLUMN_NUMBER) {
            yValues.set(rowIndex, String.valueOf(aValue));
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return false;
            case X_COLUMN_NUMBER:
                return true;
            case Y_COLUMN_NUMBER:
                return true;
        }
        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case INDEX_COLUMN_NUMBER:
                return "Index";
            case X_COLUMN_NUMBER:
                return "xValues";
            case Y_COLUMN_NUMBER:
                return "yValues";
        }
        return super.getColumnName(column);
    }

}
