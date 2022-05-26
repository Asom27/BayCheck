/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.check.display;


import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class CheckDialog extends JDialog {
    private JTextField custNameField;
    private JTextField cheDateField;
    private JLabel custNameLbl;
    private JLabel cheDateLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public CheckDialog(CheckJFrame frame) {
        custNameLbl = new JLabel("Customer Name:");
        custNameField = new JTextField(20);
       cheDateLbl = new JLabel("Check Date:");
       cheDateField = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("createCheckOK");
        cancelBtn.setActionCommand("createCheckCancel");
        
        okBtn.addActionListener(frame.getMonitor());
        cancelBtn.addActionListener(frame.getMonitor());
        setLayout(new GridLayout(3, 2));
        
        add(cheDateLbl);
        add(cheDateField);
        add(custNameLbl);
        add(custNameField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getCustNameField() {
        return custNameField;
    }

    public JTextField getCheDateField() {
        return cheDateField;
    }
    
}
