/*
 * TableGeneratorDialog.java
 *
 * Displays Table Generator Dialog to allow specification of dive time modifiers
 * and selection of controlling dive segment.
 *
 * Created on 23 November 2005, 21:15
 * @author Guy Wittig
 * @version 04-Mar-2005
 *
 *   This program is part of MV-Plan
 *   Copywrite 2006 Guy Wittig
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   The GNU General Public License can be read at http://www.gnu.org/licenses/licenses.html
 */

package mvplan.gui;

import mvplan.main.*;
import mvplan.dive.TableGeneratorModel;
import mvplan.segments.SegmentAbstract;

import java.awt.Frame;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TableGeneratorDialog extends JDialog implements FocusListener {
    
    private int[] modifiers;    // Dive time modifiers to be returned
    private boolean returnResult;
    private TableGeneratorModel mp;
    
    /** Creates new form TableGeneratorDialog */
    public TableGeneratorDialog(Frame parentFrame, TableGeneratorModel multiProfile) {        
        super(parentFrame,Mvplan.getResource("mvplan.gui.TableGeneratorDialog.title"),true);
        mp=multiProfile;    // Store multiprofile
        
        initComponents();
        //infoLabel.setText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.infoLabel.text"));
        modifiers = mp.getModifiers();        
       
        // Set ESCAPE key to close dialog
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction(){
                public void actionPerformed(ActionEvent e){
                    dispose();
                }
        };        
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE",escapeAction);          
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);
        getRootPane().setDefaultButton(okButton);
        //setVisible(true);        
    }
    public boolean showDialog() {
        int cs = mp.getControlSegmentIndex();
        SegmentAbstract s;
        //String str;
        ArrayList knownSegments, modifiableSegments=new ArrayList();
        
        if (cs>=0) {            
            // New multi segment code
            knownSegments = mp.getKnownSegments();           
            Iterator it=knownSegments.iterator();
            while(it.hasNext()){
                s = (SegmentAbstract)it.next();
                if(s.getTime()>0.0)
                    // Add to arraylist, but reverse order so it comes up on in spinner
                    modifiableSegments.add(0,s);                
            }
            // Set spinner model to array of Segment objects                 
            segmentSpinner.setModel(new SpinnerListModel(modifiableSegments.toArray()));        
            // Tweek spinner text field
            ((JSpinner.DefaultEditor)segmentSpinner.getEditor()).getTextField().setFocusable(false);
            ((JSpinner.DefaultEditor)segmentSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
            if(mp.getControlSegment()!=null)
                segmentSpinner.setValue(mp.getControlSegment());
        } else {
            //infoField.setText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.noSegments.text"));
            segmentSpinner.setModel(new SpinnerListModel(new String [] {Mvplan.getResource("mvplan.gui.TableGeneratorDialog.noSegments.text")})); 
            //segmentSpinner.setEnabled(false);
            okButton.setEnabled(false);
        }
        // Display modifiers array
        modifierField0.setText(String.valueOf(modifiers[0]));
        modifierField1.setText(String.valueOf(modifiers[1]));
        modifierField2.setText(String.valueOf(modifiers[2]));
        modifierField3.setText(String.valueOf(modifiers[3]));
        modifierField4.setText(String.valueOf(modifiers[4]));
        setVisible(true);
        return returnResult;      
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        infoPanel = new javax.swing.JPanel();
        segmentSpinner = new javax.swing.JSpinner();
        inputPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        modifierField0 = new javax.swing.JTextField();
        modifierField1 = new javax.swing.JTextField();
        modifierField1.addFocusListener(this);
        modifierField2 = new javax.swing.JTextField();
        modifierField2.addFocusListener(this);
        modifierField3 = new javax.swing.JTextField();
        modifierField3.addFocusListener(this);
        modifierField4 = new javax.swing.JTextField();
        modifierField4.addFocusListener(this);
        buttonPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("");
        setResizable(false);
        infoPanel.setLayout(new java.awt.GridBagLayout());

        infoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Mvplan.getResource("mvplan.gui.TableGeneratorDialog.segmentLabel.text"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10)));
        infoPanel.setToolTipText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.segmentTip.text"));
        infoPanel.setMinimumSize(new java.awt.Dimension(230, 45));
        infoPanel.setPreferredSize(new java.awt.Dimension(320, 70));
        segmentSpinner.setFont(new java.awt.Font("Tahoma", 0, 12));
        segmentSpinner.setToolTipText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.segmentTip.text"));
        segmentSpinner.setMinimumSize(new java.awt.Dimension(300, 18));
        segmentSpinner.setPreferredSize(new java.awt.Dimension(210, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        infoPanel.add(segmentSpinner, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        getContentPane().add(infoPanel, gridBagConstraints);

        inputPanel.setLayout(new java.awt.GridBagLayout());

        inputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), Mvplan.getResource("mvplan.gui.TableGeneratorDialog.modifierLabel.text"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10)));
        inputPanel.setToolTipText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.modifierTip.text"));
        inputPanel.setPreferredSize(new java.awt.Dimension(270, 70));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 29));
        modifierField0.setColumns(3);
        modifierField0.setEditable(false);
        modifierField0.setText("0");
        modifierField0.setToolTipText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.modifierTip.text"));
        modifierField0.setEnabled(false);
        modifierField0.setFocusable(false);
        jPanel1.add(modifierField0);

        modifierField1.setColumns(3);
        modifierField1.setText("0");
        modifierField1.setToolTipText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.modifierTip.text"));
        jPanel1.add(modifierField1);

        modifierField2.setColumns(3);
        modifierField2.setText("0");
        modifierField2.setToolTipText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.modifierTip.text"));
        jPanel1.add(modifierField2);

        modifierField3.setColumns(3);
        modifierField3.setText("0");
        modifierField3.setToolTipText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.modifierTip.text"));
        jPanel1.add(modifierField3);

        modifierField4.setColumns(3);
        modifierField4.setText("0");
        modifierField4.setToolTipText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.modifierTip.text"));
        jPanel1.add(modifierField4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        inputPanel.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(inputPanel, gridBagConstraints);

        okButton.setText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.okButton.text"));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        buttonPanel.add(okButton);

        cancelButton.setText(Mvplan.getResource("mvplan.gui.TableGeneratorDialog.cancelButton.text"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        buttonPanel.add(cancelButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(buttonPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles focus gained events. Selects all test to make it easier to edit.
     * @param e Focus gained event
     */
    public void focusGained(FocusEvent e) {
        JTextField tf = (JTextField)(e.getComponent());
        tf.selectAll();        
    }
    
    /**
     * Handles focus lost events. Takes no action but is required to complete the interface.
     * @param e Event   
     */
    public void focusLost(FocusEvent e) {}
    
    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // Take modifiers and fill modifier array        
        if(modifiers.length == 5) {
            try {
                // Note that modifiers[] is a reference to an array in the prefs  Object. So this updates it directly
                modifiers[0]=Integer.parseInt(modifierField0.getText());
                modifiers[1]=Integer.parseInt(modifierField1.getText());
                modifiers[2]=Integer.parseInt(modifierField2.getText());
                modifiers[3]=Integer.parseInt(modifierField3.getText());
                modifiers[4]=Integer.parseInt(modifierField4.getText());
            } catch (NumberFormatException e) {
                if(Mvplan.DEBUG>0) System.err.println("MultiDiveDialog: Number format error parsing modifiers.");
            }
        } else {
                if(Mvplan.DEBUG>0) System.err.println("MultiDiveDialog: Array length error.");
        }
        // Check modifiers for invalid entries
        for(int i=0; i<modifiers.length;i++) {
            if( (((SegmentAbstract)segmentSpinner.getValue()).getTime() + modifiers[i]) <= 0.0 ) {
                modifiers[i]=0;
                if(Mvplan.DEBUG>0) System.err.println("TablegeneratorDialog: corrected modifier error.");
            }
        }
        
        if(Mvplan.DEBUG>0) System.out.println("TablegeneratorDialog: selected segment"+segmentSpinner.getValue().toString());
        mp.setControlSegmentIndex(segmentSpinner.getValue());
        returnResult= true;
        setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        returnResult = false;
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField modifierField0;
    private javax.swing.JTextField modifierField1;
    private javax.swing.JTextField modifierField2;
    private javax.swing.JTextField modifierField3;
    private javax.swing.JTextField modifierField4;
    private javax.swing.JButton okButton;
    private javax.swing.JSpinner segmentSpinner;
    // End of variables declaration//GEN-END:variables
    
}
