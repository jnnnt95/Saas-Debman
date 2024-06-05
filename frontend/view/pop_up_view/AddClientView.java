package view.pop_up_view;

import control.AddClientController;
import control.AddDebtController;
import control.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class AddClientView extends javax.swing.JFrame {

    private boolean updated;
    private final AddClientController controller;
    private final String sessionKey;
    private boolean buttonsEnabled;

    /**
     * Creates new form ClientInfo
     *
     * @param controller
     * @param sessionKey
     */
    public AddClientView(AddClientController controller, String sessionKey) {
        buttonsEnabled = true;
        updated = false;
        this.controller = controller;
        this.sessionKey = sessionKey;

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        addClientButton = new javax.swing.JButton();
        nickTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        commentaryTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        initialBalanceTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        phoneTextField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        interestRateTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        interestRateOnDefaultTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        addClientButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addClientButton.setText("Agregar cliente");
        addClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        nickTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Nick:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setText("Nombre:");

        nameTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("Comentario:");

        commentaryTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Saldo inicial:");

        initialBalanceTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        initialBalanceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initialBalanceTextFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel6.setText("Teléfono:");

        phoneTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        cancelButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 153, 153));
        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setText("% (≥ 0)");

        interestRateTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setText("Tasa de interés:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel9.setText("Tasa de interés en mora:");

        interestRateOnDefaultTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel10.setText("% (≥ 0)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(commentaryTextField)
                            .addComponent(phoneTextField)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nickTextField)
                            .addComponent(nameTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addClientButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(initialBalanceTextField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(interestRateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(interestRateOnDefaultTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nickTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(commentaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(interestRateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(interestRateOnDefaultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initialBalanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(addClientButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initialBalanceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initialBalanceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_initialBalanceTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClientButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField commentaryTextField;
    private javax.swing.JTextField initialBalanceTextField;
    private javax.swing.JTextField interestRateOnDefaultTextField;
    private javax.swing.JTextField interestRateTextField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField nickTextField;
    private javax.swing.JTextField phoneTextField;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            nameTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            nickTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            phoneTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            commentaryTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            interestRateTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            interestRateOnDefaultTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            initialBalanceTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            addClientButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        addClient();
                    } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(AddClientController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            });
            addClientButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    controller.cancelAddingAClient();
                }
            });
            cancelButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.cancelAddingAClient();
                    }
                }
            });

            updated = true;
        }
    }

    private void disableOperation() {
        //buttons
        this.addClientButton.setEnabled(false);
        this.cancelButton.setEnabled(false);
        //text fields
        this.nameTextField.setEnabled(false);
        this.nickTextField.setEnabled(false);
        this.phoneTextField.setEnabled(false);
        this.commentaryTextField.setEnabled(false);
        this.interestRateTextField.setEnabled(false);
        this.interestRateOnDefaultTextField.setEnabled(false);
        this.initialBalanceTextField.setEnabled(false);
        buttonsEnabled = false;
    }

    public void enableOperation() {
        //buttons
        this.addClientButton.setEnabled(true);
        this.cancelButton.setEnabled(true);
        //text fields
        this.nameTextField.setEnabled(true);
        this.nickTextField.setEnabled(true);
        this.phoneTextField.setEnabled(true);
        this.commentaryTextField.setEnabled(true);
        this.interestRateTextField.setEnabled(true);
        this.interestRateOnDefaultTextField.setEnabled(true);
        this.initialBalanceTextField.setEnabled(true);
        buttonsEnabled = true;
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        nameTextField.requestFocus();
    }

    private void setFocus(JTextField field) {
        MainController.authenticate(sessionKey);
        field.requestFocus();
        field.selectAll();
    }

    public void clearName() {
        MainController.authenticate(sessionKey);
        nameTextField.setText("");
    }

    public void clearNick() {
        MainController.authenticate(sessionKey);
        nickTextField.setText("");
    }

    public void clearCPNumber() {
        MainController.authenticate(sessionKey);
        phoneTextField.setText("");
    }

    public void clearArea() {
        MainController.authenticate(sessionKey);
        commentaryTextField.setText("");
    }

    public void clearInterestRate() {
        MainController.authenticate(sessionKey);
        interestRateTextField.setText(String.valueOf(MainController.getUser().getPreferredInterestRate()));
    }

    public void clearInterestRateOnDefault() {
        MainController.authenticate(sessionKey);
        interestRateOnDefaultTextField.setText(String.valueOf(MainController.getUser().getPreferredInterestRateOnDefault()));
    }

    public void clearInitialBalance() {
        MainController.authenticate(sessionKey);
        initialBalanceTextField.setText("");
    }

    public String getNewClientName() {
        MainController.authenticate(sessionKey);
        return nameTextField.getText().
                trim();
    }

    public void setFocusOnName() {
        MainController.authenticate(sessionKey);
        setFocus(nameTextField);
    }

    public String getNewClientNick() {
        MainController.authenticate(sessionKey);
        return nickTextField.getText().
                trim();
    }

    public void setFocusOnNick() {
        MainController.authenticate(sessionKey);
        setFocus(nickTextField);
    }

    public String getNewClientCPNumber() {
        MainController.authenticate(sessionKey);
        return phoneTextField.getText().
                trim();
    }

    public void setFocusOnCPNumber() {
        MainController.authenticate(sessionKey);
        setFocus(phoneTextField);
    }

    public String getNewClientCommentary() {
        MainController.authenticate(sessionKey);
        return commentaryTextField.getText().
                trim();
    }

    public String getNewClientInterestRate() {
        MainController.authenticate(sessionKey);
        return interestRateTextField.getText().trim();
    }

    public String getNewClientInterestRateOnDefault() {
        MainController.authenticate(sessionKey);
        return interestRateOnDefaultTextField.getText().
                trim();
    }

    public void setFocusOnInterestRate() {
        MainController.authenticate(sessionKey);
        setFocus(interestRateTextField);
    }

    public void setFocusOnInterestRateOnDefault() {
        MainController.authenticate(sessionKey);
        setFocus(interestRateOnDefaultTextField);
    }

    public void setFocusOnCommentary() {
        MainController.authenticate(sessionKey);
        setFocus(commentaryTextField);
    }

    public String getNewClientInitialBalance() {
        MainController.authenticate(sessionKey);
        return initialBalanceTextField.getText().
                trim();
    }

    public void setFocusOnInitialBalance() {
        MainController.authenticate(sessionKey);
        setFocus(initialBalanceTextField);
    }

    public void setNullNewBalance() {
        MainController.authenticate(sessionKey);
        initialBalanceTextField.setText("");
    }

    public void addClient() throws IOException, ParseException, ClassNotFoundException, SQLException {
        if (buttonsEnabled) {
            disableOperation();
            controller.addClient();
        }
    }
}
