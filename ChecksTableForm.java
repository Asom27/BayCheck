/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.check.form;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author T-SBY
 */
public class ChecksTableForm extends AbstractTableModel {
        private ArrayList<Check> checks;
         private String[] columns = {"No.", "Date", "Customer", "Total"};
   

    public ChecksTableForm(ArrayList<Check> checks) {
        this.checks = checks;
    }
        

    @Override
    public int getRowCount() {
         return checks.size();
    }

    @Override
    public int getColumnCount() {
         return columns.length;
    }
        @Override
 public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Check check = checks.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return check.getNum();
            case 1: return check.getDate();
            case 2: return check.getCustomer();
            case 3: return check.getCheckTotal();
            default : return "";
        }
    }
    
}
