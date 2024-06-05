package view.pop_up_view;

import control.SetSubuserAbilityController;
import control.MainController;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.AbstractAction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.Client;
import model.User;

/**
 *
 * @author admin
 */
public class SetSubuserAbilityView extends javax.swing.JFrame {

    private boolean updated;
    private final SetSubuserAbilityController controller;
    private final String sessionKey;

    /**
     * Creates new form modifyClientView
     *
     * @param controller
     * @param sessionKey
     */
    public SetSubuserAbilityView(SetSubuserAbilityController controller, String sessionKey) {
        updated = false;
        this.controller = controller;
        this.sessionKey = sessionKey;

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        subuserTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Habilitar/deshabilitar subusuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        cancelButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 153, 153));
        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("Doble click sobre el subusuario a habilitar/deshabilitar:");

        subuserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Nombre de usuario", "Fecha de creación", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(subuserTable);
        if (subuserTable.getColumnModel().getColumnCount() > 0) {
            subuserTable.getColumnModel().getColumn(0).setResizable(false);
            subuserTable.getColumnModel().getColumn(1).setResizable(false);
            subuserTable.getColumnModel().getColumn(2).setResizable(false);
            subuserTable.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JTable subuserTable;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            subuserTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        try {
                            controller.setAbility();
                        } catch (ClassNotFoundException | SQLException | IOException | ParseException | InterruptedException ex) {
                            Logger.getLogger(SetSubuserAbilityView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    controller.cancelSettingSubuserAbility();
                }
            });
            cancelButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.cancelSettingSubuserAbility();
                    }
                }
            });

            updated = true;
        }
    }

    public int getSelectedSubuser() {
        int selectedSubuser = (Integer) subuserTable.getModel().getValueAt(subuserTable.getSelectedRow(), 0);
        return selectedSubuser;
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        subuserTable.requestFocus();
    }
    
    public void setNewSubuserTableModel(List<User> subusers) {
        MainController.authenticate(sessionKey);
        subuserTable.setModel(getNewResultTableModel(subusers));
        subuserTable.removeColumn(subuserTable.getColumnModel().getColumn(0));
        setSubuserTableModelFormat(subusers);
    }

    private void setSubuserTableModelFormat(List<User> subusers) {
        MainController.authenticate(sessionKey);
        if (subuserTable.getColumnModel().
                getColumnCount()
                > 0) {
            subuserTable.getColumnModel().
                    getColumn(0).
                    setResizable(false);
            subuserTable.getColumnModel().
                    getColumn(1).
                    setResizable(false);
            subuserTable.getColumnModel().
                    getColumn(2).
                    setResizable(false);
            subuserTable.getColumnModel().
                    getColumn(3).
                    setResizable(false);
        }

        subuserTable.getTableHeader().
                setResizingAllowed(false);
        subuserTable.getTableHeader().
                setReorderingAllowed(false);

        TableColumnModel columns;
        columns = subuserTable.getColumnModel();

        for (int i = 0; i
                < columns.getColumnCount(); i++) {
            subuserTable.setDefaultRenderer(subuserTable.
                    getColumnClass(i), new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {

                    if (subusers.get(row).
                            isDisabledSubuser()) {
                        setForeground(new Color(255, 102, 102));
                    } else {
                        setForeground(Color.BLACK);
                    }

                    return super.
                            getTableCellRendererComponent(table, value, isSelected,
                                    hasFocus, row, column);
                }
            });
        }
    }

    private DefaultTableModel getNewResultTableModel(List<User> subusers) {
        MainController.authenticate(sessionKey);
        Object[][] objectMatrix;
        objectMatrix = new Object[subusers.size()][5];

        for (int i = subusers.size() - 1; i
                >= 0 ; i--) {
            objectMatrix[i][0] = subusers.get(i).getId();
            objectMatrix[i][1] = subusers.get(i).getName();
            objectMatrix[i][2] = subusers.get(i).getUsername();
            objectMatrix[i][3] = MainController.parseToViewDate(subusers.get(i).getCreationDate());
            if(subusers.get(i).isDisabledSubuser()) {
                objectMatrix[i][4] = "Deshabilitado";
            }
            else {
                objectMatrix[i][4] = "Habilitado";
            }
        }

        DefaultTableModel model;
        model = new DefaultTableModel(
                objectMatrix,
                new String[]{
                    "Id",
                    "Nombre",
                    "Nombre de usuario",
                    "Fecha de creación",
                    "Estado"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false,
                false,
                false,
                false,
                false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            Class[] types = new Class[]{
                java.lang.Integer.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        return model;
    }
}
