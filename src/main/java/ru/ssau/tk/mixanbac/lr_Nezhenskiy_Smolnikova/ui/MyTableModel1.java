package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTableModel1 extends AbstractTableModel {
    private static final int INDEX_COLUMN_NUMBER = 0;
    private static final int X_COLUMN_NUMBER = 1;
    private static final int Y_COLUMN_NUMBER = 2;
    private TabulatedFunction myFunction;

    public MyTableModel1() {
    }

    @Override
    public int getRowCount() {
        return (myFunction == null) ? 0 : myFunction.getCount();
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
                return myFunction.getX(rowIndex);
            case Y_COLUMN_NUMBER:
                return myFunction.getY(rowIndex);
        }
        throw new UnsupportedOperationException();
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) throws NumberFormatException {
        if (columnIndex == Y_COLUMN_NUMBER) {
            try {
                myFunction.setY(rowIndex, Double.parseDouble(aValue.toString()));
            } catch (Exception e) {
                myFunction.setY(rowIndex, 0.0);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
            case X_COLUMN_NUMBER:
                return false;
            case Y_COLUMN_NUMBER:
                return true;
        }
        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case INDEX_COLUMN_NUMBER:
                return "Индекс";
            case X_COLUMN_NUMBER:
                return "X";
            case Y_COLUMN_NUMBER:
                return "Y";
        }
        return super.getColumnName(column);
    }

    public TabulatedFunction getFunction() {
        return myFunction;
    }

    public void setFunction(TabulatedFunction function) {
        this.myFunction = function;
    }
}
