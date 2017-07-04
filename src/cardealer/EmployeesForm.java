/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer;

import cardealer.database.Database;
import cardealer.database.EmployeesEntityFacade;
import cardealer.database.GendersEntityFacade;
import cardealer.database.PositionsEntityFacade;
import cardealer.database.exceptions.EntityExistsException;
import cardealer.models.Employee;
import cardealer.models.Gender;
import cardealer.models.Position;
import cardealer.tableModels.EmployeesTableModel;
import java.awt.ContainerOrderFocusTraversalPolicy;
import java.awt.FocusTraversalPolicy;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
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
public class EmployeesForm extends javax.swing.JFrame {

    private FocusTraversalPolicy focusTravelPolicy;
    public static Database db;
    private Properties props;
    private static EmployeesEntityFacade eef;
    private static ArrayList<Employee> mEmployees;
    private static Employee currentEmployee;
    private static final Logger LOG = Logger.getLogger(EmployeesForm.class.getName());
    private boolean carInsertFlag = false;
    private ArrayList<Gender> mGenders;
    private GendersEntityFacade gef;
    private ArrayList<Position> mPositions;
    private PositionsEntityFacade pef;

    /**
     * Creates new form PersonsForm
     */
    public EmployeesForm() {
        initComponents();
        focusTravelPolicy = new ContainerOrderFocusTraversalPolicy();
       
        this.setTitle("Administrim i punëtorëve");
        jTblEmployees.setModel(new EmployeesTableModel());
        try {
            Path workingDirectory = Paths.get("").toAbsolutePath();
            String path = workingDirectory + "\\build\\classes\\cardealer\\config\\config.ini";

            File f = new File(path);
            if (f.exists()) {

                props = new Properties();
                props.load(new FileInputStream(path));
                // TODO code application logic here
                System.out.println("Trying to connect");
                EmployeesForm.db = new Database(props);
                PositionForm.db = EmployeesForm.db;
                System.out.println("Connected");
                eef = new EmployeesEntityFacade();
                mEmployees = new ArrayList<>();
                currentEmployee = new Employee();
                mPositions = new ArrayList<>();

                try {
                    populateEmployees();
                    gef = new GendersEntityFacade();
                    pef = new PositionsEntityFacade();
                    mGenders = gef.reads();
                    mPositions = pef.reads();
                    jCboGenders.removeAllItems();
                    jCboPositions.removeAllItems();
                    mPositions.forEach((position) -> {
                        jCboPositions.addItem(position);
                    });

                    mGenders.forEach((gender) -> {
                        jCboGenders.addItem(gender);
                    });
                    clearEmployeeFields();
                    setEmployeeFieldsEditable(false);
                } catch (EntityExistsException | IllegalStateException | IllegalArgumentException | TransactionRequiredException ex) {
                    Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                placeEmployees();
            }
        } catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        }

        jTblEmployees.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jTblEmployees.getSelectedRowCount() == 1) {
                    jBtnEmployeeEdit.setEnabled(true);
                } else {
                    jBtnEmployeeEdit.setEnabled(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
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

        jPanel3 = new javax.swing.JPanel();
        jBtnEmployeeCreate = new javax.swing.JButton();
        jBtnEmployeeEdit = new javax.swing.JButton();
        jBtnEmployeeSave = new javax.swing.JButton();
        jBtnEmployeeCancel = new javax.swing.JButton();
        jBtnEmployeeRefresh = new javax.swing.JButton();
        jTfEmployeeFirstName = new javax.swing.JTextField();
        jTfEmployeeMiddleName = new javax.swing.JTextField();
        jTfEmployeeLastName = new javax.swing.JTextField();
        jTfEmployeeHiringDate = new javax.swing.JTextField();
        jCboGenders = new javax.swing.JComboBox<Gender>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCboPositions = new javax.swing.JComboBox<Position>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblEmployees = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBtnEmployeeCreate.setText("Shto");
        jBtnEmployeeCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEmployeeCreateActionPerformed(evt);
            }
        });

        jBtnEmployeeEdit.setText("Redakto");
        jBtnEmployeeEdit.setEnabled(false);
        jBtnEmployeeEdit.setNextFocusableComponent(jBtnEmployeeSave);
        jBtnEmployeeEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEmployeeEditActionPerformed(evt);
            }
        });

        jBtnEmployeeSave.setText("Ruaj");
        jBtnEmployeeSave.setEnabled(false);
        jBtnEmployeeSave.setNextFocusableComponent(jBtnEmployeeRefresh);
        jBtnEmployeeSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEmployeeSaveActionPerformed(evt);
            }
        });

        jBtnEmployeeCancel.setText("Anulo");
        jBtnEmployeeCancel.setNextFocusableComponent(jTblEmployees);
        jBtnEmployeeCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEmployeeCancelActionPerformed(evt);
            }
        });

        jBtnEmployeeRefresh.setText("Rifresko");
        jBtnEmployeeRefresh.setNextFocusableComponent(jBtnEmployeeCancel);
        jBtnEmployeeRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEmployeeRefreshActionPerformed(evt);
            }
        });

        jTfEmployeeFirstName.setText("jTextField1");
        jTfEmployeeFirstName.setNextFocusableComponent(jTfEmployeeMiddleName);

        jTfEmployeeMiddleName.setText("jTextField2");
        jTfEmployeeMiddleName.setNextFocusableComponent(jTfEmployeeLastName);

        jTfEmployeeLastName.setText("jTextField3");
        jTfEmployeeLastName.setNextFocusableComponent(jCboGenders);

        jTfEmployeeHiringDate.setEditable(false);
        jTfEmployeeHiringDate.setText("jTextField4");
        jTfEmployeeHiringDate.setNextFocusableComponent(jBtnEmployeeCreate);
 
       
        jCboGenders.setNextFocusableComponent(jTfEmployeeHiringDate);

        jLabel1.setText("Emri:");

        jLabel2.setText("Emri i mesëm:");

        jLabel3.setText("Mbiemri:");

        jLabel4.setText("Gjinia:");

        jLabel5.setText("Data e punësimit:");

        
        jCboPositions.setNextFocusableComponent(jTfEmployeeHiringDate);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jBtnEmployeeRefresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnEmployeeCancel))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jBtnEmployeeCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBtnEmployeeEdit)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnEmployeeSave))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTfEmployeeHiringDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTfEmployeeFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTfEmployeeMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTfEmployeeLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCboGenders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCboPositions, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jCboGenders, jTfEmployeeFirstName, jTfEmployeeHiringDate, jTfEmployeeLastName, jTfEmployeeMiddleName});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfEmployeeFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jCboPositions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfEmployeeMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfEmployeeLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboGenders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfEmployeeHiringDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEmployeeCreate)
                    .addComponent(jBtnEmployeeEdit)
                    .addComponent(jBtnEmployeeSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEmployeeRefresh)
                    .addComponent(jBtnEmployeeCancel))
                .addGap(129, 129, 129))
        );

        jTblEmployees.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTblEmployees);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnEmployeeCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEmployeeCreateActionPerformed
        // TODO add your handling code here:
        clearEmployeeFields();
        setEmployeeFieldsEditable(true);
        carInsertFlag = true;
        //jTFEmployeeMake.requestFocus();
        jBtnEmployeeCreate.setEnabled(false);
        jBtnEmployeeSave.setEnabled(true);
    }//GEN-LAST:event_jBtnEmployeeCreateActionPerformed

    private void jBtnEmployeeEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEmployeeEditActionPerformed
        // TODO add your handling code here:
        if (jTblEmployees.getSelectedRowCount() == 1) {
            int index = jTblEmployees.getSelectedRow();
            EmployeesTableModel d = ((EmployeesTableModel) jTblEmployees.getModel());
            currentEmployee = d.getSelectedRow(index);
            placeEmployeeObjectToFields();
            setEmployeeFieldsEditable(true);
            jBtnEmployeeCreate.setEnabled(false);
            jBtnEmployeeSave.setEnabled(true);
            jBtnEmployeeEdit.setEnabled(false);
            carInsertFlag = false;
        }
    }//GEN-LAST:event_jBtnEmployeeEditActionPerformed

    private void jBtnEmployeeSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEmployeeSaveActionPerformed
        // TODO add your handling code here:
        populateEmployeeObject();

        boolean res = false;
        try {
            if (carInsertFlag) {
                res = eef.create( currentEmployee);

            } else {
                res = eef.update(currentEmployee);
            }
            setEmployeeFieldsEditable(false);
            clearEmployeeFields();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (res) {
            try {
                jBtnEmployeeCreate.setEnabled(true);
                jBtnEmployeeEdit.setEnabled(false);
                jBtnEmployeeSave.setEnabled(false);
                populateEmployees();
                placeEmployees();
                if (jCboPositions.getSelectedIndex() > -1) {
                    
                }
            } catch (SQLException | EntityExistsException | IllegalStateException | IllegalArgumentException | TransactionRequiredException ex) {
                Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jBtnEmployeeSaveActionPerformed

    private void jBtnEmployeeCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEmployeeCancelActionPerformed
        jBtnEmployeeCreate.setEnabled(true);
        jBtnEmployeeEdit.setEnabled(false);
        jBtnEmployeeSave.setEnabled(false);
        clearEmployeeFields();
        setEmployeeFieldsEditable(false);
    }//GEN-LAST:event_jBtnEmployeeCancelActionPerformed

    private void jBtnEmployeeRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEmployeeRefreshActionPerformed
        try {
            // TODO add your handling code here:
            populateEmployees();
            placeEmployees();
            jBtnEmployeeCreate.setEnabled(true);
            jBtnEmployeeEdit.setEnabled(false);
            jBtnEmployeeSave.setEnabled(false);
            clearEmployeeFields();
            setEmployeeFieldsEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EntityExistsException ex) {
            Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransactionRequiredException ex) {
            Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnEmployeeRefreshActionPerformed

     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEmployeeCancel;
    private javax.swing.JButton jBtnEmployeeCreate;
    private javax.swing.JButton jBtnEmployeeEdit;
    private javax.swing.JButton jBtnEmployeeRefresh;
    private javax.swing.JButton jBtnEmployeeSave;
    private javax.swing.JComboBox<Gender> jCboGenders;
    private javax.swing.JComboBox<Position> jCboPositions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTblEmployees;
    private javax.swing.JTextField jTfEmployeeFirstName;
    private javax.swing.JTextField jTfEmployeeHiringDate;
    private javax.swing.JTextField jTfEmployeeLastName;
    private javax.swing.JTextField jTfEmployeeMiddleName;
    // End of variables declaration//GEN-END:variables

    private void populateEmployees() throws SQLException, EntityExistsException,
            IllegalStateException, IllegalStateException,
            IllegalArgumentException, IllegalArgumentException,
            TransactionRequiredException {
        mEmployees = eef.reads();
    }

    private void placeEmployees() {
        DefaultTableModel tableModel = (DefaultTableModel) jTblEmployees.getModel();
        tableModel.getDataVector().clear();
        Vector v = new Vector();
        for (Employee c : mEmployees) {
            v = new Vector();
            v.add(c.getIdEmployee());
            v.add(c.getFirstName());
            v.add(c.getMiddleName());
            v.add(c.getLastName());
            v.add(c.getGenderID().getTitle());
            v.add(c.getHiringDate());

            tableModel.addRow(v);

        }
        jTblEmployees.setModel(tableModel);
    }

    private void clearEmployeeFields() {
        jTfEmployeeFirstName.setText("");
        jTfEmployeeMiddleName.setText("");
        jTfEmployeeLastName.setText("");
        jCboGenders.setSelectedIndex(0);
        jTfEmployeeHiringDate.setText("");
    }

    private void setEmployeeFieldsEditable(boolean b) {
        jTfEmployeeFirstName.setEditable(b);
        jTfEmployeeMiddleName.setEditable(b);
        jTfEmployeeLastName.setEditable(b);
        jCboGenders.setEnabled(b);

    }

    private void placeEmployeeObjectToFields() {
        jTfEmployeeFirstName.setText(currentEmployee.getFirstName());
        jTfEmployeeMiddleName.setText(currentEmployee.getMiddleName());
        jTfEmployeeLastName.setText(currentEmployee.getLastName());
        jCboGenders.setSelectedItem(currentEmployee.getGenderID());
        jTfEmployeeHiringDate.setText(currentEmployee.getHiringDate().toString());
    }

    private void populateEmployeeObject() {
        currentEmployee.setFirstName(jTfEmployeeFirstName.getText());
        currentEmployee.setMiddleName(jTfEmployeeMiddleName.getText());
        currentEmployee.setLastName(jTfEmployeeLastName.getText());
        currentEmployee.setGenderID((Gender) jCboGenders.getSelectedItem());
    }
}
