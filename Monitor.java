/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.check.monitor;



import com.check.display.CheckDialog;
import com.check.display.CheckJFrame;
import com.check.display.LineDialog;
import com.check.form.Check;
import com.check.form.ChecksTableForm;
import com.check.form.Line;
import com.check.form.LinesTableForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;






/**
 *
 * @author T-SBY
 */
public class Monitor implements ActionListener,ListSelectionListener {
    private CheckJFrame frame;
    
    private CheckDialog checkDialog;
    private LineDialog lineDialog;

public Monitor(CheckJFrame frame){
this.frame = frame; }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    String actionCommand = e.getActionCommand();
        System.out.println("Action: " + actionCommand);
        
        switch (actionCommand) {
            case "Load File":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
            case "Create New Check":
                createNewCheck();
                break;
            case "Delete Check":
                deleteCheck();
                break;
            case "Create New Item":
                createNewItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
               
            case "createCheckCancel":
                createCheckCancel();
                break;
            case "createCheckOK":
                createCheckOK();
                break;
            case "createLineOK":
                createLineOK();
                break;
            case "createLineCancel":
                createLineCancel();
                break;
        
                
                
                
                
        }}

      @Override
    public void valueChanged(ListSelectionEvent e) {
             int selectedIndex = frame.getCheckjTable1().getSelectedRow();
              if (selectedIndex != -1) {
        System.out.println("You have selected row: " + selectedIndex);
       Check currentCheck = frame.getChecks().get(selectedIndex);
        frame.getCheckNumjLabel1().setText(""+currentCheck.getNum());
        frame.getCheckDatejLabel2().setText(currentCheck.getDate());
        frame.getCustomerNamejLabel3().setText(currentCheck.getCustomer());
        frame.getCheckTotaljLabel4().setText(""+currentCheck.getCheckTotal());
        LinesTableForm linesTableForm = new LinesTableForm(currentCheck.getLines());
        frame.getLinejTable2().setModel(linesTableForm);
        linesTableForm.fireTableDataChanged();}
    

   
    }
  private void loadFile() {
        JFileChooser fc = new JFileChooser();
        
        try {
           int result = fc.showOpenDialog(frame);
           if (result == JFileChooser.APPROVE_OPTION) {
               File headerFile = fc.getSelectedFile();
               Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                      
                       
                System.out.println("Check have been read");
                // 2,20-10-2022,Ahmed
                // 1,15-11-2020,Sara
                // 2,08-02-2019,Israa
       
 ArrayList<Check> checksArray = new ArrayList<>();

 for (String headerLine : headerLines) {
                   String[] headerParts = null;
           //         String[] headerParts = headerLine.split(",");
    int checkNum = Integer.parseInt(headerParts[0]);
    String checkDate = headerParts[1];
    String customerName = headerParts[2];
     Check check = new Check(checkNum, checkDate, customerName);
                   checksArray.add(check);
                }     
        
         System.out.println("Check point");
         
        
              result = fc.showOpenDialog(frame);
               if(result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    System.out.println("Lines have been read");
                    for (String lineLine : lineLines) {
                       String lineParts[] = lineLine.split(",");
                       int checkNum = Integer.parseInt(lineParts[0]);
                        String itemName = lineParts[1];
                        double itemPrice = Double.parseDouble(lineParts[2]);
                        int count = Integer.parseInt(lineParts[3]);
                       Check che = null;
                        for (Check check : checksArray) {
                           if (check.getNum() == checkNum) {
                               che = check;
                              break;
                           }
                        }
                                              
                        
                       Line line = new Line(checkNum, itemName, itemPrice, count, che);
                       che.getLines().add(line);
                   }
                   System.out.println("Check point");
                }
                
               frame.setChecks(checksArray);
                
               ChecksTableForm checksTableForm = new ChecksTableForm(checksArray);
               frame.setChecksTableForm(checksTableForm);
                frame.getCheckjTable1().setModel(checksTableForm);
                
                frame.getChecksTableForm().fireTableDataChanged();
            }
        } catch (IOException ex) {
           ex.printStackTrace();
}
    }

    private void deleteItem() {
                int selectedChe = frame.getCheckjTable1().getSelectedRow();

        
        int selectedRow = frame.getLinejTable2().getSelectedRow();

        if (selectedChe  != -1 && selectedRow !=-1) {
            Check check = frame.getChecks().get(selectedChe);
            check.getLines().remove(selectedRow);
           LinesTableForm linesTableForm=new LinesTableForm(check.getLines());
           frame.getLinejTable2().setModel(linesTableForm);
           linesTableForm.fireTableDataChanged();
    }}

    private void saveFile() {
 ArrayList<Check> checks = frame.getChecks();
        String headers = "";
        String lines = "";
        for (Check check : checks) {
            String cheCSV = check.getAsCSV();
            headers += cheCSV;
            headers += "\n";

            for (Line line : check.getLines()) {
                String lineCSV = line.getAsCSV();
                lines += lineCSV;
                lines += "\n";
            }
        }
        System.out.println("Check point");
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                hfw.write(headers);
                hfw.flush();
                hfw.close();
                result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();
                }
            }
        } catch (Exception ex) {

        }    }

    private void createNewCheck() {
        checkDialog = new CheckDialog(frame);
        checkDialog.setVisible(true);
    }
    

    private void deleteCheck() {
        
        int selectedRow = frame.getCheckjTable1().getSelectedRow();
        if (selectedRow != -1) {
            frame.getChecks().remove(selectedRow);
            frame.getChecksTableForm().fireTableDataChanged();
        
    }}

    private void createNewItem() {
LineDialog lineDialog = new LineDialog(frame);
        lineDialog.setVisible(true);
        }

    private void createCheckCancel() {
 checkDialog.setVisible(false);
        checkDialog.dispose();
        checkDialog = null;
        }

    private void createCheckOK() {
String date = checkDialog.getCheDateField().getText();
        String customer = checkDialog.getCustNameField().getText();
        int num = frame.getNextCheckNum();

       Check check = new Check(num, date, customer);
        frame.getChecks().add(check);
        frame.getChecksTableForm().fireTableDataChanged();
        checkDialog.setVisible(false);
        checkDialog.dispose();
        checkDialog = null;
        }

    private void createLineOK() {
  String item = lineDialog.getItemNameField().getText();
        String countStr = lineDialog.getItemCountField().getText();
        String priceStr = lineDialog.getItemPriceField().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
      int selectedCheck = frame.getCheckjTable1().getSelectedRow();
       if (selectedCheck != -1) {
          Check check = frame.getChecks().get(selectedCheck);
          Line line = new Line(item, price, count, check);
          check.getLines().add(line);
           LinesTableForm linesTableForm = (LinesTableForm) frame.getLinejTable2().getModel();
          // linesTableForm.getLines().add(line);
           linesTableForm.fireTableDataChanged();
            frame.getChecksTableForm().fireTableDataChanged();}
        
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
}

    private void createLineCancel() {
 lineDialog.setVisible(false);
       lineDialog.dispose();
        lineDialog = null;
            }

    
}