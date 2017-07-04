/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer;

import cardealer.database.Database;
import cardealer.database.exceptions.EntityExistsException;

import cardealer.database.PositionsEntityFacade;
import cardealer.models.Position;
import cardealer.tableModels.PositionTableModel;
import cardealer.utils.Convert;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.transaction.TransactionRequiredException;

/**
 *
 * @author ahmet
 */
public class PositionForm extends javax.swing.JFrame {

    public static Database db;
    private SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat sdfMysql = new SimpleDateFormat("yyyy-mm-dd");
    private PositionsEntityFacade pef;

    private boolean employeeInsertFlag;
    private Position currentPosition;
    private ArrayList<Position> mPositions;

    /**
     * Creates new form PositionForm
     */
    public PositionForm() {
        initComponents();

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        this.setTitle("Regjistrimi i pozitave");
        jTblPositions.setModel(new PositionTableModel());
        try {
            Path workingDirectory = Paths.get("").toAbsolutePath();
            String path = workingDirectory + "\\build\\classes\\cardealer\\config\\config.ini";
            final String dir = System.getProperty("user.dir");

            File f = new File(path);
            if (f.exists()) {
                System.out.println("current dir = " + dir);
                Properties props = new Properties();
                props.load(new FileInputStream(path));
                // TODO code application logic here
                System.out.println("Trying to connect");
                PositionForm.db = new Database(props);
                System.out.println("Connected");
                pef = new PositionsEntityFacade();
                mPositions = new ArrayList<>();
                currentPosition = new Position();
                populatePositions();
                placePositions();
                clearPositionFields();
                setPositionFieldsEditable(false);
            }
        } catch (EntityExistsException | IllegalStateException | IllegalArgumentException | TransactionRequiredException ex) {
            Logger.getLogger(PositionForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(PositionForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTblPositions = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTADescription = new javax.swing.JTextArea();
        jTfTitle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jBtnPositionCreate = new javax.swing.JButton();
        jBtnPositionRefresh = new javax.swing.JButton();
        jBtnPositionCancel = new javax.swing.JButton();
        jBtnPositionEdit = new javax.swing.JButton();
        jBtnPositionSave = new javax.swing.JButton();
        jTfStartDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTfEndDate = new javax.swing.JTextField();
        jChkBStatus = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTblPositions.setAutoCreateRowSorter(true);
        jTblPositions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTblPositions.setToolTipText("Lista e pozitave në Kompani");
        jTblPositions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblPositionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblPositions);

        jLabel1.setText("Emërtimi:");

        jTADescription.setColumns(20);
        jTADescription.setRows(5);
        jTADescription.setWrapStyleWord(true);
        jTADescription.setNextFocusableComponent(jTfEndDate);
        jScrollPane2.setViewportView(jTADescription);

        jTfTitle.setNextFocusableComponent(jTADescription);

        jLabel2.setText("Përshkrimi:");

        jBtnPositionCreate.setText("Shto");
        jBtnPositionCreate.setNextFocusableComponent(jBtnPositionEdit);
        jBtnPositionCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPositionCreateActionPerformed(evt);
            }
        });

