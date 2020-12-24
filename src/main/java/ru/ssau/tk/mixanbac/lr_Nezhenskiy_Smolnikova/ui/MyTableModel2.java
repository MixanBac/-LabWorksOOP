package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;

import javax.swing.table.AbstractTableModel;

public class MyTableModel2 extends AbstractTableModel {
    private static final int INDEX_COLUMN_NUMBER = 0;
    private static final int X_COLUMN_NUMBER = 1;
    private static final int Y_COLUMN_NUMBER = 2;
    private static final long serialVersionUID = -7781866963434445365L;
    TabulatedFunction func;

    public MyTableModel2(TabulatedFunction func) {
        this.func = func;
    }

    @Override
    public int getRowCount() {
        return func.getCount();
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
                return func.getX(rowIndex);
            case Y_COLUMN_NUMBER:
                return func.getY(rowIndex);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return false;
            case X_COLUMN_NUMBER:
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
                return "Значение X";
            case Y_COLUMN_NUMBER:
                return "Значение Y";
        }
        return super.getColumnName(column);
    }
}