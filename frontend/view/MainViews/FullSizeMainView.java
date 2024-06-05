package view.MainViews;

import control.MainController;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.IO.Reader;
import model.License;
import view.full_size_view.ClientInfoView;
import view.full_size_view.DepositClientsOnDateView;
import view.full_size_view.DetailedHistoryView;
import view.full_size_view.QueryClientView;

public final class FullSizeMainView
        extends javax.swing.JFrame {

    private String userType;
    private boolean updated;
    private final String sessionKey;
    private PopUpMainView popUpSizeViewport;

    public FullSizeMainView(String sessionKey) {
        updated = false;
        this.sessionKey = sessionKey;

        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Debtor Manager");
        ImageIcon img = new ImageIcon("Images/icon.png");
        setIconImage(img.getImage());
    }

    public void setUserName(String username) {
        MainController.authenticate(sessionKey);
        setTitle("Debtor Manager <Beta> | "
                + userType
                + ": "
                + username);
    }

    public void setInstantiationUserType(String userType,
            String username) {
        this.userType = userType;
        MainController.authenticate(sessionKey);
        setTitle("Debtor Manager <Beta> | "
                + userType
                + ": "
                + username
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        queryClientsMenuItem = new javax.swing.JMenuItem();
        createClientMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        changePersonalnfoMenuItem = new javax.swing.JMenuItem();
        changePasswordMenuItem = new javax.swing.JMenuItem();
        createSubuserMenuItem = new javax.swing.JMenuItem();
        setSubuserHabilityMenuItem = new javax.swing.JMenuItem();
        generalSettingsMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        todaysTotalDepositsMenuItem = new javax.swing.JMenuItem();
        depositClientsMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        businessMenuItem = new javax.swing.JMenuItem();
        userInfoMenuItem = new javax.swing.JMenuItem();
        associatedUsersMenuItem = new javax.swing.JMenuItem();
        licenseMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Default content placeholder");

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jLabel1)
                .addContainerGap(587, Short.MAX_VALUE))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel1)
                .addContainerGap(468, Short.MAX_VALUE))
        );

        FileMenu.setText("Opciones");
        FileMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        queryClientsMenuItem.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        queryClientsMenuItem.setText("Consultar clientes");
        queryClientsMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        queryClientsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryClientsMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(queryClientsMenuItem);

        createClientMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        createClientMenuItem.setText("Crear cliente");
        createClientMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FileMenu.add(createClientMenuItem);

        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FileMenu.add(jSeparator1);

        changePersonalnfoMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        changePersonalnfoMenuItem.setText("Cambiar información personal");
        changePersonalnfoMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FileMenu.add(changePersonalnfoMenuItem);

        changePasswordMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        changePasswordMenuItem.setText("Cambiar contraseña");
        changePasswordMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FileMenu.add(changePasswordMenuItem);

        createSubuserMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        createSubuserMenuItem.setText("Crear subusuario");
        createSubuserMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FileMenu.add(createSubuserMenuItem);

        setSubuserHabilityMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        setSubuserHabilityMenuItem.setText("Habilitar/Deshabilitar subusuario");
        setSubuserHabilityMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FileMenu.add(setSubuserHabilityMenuItem);

        generalSettingsMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        generalSettingsMenuItem.setText("Configuración general");
        generalSettingsMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FileMenu.add(generalSettingsMenuItem);
        FileMenu.add(jSeparator4);

        exitMenuItem.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        exitMenuItem.setText("Salir");
        exitMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FileMenu.add(exitMenuItem);

        MenuBar.add(FileMenu);

        jMenu1.setText("Información");

        todaysTotalDepositsMenuItem.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        todaysTotalDepositsMenuItem.setText("Depósitos de hoy en caja");
        todaysTotalDepositsMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.add(todaysTotalDepositsMenuItem);

        depositClientsMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        depositClientsMenuItem.setText("Depósitos por fecha");
        depositClientsMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.add(depositClientsMenuItem);
        jMenu1.add(jSeparator2);

        businessMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        businessMenuItem.setText("Negocio");
        businessMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.add(businessMenuItem);

        userInfoMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        userInfoMenuItem.setText("Usuario");
        userInfoMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.add(userInfoMenuItem);

        associatedUsersMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        associatedUsersMenuItem.setText("Usuarios asociados");
        associatedUsersMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.add(associatedUsersMenuItem);

        licenseMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        licenseMenuItem.setText("Licencia");
        licenseMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.add(licenseMenuItem);
        jMenu1.add(jSeparator3);

        aboutMenuItem.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        aboutMenuItem.setText("About...");
        aboutMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.add(aboutMenuItem);

        MenuBar.add(jMenu1);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void queryClientsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryClientsMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_queryClientsMenuItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenu FileMenu;
    public javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem associatedUsersMenuItem;
    private javax.swing.JMenuItem businessMenuItem;
    private javax.swing.JMenuItem changePasswordMenuItem;
    private javax.swing.JMenuItem changePersonalnfoMenuItem;
    public javax.swing.JPanel container;
    private javax.swing.JMenuItem createClientMenuItem;
    private javax.swing.JMenuItem createSubuserMenuItem;
    private javax.swing.JMenuItem depositClientsMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem generalSettingsMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem licenseMenuItem;
    private javax.swing.JMenuItem queryClientsMenuItem;
    private javax.swing.JMenuItem setSubuserHabilityMenuItem;
    private javax.swing.JMenuItem todaysTotalDepositsMenuItem;
    private javax.swing.JMenuItem userInfoMenuItem;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            //Options menu
            queryClientsMenuItem.addActionListener(
                    new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        MainController.changeToQueryClientMode(sessionKey);
                    } catch (InterruptedException
                            | IOException
                            | ParseException
                            | ClassNotFoundException
                            | SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }

            });
            exitMenuItem.addActionListener(
                    new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int option;
                    option = MainController.confirm("Confirmar", "¿Salir de Debtor Manager?", "Salir", "Cancelar");
                    switch (option) {
                        case 0:
                            System.exit(0);
                            break;
                        default:
                            break;
                    }
                }
            });
            createClientMenuItem.addActionListener(
                    new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.changeToAddClientMode(sessionKey);
                }
            });
            changePersonalnfoMenuItem.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.changeToModifyPersonalInfoMode(sessionKey);
                }
            });
            changePasswordMenuItem.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.changeToModifyPasswordMode(sessionKey);
                }
            });
            //Information menu
            aboutMenuItem.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showAbout();
                }
            });
            userInfoMenuItem.addActionListener(
                    new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String message = Reader.getUserInfo(MainController.getUser().getId());
                        if(message != null) {
                            MainController.notify("Información de usuario", message, "Aceptar");
                        }
                        else {
                            MainController.alert("Operación cancelada", "Se canceló la operación de consultar usuario", "Aceptar");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FullSizeMainView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            depositClientsMenuItem.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        MainController.changeToDepositClientsOnDateMode(sessionKey);
                    } catch (ParseException | ClassNotFoundException | SQLException | IOException ex) {
                        Logger.getLogger(FullSizeMainView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            });
            todaysTotalDepositsMenuItem.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String today;
                        today = MainController.getTodayDateToString();
                        int totalInRegister;
                        totalInRegister = Reader.cashInRegister(today);

                        if (totalInRegister >= 0) {
                            String amount;
                            amount = "$ " + MainController.formatAmount(totalInRegister);
                            String message;
                            message = "Total depósitos del " + MainController.parseToViewDate(today) + ":\n\n" + amount + "\n\nAplica para los depósitos no anulados.";
                            MainController.notify("Depósitos de hoy", message, "Aceptar");
                        }
                        else {
                            MainController.alert("Operación cancelada", "Se canceló la operación de consultar depósitos de hoy", "Aceptar");
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(FullSizeMainView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            associatedUsersMenuItem.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String message;
                        message = Reader.getAssociatedUsers();
                        if(message != null) {
                            MainController.notify("Información de usuario asociados", message, "Aceptar");
                        }
                        else {
                            MainController.alert("Operación cancelada", "Se canceló la operación de consultar usuarios asociados", "Aceptar");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FullSizeMainView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            businessMenuItem.setVisible(false);
            licenseMenuItem.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    License license;
                    license = MainController.getUser().getLicense();

                    String message;
                    message = "Su licencia vence el " + MainController.parseToViewDate(license.getExpirationDate()) + "\n\n"
                            + "ID de licencia: " + license.getId() + "\n"
                            + "Fecha de inicio: " + MainController.parseToViewDate(license.getCreationDate());
                    
                    MainController.notify("Información de licencia", message, "Aceptar");
                }
            });

            updated = true;
        }
    }

    public void prepareViewForAdministrator() {
        MainController.authenticate(sessionKey);
        setInstantiationUserType("Usuario administrador",
                MainController.getUser().
                        getName());
        generalSettingsMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainController.changeToModifyGeneralSettingsMode();
            }
        });
        createSubuserMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainController.changeToAddSubuserMode(sessionKey);
                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(FullSizeMainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        setSubuserHabilityMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainController.changeToDisableSubuserMode();
                } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(FullSizeMainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void prepareViewForNormalUser() {
        MainController.authenticate(sessionKey);
        setInstantiationUserType("Subusuario",
                MainController.getUser().
                        getName());
        businessMenuItem.setVisible(false);
        generalSettingsMenuItem.setVisible(false);
        createSubuserMenuItem.setVisible(false);
        setSubuserHabilityMenuItem.setVisible(false);
    }

    private void setContainerContent(JPanel content) {
        MainController.authenticate(sessionKey);
        if (!content.getSize().equals(container.getSize())) {
            content.setSize(MainController.getFullSizeDimension());
        }
        container.getComponent(0).
                setVisible(false);
        container.removeAll();
        container.add(content);
        container.getComponent(0).
                setVisible(true);
    }

    private void backToFullSize() {
        MainController.authenticate(sessionKey);
        popUpSizeViewport.setVisible(false);
        setEnabled(true);
        requestFocus();
    }

    private static void showAbout() {
        String printable = 
                  "<html><strong>® Debtor Manager<br><br>"
                + "© Jonathan Torres</strong><br>"
                + "   Se prohibe la distribución no autorizada de este software.<br>"
                + "   Para más información:<br>"
                + "      <strong>email</strong>: jnthntrm@gmail.com<br>"
                + "      <strong>teléfono celular</strong>: 305 925 40 24<br><br>"
                + "<strong>Arte</strong>: https://iconos8.es/</html>";
        MainController.notify("About", printable, "Aceptar");
    }

    public void setPopUpSizeViewport(PopUpMainView popUpSizeViewport) {
        MainController.authenticate(sessionKey);
        this.popUpSizeViewport = popUpSizeViewport;
    }

    // -------------------- Change-to-methods
    public void changeToQueryClientMode(QueryClientView queryClientView) {
        MainController.authenticate(sessionKey);
        if (!createClientMenuItem.isVisible()) {
            createClientMenuItem.setVisible(true);
        }
        if (!depositClientsMenuItem.isVisible()) {
            depositClientsMenuItem.setVisible(true);
        }
        setContainerContent(queryClientView.mainContainer);
        queryClientView.setSearchFieldText("");
        queryClientsMenuItem.setVisible(false);
        backToFullSize();
        queryClientView.setMainElementFocus();
    }

    public void changeToClientInfoMode(ClientInfoView clientInfoView) {
        MainController.authenticate(sessionKey);
        if (!queryClientsMenuItem.isVisible()) {
            queryClientsMenuItem.setVisible(true);
        }
        if (!createClientMenuItem.isVisible()) {
            createClientMenuItem.setVisible(true);
        }
        if (!depositClientsMenuItem.isVisible()) {
            depositClientsMenuItem.setVisible(true);
        }
        setContainerContent(clientInfoView.mainContainer);
        backToFullSize();
        clientInfoView.setMainElementFocus();
    }

    public void changeToDetailedHistoryMode(DetailedHistoryView detailedHistoryView) {
        MainController.authenticate(sessionKey);
        if (!queryClientsMenuItem.isVisible()) {
            queryClientsMenuItem.setVisible(true);
        }
        if (!createClientMenuItem.isVisible()) {
            createClientMenuItem.setVisible(true);
        }
        if (!depositClientsMenuItem.isVisible()) {
            depositClientsMenuItem.setVisible(true);
        }
        MainController.authenticate(sessionKey);
        setContainerContent(detailedHistoryView.mainContainer);
        backToFullSize();
        detailedHistoryView.setMainElementFocus();
    }

    public void changeToDepositClientsOnDateMode(DepositClientsOnDateView depositClientsOnDateView) {
        MainController.authenticate(sessionKey);
        if (!queryClientsMenuItem.isVisible()) {
            queryClientsMenuItem.setVisible(true);
        }
        if (!createClientMenuItem.isVisible()) {
            createClientMenuItem.setVisible(true);
        }
        depositClientsOnDateView.setToday();
        setContainerContent(depositClientsOnDateView.mainContainer);
        backToFullSize();
        depositClientsOnDateView.setMainElementFocus();
        depositClientsMenuItem.setVisible(false);
    }
}
