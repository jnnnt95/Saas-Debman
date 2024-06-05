package view.full_size_view;

import control.DepositClientsOnDateController;
import control.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class DepositClientsOnDateView extends javax.swing.JFrame {

    private boolean updated;
    private final DepositClientsOnDateController controller;
    private final String sessionKey;
    private String date;

    /**
     * Creates new form ClientInfo
     *
     * @param controller
     * @param sessionKey
     */
    public DepositClientsOnDateView(DepositClientsOnDateController controller, String sessionKey) {
        updated = false;
        this.controller = controller;
        this.sessionKey = sessionKey;
        initComponents();
        depositClientsTable.
                setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        descriptionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        depositClientsTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        dateField = new javax.swing.JTextField();
        nameLabelTitle1 = new javax.swing.JLabel();
        removeOnedayButton = new javax.swing.JButton();
        addOneDayButton = new javax.swing.JButton();
        nameLabelTitle2 = new javax.swing.JLabel();
        cashInRegisterLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Depósitos por fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        descriptionLabel.setBackground(new java.awt.Color(0, 0, 0));
        descriptionLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        descriptionLabel.setText("Para el día [fecha] pagaron:");

        depositClientsTable.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        depositClientsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Nick", "Cantidad pagada", "Saldo actual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(depositClientsTable);
        if (depositClientsTable.getColumnModel().getColumnCount() > 0) {
            depositClientsTable.getColumnModel().getColumn(0).setResizable(false);
            depositClientsTable.getColumnModel().getColumn(1).setResizable(false);
            depositClientsTable.getColumnModel().getColumn(2).setResizable(false);
            depositClientsTable.getColumnModel().getColumn(3).setResizable(false);
        }

        dateField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        dateField.setText("fecha");

        nameLabelTitle1.setBackground(new java.awt.Color(0, 0, 0));
        nameLabelTitle1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nameLabelTitle1.setText("Ingresar otra fecha:");

        removeOnedayButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        removeOnedayButton.setText("<");
        removeOnedayButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        addOneDayButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addOneDayButton.setText(">");
        addOneDayButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addOneDayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOneDayButtonActionPerformed(evt);
            }
        });

        nameLabelTitle2.setBackground(new java.awt.Color(0, 0, 0));
        nameLabelTitle2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nameLabelTitle2.setText("Total en caja:");

        cashInRegisterLabel.setBackground(new java.awt.Color(0, 0, 0));
        cashInRegisterLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cashInRegisterLabel.setForeground(new java.awt.Color(0, 153, 0));
        cashInRegisterLabel.setText("$ ##.###.###");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabelTitle1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeOnedayButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addOneDayButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nameLabelTitle2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cashInRegisterLabel))
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 514, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabelTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cashInRegisterLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabelTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeOnedayButton)
                    .addComponent(addOneDayButton))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 982, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addOneDayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOneDayButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addOneDayButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addOneDayButton;
    private javax.swing.JLabel cashInRegisterLabel;
    private javax.swing.JTextField dateField;
    private javax.swing.JTable depositClientsTable;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JLabel nameLabelTitle1;
    private javax.swing.JLabel nameLabelTitle2;
    private javax.swing.JButton removeOnedayButton;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            removeOnedayButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.removeOneDayToDate();
                        controller.setViewData();
                    } catch (ParseException | ClassNotFoundException | SQLException | IOException ex) {
                        Logger.getLogger(DepositClientsOnDateView.class.
                                getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            });
            addOneDayButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.addOneDayToDate();
                        controller.setViewData();
                    } catch (ParseException | ClassNotFoundException | SQLException | IOException ex) {
                        Logger.getLogger(DepositClientsOnDateView.class.
                                getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            });
            dateField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            controller.setViewData();
                            setCashInRegister(MainController.formatAmount(controller.getCashInRegister(MainController.parseToModelDate(getDate()))));
                        } catch (ParseException ex) {
                            Logger.getLogger(DepositClientsOnDateView.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(DepositClientsOnDateView.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(DepositClientsOnDateView.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(DepositClientsOnDateView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });

            updated = true;
        }
    }
    
    public void setCashInRegister(String cashInRegister) {
        cashInRegisterLabel.setText("$ " + cashInRegister);
    }

    public void setToday() {
        controller.setToday();
    }

    public void setInfoData(String date)
            throws ParseException {
        try {
            MainController.authenticate(sessionKey);
            controller.verifyDate(date);
            this.date = date;
            setDepositClientsDescription();
            setDepositClientsTable();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(DepositClientsOnDateView.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private void setDepositClientsDescription() {
        descriptionLabel.setText(
                "<html>"
                + "Para el día <strong>" + getDate() + "</strong>, pagaron los siguientes clientes:"
                + "</html>");
    }

    private void setDepositClientsTable() throws ClassNotFoundException, SQLException, IOException, ParseException {
        MainController.authenticate(sessionKey);
        Object[][] objectMatrix;

        ArrayList<String[]> data = controller.getDepositClients(getDate());

        objectMatrix = new Object[data.size()][4];
        for (int i = 0;
                i
                < data.size();
                i++) {
            objectMatrix[i][0] = "$ " + MainController.formatAmount(Float.
                    parseFloat(data.get(i)[2]));
            objectMatrix[i][1] = data.get(i)[0];
            objectMatrix[i][2] = data.get(i)[1];
            objectMatrix[i][3] = "$ " + MainController.formatAmount(Float.
                    parseFloat(data.get(i)[3]));
        }

        DefaultTableModel model;
        model = new DefaultTableModel(
                objectMatrix,
                new String[]{
                    "Cantidad pagada",
                    "Nombre",
                    "Nick",
                    "Saldo pendiente actual"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false,
                false,
                false,
                false
            };

            @Override
            public boolean isCellEditable(int rowIndex,
                    int columnIndex) {
                return canEdit[columnIndex];
            }

            Class[] types = new Class[]{
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

        depositClientsTable.getTableHeader().
                setResizingAllowed(false);
        depositClientsTable.getTableHeader().
                setReorderingAllowed(false);
        depositClientsTable.setModel(model);

        depositClientsTable.getColumnModel().
                getColumn(2).
                setPreferredWidth(15);
        depositClientsTable.getColumnModel().
                getColumn(3).
                setPreferredWidth(15);

        if (depositClientsTable.getColumnModel().
                getColumnCount()
                > 0) {
            depositClientsTable.getColumnModel().
                    getColumn(0).
                    setResizable(false);
            depositClientsTable.getColumnModel().
                    getColumn(1).
                    setResizable(false);
            depositClientsTable.getColumnModel().
                    getColumn(2).
                    setResizable(false);
            depositClientsTable.getColumnModel().
                    getColumn(3).
                    setResizable(false);
        }
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        removeOnedayButton.requestFocus();
    }

    public void setDate(String date) {
        this.date = date;
        dateField.setText(date);
    }

    public String getDate() {
        return dateField.getText().
                trim();
    }

    public void setFocusOnDate() {
        dateField.selectAll();
        dateField.requestFocus();
    }
}
