/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.check.form;

/**
 *
 * @author T-SBY
 */
import java.util.ArrayList;
public class Check {
     private int num;
    private String date;
    private String customer;
    private ArrayList<Line> lines;
    
    
    public Check() {
    }

    public Check(int num, String date, String customer) {
        this.num = num;
        this.date = date;
        this.customer = customer;
    }
public double getCheckTotal() {
        double total = 0.0;
        for (Line line : getLines()) {
            total += line.getLineTotal();
        }
        return total;
    }
   
    public ArrayList<Line> getLines() {
    
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Check{" + "num=" + num + ", date=" + date + ", customer=" + customer + '}';
    }
    
  
    public String getAsCSV() {
        return num + "," + date + "," + customer;
    }
      
    
    
    
    
}