        jBtnPositionRefresh.setText("Rifresko");
        jBtnPositionRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPositionRefreshActionPerformed(evt);
            }
        });

        jBtnPositionCancel.setText("Anulo");
        jBtnPositionCancel.setNextFocusableComponent(jTblPositions);
        jBtnPositionCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPositionCancelActionPerformed(evt);
            }
        });

        jBtnPositionEdit.setText("Redakto");
        jBtnPositionEdit.setEnabled(false);
        jBtnPositionEdit.setNextFocusableComponent(jBtnPositionEdit);
        jBtnPositionEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPositionEditActionPerformed(evt);
            }
        });

        jBtnPositionSave.setText("Ruaj");
        jBtnPositionSave.setEnabled(false);
        jBtnPositionSave.setNextFocusableComponent(jBtnPositionRefresh);
        jBtnPositionSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPositionSaveActionPerformed(evt);
            }
        });

        jTfStartDate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTfStartDate.setNextFocusableComponent(jTfEndDate);

        jLabel3.setText("Data e fillimit:");

        jLabel4.setText("Data e mbarimi:");

        jTfEndDate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTfEndDate.setNextFocusableComponent(jBtnPositionCreate);

        jChkBStatus.setText("Jo");
        jChkBStatus.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jChkBStatusStateChanged(evt);
            }
        });

        jLabel5.setText("Statusi:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTfStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBtnPositionRefresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnPositionCancel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBtnPositionCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBtnPositionEdit)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnPositionSave)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jChkBStatus))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jChkBStatus)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTfStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTfEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnPositionCreate)
                    .addComponent(jBtnPositionEdit)
                    .addComponent(jBtnPositionSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnPositionRefresh)
                    .addComponent(jBtnPositionCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jChkBStatusStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jChkBStatusStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jChkBStatusStateChanged

    private void jBtnPositionSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPositionSaveActionPerformed
        // TODO add your handling code here:
        populatePositionObject();

        boolean res = false;
        try {
            if (employeeInsertFlag) {
                res = pef.create(currentPosition);

            } else {
                res = pef.update(currentPosition);
            }
            setPositionFieldsEditable(false);
            clearPositionFields();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (res) {
            try {
                jBtnPositionCreate.setEnabled(true);
                jBtnPositionEdit.setEnabled(false);
                jBtnPositionSave.setEnabled(false);
                populatePositions();
                placePositions();

            } catch (SQLException | EntityExistsException | IllegalStateException | IllegalArgumentException | TransactionRequiredException ex) {
                Logger.getLogger(PositionForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jBtnPositionSaveActionPerformed

    private void jBtnPositionEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPositionEditActionPerformed
        // TODO add your handling code here:
        if (jTblPositions.getSelectedRowCount() == 1) {
            int index = jTblPositions.getSelectedRow();
            PositionTableModel d = ((PositionTableModel) jTblPositions.getModel());
            currentPosition = d.getSelectedRow(index);
            placePositionObjectToFields();
            setPositionFieldsEditable(true);
            jBtnPositionCreate.setEnabled(false);
            jBtnPositionSave.setEnabled(true);
            jBtnPositionEdit.setEnabled(false);
            employeeInsertFlag = false;
        }
    }//GEN-LAST:event_jBtnPositionEditActionPerformed

    private void jBtnPositionCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPositionCancelActionPerformed
        jBtnPositionCreate.setEnabled(true);
        jBtnPositionEdit.setEnabled(false);
        jBtnPositionSave.setEnabled(false);
        clearPositionFields();
        setPositionFieldsEditable(false);
    }//GEN-LAST:event_jBtnPositionCancelActionPerformed

    private void jBtnPositionRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPositionRefreshActionPerformed
        try {
            // TODO add your handling code here:
            populatePositions();
            placePositions();
            jBtnPositionCreate.setEnabled(true);
            jBtnPositionEdit.setEnabled(false);
            jBtnPositionSave.setEnabled(false);
            clearPositionFields();
            setPositionFieldsEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(PositionForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EntityExistsException ex) {
            Logger.getLogger(PositionForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(PositionForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PositionForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransactionRequiredException ex) {
            Logger.getLogger(PositionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnPositionRefreshActionPerformed

    private void jBtnPositionCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPositionCreateActionPerformed
        // TODO add your handling code here:
        clearPositionFields();
        jTfTitle.requestFocus();
        setPositionFieldsEditable(true);
        employeeInsertFlag = true;
        //jTFPositionMake.requestFocus();
        jBtnPositionCreate.setEnabled(false);
        jBtnPositionSave.setEnabled(true);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
        jTfStartDate.setText(dtf.format(localDate));
        
    }//GEN-LAST:event_jBtnPositionCreateActionPerformed

    private void jTblPositionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblPositionsMouseClicked
        if (jTblPositions.getSelectedRowCount() == 1) {
            jBtnPositionEdit.setEnabled(true);
            currentPosition = ((PositionTableModel) jTblPositions.getModel()).getSelectedRow(jTblPositions.getSelectedRow());
            placePositionObjectToFields();
        } else {
            jBtnPositionEdit.setEnabled(false);
        }
    }//GEN-LAST:event_jTblPositionsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnPositionCancel;
    private javax.swing.JButton jBtnPositionCreate;
    private javax.swing.JButton jBtnPositionEdit;
    private javax.swing.JButton jBtnPositionRefresh;
    private javax.swing.JButton jBtnPositionSave;
    private javax.swing.JCheckBox jChkBStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTADescription;
    private javax.swing.JTable jTblPositions;
    private javax.swing.JTextField jTfEndDate;
    private javax.swing.JTextField jTfStartDate;
    private javax.swing.JTextField jTfTitle;
    // End of variables declaration//GEN-END:variables

    private void clearPositionFields() {
        jTfTitle.setText("");
        jTADescription.setText("");
        jChkBStatus.setSelected(false);
        jTfStartDate.setText("");
        jTfEndDate.setText("");
    }

    private void setPositionFieldsEditable(boolean b) {
        jTfTitle.setEditable(b);
        jTADescription.setEditable(b);
        jChkBStatus.setEnabled(b);
        jTfStartDate.setEditable(b);
        jTfEndDate.setEditable(b);
    }

    private void populatePositions() throws EntityExistsException,
            IllegalStateException, IllegalArgumentException,
            TransactionRequiredException, SQLException {
        mPositions = pef.reads();
    }

    private void placePositions() {
        DefaultTableModel tableModel = (DefaultTableModel) jTblPositions.getModel();
        tableModel.getDataVector().clear();
        Vector v = new Vector();
        for (Position c : mPositions) {
            v = new Vector();
            v.add(c.getIdPosition());
            v.add(c.getTitle());
            v.add(c.getDescription());
            v.add(c.isStatus());
            v.add(sdf1.format(c.getStartDate()));
            v.add(c.getEndDate() != null ? c.getEndDate().toString() : "E pacaktuar");

            tableModel.addRow(v);

        }
        jTblPositions.setModel(tableModel);
    }

    private void placePositionObjectToFields() {
        this.jTfTitle.setText(currentPosition.getTitle());
        this.jTADescription.setText(currentPosition.getDescription());
        this.jChkBStatus.setSelected(currentPosition.isStatus());
        this.jChkBStatus.setText(currentPosition.isStatus() ? "Po" : "Jo");
        this.jTfStartDate.setText(Convert.toSqlDate(currentPosition.getStartDate().toString()));
        this.jTfEndDate.setText(currentPosition.getEndDate() != null
                ? Convert.toSqlDate(currentPosition.getEndDate().toString()) : "E pacaktuar");
    }

    private void populatePositionObject() {
        currentPosition.setTitle(this.jTfTitle.getText());
        currentPosition.setDescription(this.jTADescription.getText());
        currentPosition.setStatus(this.jChkBStatus.isSelected());
        currentPosition.setStartDate(Date.valueOf(Convert.toMysql(jTfStartDate.getText())));
        if (!jTfEndDate.getText().equals("E pacaktuar")) {
            try {
                Date d = Date.valueOf(Convert.toMysql(jTfEndDate.getText()));
                currentPosition.setEndDate(d);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }
}