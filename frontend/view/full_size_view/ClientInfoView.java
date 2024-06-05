package view.full_size_view;

import control.ClientInfoController;
import control.MainController;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.Debt;
import model.IO.Writer;
import model.enums.OperationCode;
import model.Deposit;

/**
 *
 * @author admin
 */
public class ClientInfoView extends javax.swing.JFrame {

    private boolean updated;
    private boolean loginUpdated;
    private final ClientInfoController controller;
    private final String sessionKey;
    private boolean showAllDebts;

    /**
     * Creates new form ClientInfo
     *
     * @param controller
     * @param sessionKey
     */
    public ClientInfoView(ClientInfoController controller, String sessionKey) {
        updated = false;
        loginUpdated = false;
        showAllDebts = false;
        this.controller = controller;
        this.sessionKey = sessionKey;
        initComponents();
        debtsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        depositsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        nameLabelTitle = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nickLabelTitle = new javax.swing.JLabel();
        nickLabel = new javax.swing.JLabel();
        balanceLabelTitle = new javax.swing.JLabel();
        notPaidBalanceLabel = new javax.swing.JLabel();
        payButton = new javax.swing.JButton();
        addDebtButton = new javax.swing.JButton();
        toggleDebtListButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        debtsTable = new javax.swing.JTable();
        defaultAmountTitleLabel = new javax.swing.JLabel();
        notPaidBalanceOnDefaultLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        modifyClientButton = new javax.swing.JButton();
        viewDetailedHistoryButton = new javax.swing.JButton();
        nickLabelTitle1 = new javax.swing.JLabel();
        cpNumberLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        disableClientButton = new javax.swing.JButton();
        nickLabelTitle2 = new javax.swing.JLabel();
        interestRateLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        depositsTable = new javax.swing.JTable();
        depositTitle = new javax.swing.JLabel();
        nickLabelTitle3 = new javax.swing.JLabel();
        interestRateOnDefaultLabel = new javax.swing.JLabel();
        nickLabelTitle4 = new javax.swing.JLabel();
        commentaryLabel = new javax.swing.JLabel();

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
        mainContainer.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        nameLabelTitle.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nameLabelTitle.setForeground(new java.awt.Color(102, 102, 102));
        nameLabelTitle.setText("Nombre:");

        nameLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nameLabel.setText("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

        nickLabelTitle.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabelTitle.setForeground(new java.awt.Color(102, 102, 102));
        nickLabelTitle.setText("Nick:");

        nickLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabel.setText("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

        balanceLabelTitle.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        balanceLabelTitle.setForeground(new java.awt.Color(102, 102, 102));
        balanceLabelTitle.setText("Total adeudado");

        notPaidBalanceLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        notPaidBalanceLabel.setForeground(new java.awt.Color(255, 153, 153));
        notPaidBalanceLabel.setText("$ ##.###.###.###");

        payButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        payButton.setForeground(new java.awt.Color(102, 204, 255));
        payButton.setText("Realizar Depósito");
        payButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        addDebtButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addDebtButton.setText("Agregar deuda");
        addDebtButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        toggleDebtListButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        toggleDebtListButton.setText("Deudas vigentes:");
        toggleDebtListButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        debtsTable.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        debtsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Monto", "Con intereses", "Abonado", "Creada", "Pagada", "Registrada por"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(debtsTable);
        if (debtsTable.getColumnModel().getColumnCount() > 0) {
            debtsTable.getColumnModel().getColumn(0).setResizable(false);
            debtsTable.getColumnModel().getColumn(1).setResizable(false);
            debtsTable.getColumnModel().getColumn(2).setResizable(false);
            debtsTable.getColumnModel().getColumn(3).setResizable(false);
            debtsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            debtsTable.getColumnModel().getColumn(4).setResizable(false);
            debtsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            debtsTable.getColumnModel().getColumn(5).setResizable(false);
        }

        defaultAmountTitleLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        defaultAmountTitleLabel.setForeground(new java.awt.Color(102, 102, 102));
        defaultAmountTitleLabel.setText("Total en mora:");

        notPaidBalanceOnDefaultLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        notPaidBalanceOnDefaultLabel.setForeground(new java.awt.Color(255, 0, 51));
        notPaidBalanceOnDefaultLabel.setText("$ ##.###.###.###");

        modifyClientButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        modifyClientButton.setText("Modificar cliente");
        modifyClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        viewDetailedHistoryButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        viewDetailedHistoryButton.setForeground(new java.awt.Color(67, 106, 137));
        viewDetailedHistoryButton.setText("Ver estadísticas de cliente");
        viewDetailedHistoryButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        nickLabelTitle1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabelTitle1.setForeground(new java.awt.Color(102, 102, 102));
        nickLabelTitle1.setText("Teléfono:");

        cpNumberLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cpNumberLabel.setText("+++");

        disableClientButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        disableClientButton.setForeground(new java.awt.Color(255, 153, 153));
        disableClientButton.setText("Deshabilitar cliente");
        disableClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        nickLabelTitle2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabelTitle2.setForeground(new java.awt.Color(102, 102, 102));
        nickLabelTitle2.setText("Tasa de interés:");

        interestRateLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        interestRateLabel.setText("+++");

        depositsTable.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        depositsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Monto", "Fecha", "Recibido por"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(depositsTable);
        if (depositsTable.getColumnModel().getColumnCount() > 0) {
            depositsTable.getColumnModel().getColumn(0).setResizable(false);
            depositsTable.getColumnModel().getColumn(1).setResizable(false);
            depositsTable.getColumnModel().getColumn(2).setResizable(false);
            depositsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        depositTitle.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        depositTitle.setText("Depósitos:");

        nickLabelTitle3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabelTitle3.setForeground(new java.awt.Color(102, 102, 102));
        nickLabelTitle3.setText("Tasa de interés en mora:");

        interestRateOnDefaultLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        interestRateOnDefaultLabel.setForeground(new java.awt.Color(255, 0, 0));
        interestRateOnDefaultLabel.setText("+++");

        nickLabelTitle4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabelTitle4.setForeground(new java.awt.Color(102, 102, 102));
        nickLabelTitle4.setText("Comentario:");

        commentaryLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        commentaryLabel.setText("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(viewDetailedHistoryButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(balanceLabelTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(notPaidBalanceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(defaultAmountTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(notPaidBalanceOnDefaultLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modifyClientButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disableClientButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(toggleDebtListButton)
                                .addGap(0, 552, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(depositTitle)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nickLabelTitle2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(interestRateLabel)
                                .addGap(18, 18, 18)
                                .addComponent(nickLabelTitle3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(interestRateOnDefaultLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nickLabelTitle1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpNumberLabel)
                                .addGap(18, 18, 18)
                                .addComponent(nickLabelTitle4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(commentaryLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nameLabelTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(nickLabelTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nickLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(payButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addDebtButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel)
                            .addComponent(nickLabelTitle)
                            .addComponent(nickLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nickLabelTitle4)
                                .addComponent(commentaryLabel))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nickLabelTitle1)
                                .addComponent(cpNumberLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nickLabelTitle2)
                            .addComponent(interestRateLabel)
                            .addComponent(nickLabelTitle3)
                            .addComponent(interestRateOnDefaultLabel)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(payButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addDebtButton)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(depositTitle)
                            .addComponent(toggleDebtListButton))
                        .addGap(3, 3, 3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(disableClientButton)
                    .addComponent(modifyClientButton)
                    .addComponent(viewDetailedHistoryButton)
                    .addComponent(balanceLabelTitle)
                    .addComponent(notPaidBalanceLabel)
                    .addComponent(defaultAmountTitleLabel)
                    .addComponent(notPaidBalanceOnDefaultLabel))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainContainerLayout.createSequentialGroup()
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
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDebtButton;
    private javax.swing.JLabel balanceLabelTitle;
    public javax.swing.JLabel commentaryLabel;
    public javax.swing.JLabel cpNumberLabel;
    private javax.swing.JTable debtsTable;
    public javax.swing.JLabel defaultAmountTitleLabel;
    private javax.swing.JLabel depositTitle;
    private javax.swing.JTable depositsTable;
    private javax.swing.JButton disableClientButton;
    public javax.swing.JLabel interestRateLabel;
    public javax.swing.JLabel interestRateOnDefaultLabel;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JButton modifyClientButton;
    public javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabelTitle;
    public javax.swing.JLabel nickLabel;
    private javax.swing.JLabel nickLabelTitle;
    private javax.swing.JLabel nickLabelTitle1;
    private javax.swing.JLabel nickLabelTitle2;
    private javax.swing.JLabel nickLabelTitle3;
    private javax.swing.JLabel nickLabelTitle4;
    public javax.swing.JLabel notPaidBalanceLabel;
    public javax.swing.JLabel notPaidBalanceOnDefaultLabel;
    private javax.swing.JButton payButton;
    private javax.swing.JButton toggleDebtListButton;
    private javax.swing.JButton viewDetailedHistoryButton;
    // End of variables declaration//GEN-END:variables
    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            modifyClientButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!controller.getCurrentClient().isDisabled()) {
                        MainController.changeToModifyClientMode(sessionKey, controller.getCurrentClient());
                    }
                    else {
                        MainController.alert("Cliente deshabilitado", "El cliente actual se encuentra deshabilida, no es posible realizar modificaciones", "Aceptar");
                    }
                }
            });
            viewDetailedHistoryButton.
                    addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                MainController.
                                        changeToDetailedHistoryMode(
                                                controller.getCurrentClient(),
                                                sessionKey
                                        );
                            } catch (ParseException ex) {
                                Logger.getLogger(ClientInfoController.class.
                                        getName()).
                                        log(Level.SEVERE,
                                                null,
                                                ex);
                            } catch (IOException ex) {
                                Logger.getLogger(ClientInfoView.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

            toggleDebtListButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleTable();
                }
            });
            payButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if (!controller.getCurrentClient().isDisabled()) {
                        try {
                            if (controller.getCurrentClient().
                                    getTotalNotPaidBalance()
                                    > 0) {
                                MainController.changeToPerformDepositMode(controller.
                                                getCurrentClient(),
                                                sessionKey);
                                MainController.
                                        executeOperation(OperationCode.updateQueryClientData,
                                                sessionKey);
                            } else {
                                MainController.notify("Cliente a paz y salvo", "No se encontraron deudas por pagar del cliente actual", "Aceptar");
                            }
                        } catch (IOException | ClassNotFoundException | SQLException | ParseException | InterruptedException ex) {
                            Logger.getLogger(ClientInfoController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    } else {
                        MainController.alert("Cliente deshabilitado", "El cliente actual está deshabilitado y no es posible realizar depósitos", "Aceptar");
                    }
                }
            });
            addDebtButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if(!controller.getCurrentClient().isDisabled()) {
                        if (controller.getCurrentClient().
                                isDefaulter()) {
                            MainController.alert("Cliente en mora", "Este cliente se encuentra en estado de mora", "Aceptar");
                        }
                        try {
                            MainController.changeToAddDebtMode(controller.
                                    getCurrentClient(),
                                    sessionKey);
                        } catch (IOException | ParseException ex) {
                            Logger.getLogger(ClientInfoView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else {
                        MainController.alert("Cliente deshabilitado", "El cliente actual se encuentra deshabilitado y no es posible agregar deudas", "Aceptar");
                    }
                }
            });
            debtsTable.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        try {
                            controller.voidDebt((Integer) debtsTable.getModel().getValueAt(debtsTable.getSelectedRow(), 0));
                        } catch (IOException | ParseException ex) {
                            Logger.getLogger(ClientInfoView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            depositsTable.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        try {
                            controller.voidDeposit((Integer) depositsTable.getModel().getValueAt(depositsTable.getSelectedRow(), 0));
                        } catch (IOException | ParseException ex) {
                            Logger.getLogger(ClientInfoView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            updated = true;
        }
    }

    public void toggleTable() {
        try {
            setInfoData(1);
        } catch (ParseException ex) {
            Logger.getLogger(ClientInfoController.class.
                    getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientInfoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setToggleButtonText() {
        if (showAllDebts) {
            toggleDebtListButton.setText("Deudas históricas:");
        } else {
            toggleDebtListButton.setText("Deudas vigentes:");
        }
    }

    private void setInfoData(int option) throws ParseException, IOException {
        MainController.authenticate(sessionKey);
        //change from external 0: change table info to only active debts
        //cange from internal 1 (or other): change table info to previous selection
        switch (option) {
            case 0:
                showAllDebts = false;
                break;
            default:
                showAllDebts = !showAllDebts;
                break;
        }
        setToggleButtonText();
        controller.getCurrentClient().setDebts();
        controller.getCurrentClient().updateDefaultAmount();
        nameLabel.setText(controller.getCurrentClient().getName());
        nickLabel.setText(controller.getCurrentClient().getNick());
        commentaryLabel.setText(controller.getCurrentClient().getCommentary());
        if (!controller.getCurrentClient().getPhone().equals("")) {
            cpNumberLabel.setText(controller.getCurrentClient().getPhone());
        } else {
            cpNumberLabel.setText("<No registra>");
        }
        interestRateLabel.setText(String.valueOf(controller.getCurrentClient().getInterestRate()) + " %");
        interestRateOnDefaultLabel.setText(String.valueOf(controller.getCurrentClient().getInterestRateOnDefault()) + " %");
        notPaidBalanceLabel.setText("$" + MainController.formatAmount(controller.getCurrentClient().getTotalNotPaidBalance()));
        if (controller.getCurrentClient().isDefaulter()) {
            notPaidBalanceOnDefaultLabel.setVisible(true);
            defaultAmountTitleLabel.setVisible(true);
            notPaidBalanceOnDefaultLabel.setText("$" + MainController.formatAmount(controller.getCurrentClient().getDefaultAmount()));
        } else {
            notPaidBalanceOnDefaultLabel.setVisible(false);
            defaultAmountTitleLabel.setVisible(false);
        }
        setDebtsTable();
        setDepositsTable();
    }

    public void setInfoData()
            throws ParseException, IOException {
        setInfoData(0);
    }

    private void setDepositsTable() throws IOException, ParseException {
        MainController.authenticate(sessionKey);
        Object[][] objectMatrix;
        List<Deposit> deposits;
        deposits = controller.getCurrentClient().getDeposits();

        objectMatrix = new Object[deposits.size()][4];
        int depositSize = deposits.size();
        for (int i = 0; i < depositSize; i++) {
            objectMatrix[i][0] = deposits.get(i).getId();
            objectMatrix[i][1] = "$ " + MainController.formatAmount(deposits.get(depositSize - (i + 1)).getAmount());
            objectMatrix[i][2] = MainController.parseToViewDate(deposits.get(depositSize - (i + 1)).getReceptionDate());
            objectMatrix[i][3] = deposits.get(depositSize - (i + 1)).getReceiverName();
        }

        DefaultTableModel model;
        model = new DefaultTableModel(
                objectMatrix,
                new String[]{
                    "id",
                    "Monto",
                    "Fecha",
                    "Recibido por"
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
                java.lang.Integer.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        depositsTable.getTableHeader().setResizingAllowed(false);
        depositsTable.getTableHeader().setReorderingAllowed(false);
        depositsTable.setModel(model);

        depositsTable.getColumnModel().getColumn(1).setPreferredWidth(40);

        if (depositsTable.getColumnModel().getColumnCount() > 0) {
            depositsTable.getColumnModel().getColumn(0).setResizable(false);
            depositsTable.getColumnModel().getColumn(1).setResizable(false);
            depositsTable.getColumnModel().getColumn(2).setResizable(false);
        }

        depositsTable.removeColumn(depositsTable.getColumnModel().getColumn(0));

        TableColumnModel columns;
        columns = depositsTable.getColumnModel();

        for (int i = 0; i < columns.getColumnCount(); i++) {
            depositsTable.setDefaultRenderer(depositsTable.getColumnClass(i), new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table,
                        Object value,
                        boolean isSelected,
                        boolean hasFocus,
                        int row,
                        int column) {

                    //deposits.get(deposits.size() - (row + 1)).isVoid()
                    if (deposits.get(row).isVoid()) {
                        setForeground(new Color(204, 153, 0));
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

    private void setDebtsTable() throws IOException, ParseException {
        MainController.authenticate(sessionKey);
        Object[][] objectMatrix;
        List<Debt> debts;
        debts = new ArrayList<>();

        for (int i = 0; i < controller.getCurrentClient().getDebts().size(); i++) {
            if (showAllDebts) {
                debts.add(controller.getCurrentClient().getDebts().get(i));
            } else if (!controller.getCurrentClient().getDebts().get(i).isPaid()) {
                if (!controller.getCurrentClient().getDebts().get(i).isVoid()) {
                    debts.add(controller.getCurrentClient().getDebts().get(i));
                }
            }
        }

        objectMatrix = new Object[debts.size()][7];
        int debtsSize = debts.size();
        for (int i = 0; i < debtsSize; i++) {
            objectMatrix[i][0] = debts.get(debtsSize - (i + 1)).getId();
            objectMatrix[i][1] = "$ " + MainController.formatAmount(debts.get(debtsSize - (i + 1)).getAmount());
            objectMatrix[i][2] = "$ " + MainController.formatAmount(debts.get(debtsSize - (i + 1)).getAmountPlusInterest());
            objectMatrix[i][3] = "$ " + MainController.formatAmount(debts.get(debtsSize - (i + 1)).getDeposit());
            objectMatrix[i][4] = MainController.parseToViewDate(debts.get(debtsSize - (i + 1)).getCreationDate());
            if (!debts.get(debtsSize - (i + 1)).isPaid()) {
                objectMatrix[i][5] = "No pagada";
            } else if (debts.get(debtsSize - (i + 1)).isPaid() && (debts.get(debtsSize - (i + 1)).getPaidDate() == null)) {
                objectMatrix[i][5] = "Pagada v.a. (no fecha)";
            } else {
                objectMatrix[i][5] = MainController.parseToViewDate(debts.get(debtsSize - (i + 1)).getPaidDate());
            }
            if (debts.get(debtsSize - (i + 1)).isVoid()) {
                objectMatrix[i][5] = "Anulada";
            }
            objectMatrix[i][6] = debts.get(debtsSize - (i + 1)).getCreatedBy();
        }

        DefaultTableModel model;
        model = new DefaultTableModel(
                objectMatrix,
                new String[]{
                    "id",
                    "Monto",
                    "Con intereses",
                    "Abonado",
                    "Creada",
                    "Pagada",
                    "Registrada por"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false,
                false,
                false,
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

        debtsTable.getTableHeader().setResizingAllowed(false);
        debtsTable.getTableHeader().setReorderingAllowed(false);
        debtsTable.setModel(model);

        debtsTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        debtsTable.getColumnModel().getColumn(4).setPreferredWidth(30);

        if (debtsTable.getColumnModel().getColumnCount() > 0) {
            debtsTable.getColumnModel().getColumn(0).setResizable(false);
            debtsTable.getColumnModel().getColumn(1).setResizable(false);
            debtsTable.getColumnModel().getColumn(2).setResizable(false);
            debtsTable.getColumnModel().getColumn(3).setResizable(false);
            debtsTable.getColumnModel().getColumn(4).setResizable(false);
            debtsTable.getColumnModel().getColumn(5).setResizable(false);
            debtsTable.getColumnModel().getColumn(6).setResizable(false);
        }

        TableColumnModel columns;
        columns = debtsTable.getColumnModel();

        for (int i = 0; i < columns.getColumnCount(); i++) {
            debtsTable.setDefaultRenderer(debtsTable.getColumnClass(i), new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table,
                        Object value,
                        boolean isSelected,
                        boolean hasFocus,
                        int row,
                        int column) {

                    if (debts.get(debts.size() - (row + 1)).isInDefault()) {
                        setForeground(new Color(255, 102, 102));
                    } else if (debts.get(debts.size() - (row + 1)).isPaid()) {
                        setForeground(new Color(48, 138, 36));
                    } else if (debts.get(debts.size() - (row + 1)).isVoid()) {
                        setForeground(new Color(204, 153, 0));
                    } else {
                        setForeground(Color.BLACK);
                    }

                    return super.
                            getTableCellRendererComponent(table, value, isSelected,
                                    hasFocus, row, column);
                }
            });
        }

        debtsTable.removeColumn(debtsTable.getColumnModel().getColumn(0));
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        payButton.requestFocus();
    }

    public void loginUpdate() {
        if (!loginUpdated) {
            switch (MainController.getUser().
                    getType()) {
                case servicesUser:
                    disableClientButton.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!controller.getCurrentClient().isDisabled()) {
                                try {
                                    int selection;
                                    selection = MainController.confirm("Confirmar", "El cliente actual será deshabilitado, ¿desea continuar?", "Deshabilitar cliente", "Cancelar");
                                    switch (selection) {
                                        case 0:
                                            Writer.disableClient(controller.getCurrentClient());
                                            MainController.notify("Operación exitosa", "Cliente deshabilitado exitosamente", "Aceptar");
                                            controller.disableClient();
                                            MainController.changeToQueryClientMode(sessionKey);
                                            break;
                                        case 1:
                                        case 2:
                                            break;
                                    }
                                } catch (ClassNotFoundException | SQLException | InterruptedException | IOException | ParseException ex) {
                                    Logger.getLogger(ClientInfoController.class.
                                            getName()).
                                            log(Level.SEVERE,
                                                    null,
                                                    ex);
                                }
                            } else {
                                MainController.alert("Error", "El cliente actual ya se encuentra deshabilitado", "Aceptar");
                            }
                        }
                    });
                    break;
                case servicesSubuser:
                    disableClientButton.setVisible(false);
                    break;
            }
            loginUpdated = false;
        }
    }
}
