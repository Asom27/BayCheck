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
public class Line {
    
    private String item;
    private double price;
    private int count;
    private Check check;

    public Line() {
    }

    public Line( String item, double price, int count, Check check) {
        this.item = item;
        this.price = price;
        this.count = count;
       this.check = check;
    }

    public Line(int checkNum, String itemName, double itemPrice, int count, Check che) {
   }



    /**
     *
     * @return
     */
    public double getLineTotal(){
    return price*count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

 
    

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Line{" + "num=" + check.getNum()+ ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }
   
     public Check getCheck() {
        return check;
    }
    
   
    public String getAsCSV() {
        return check.getNum() + "," + item + "," + price + "," + count;
    }
     
    
}
