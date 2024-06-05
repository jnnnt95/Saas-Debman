package view.pop_up_view;

import control.AddSubuserController;
import control.MainController;
import control.ModifyUserController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
public class AddSubuserView extends javax.swing.JFrame {

    private boolean updated;
    private final AddSubuserController controller;
    private final String sessionKey;
    private boolean isOperable;

    /**
     * Creates new form AddSubuserView
     * @param controller
     * @param sessionKey
     */
    public AddSubuserView(AddSubuserController controller, String sessionKey) {
        updated = false;
        isOperable = true;
        this.controller = controller;
        this.sessionKey = sessionKey;

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        namesField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        surnamesField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        modifyUserButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        passwordRepetitionField = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Crear subusuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("Nombres:");

        namesField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Apellidos:");

        surnamesField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("Correo electrónico:");

        modifyUserButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        modifyUserButton.setText("Crear subusuario");
        modifyUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Teléfono:");

        phoneField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        phoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneFieldActionPerformed(evt);
            }
        });

        emailField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        cancelButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 153, 153));
        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setText("Nombre de usuario:");

        usernameField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel6.setText("Contraseña:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setText("Repetir contraseña:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setText("<html>*<strong>No puede haber campos vacíos</strong></html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordRepetitionField)
                            .addComponent(phoneField)
                            .addComponent(emailField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(1, 1, 1))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(surnamesField)
                            .addComponent(namesField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(modifyUserButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(namesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(surnamesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordRepetitionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyUserButton)
                    .addComponent(cancelButton)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
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
            .addComponent(mainContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void phoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JButton modifyUserButton;
    private javax.swing.JTextField namesField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField passwordRepetitionField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField surnamesField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            namesField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addSubuser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException | NoSuchAlgorithmException ex) {
                            Logger.getLogger(AddSubuserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            surnamesField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addSubuser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException | NoSuchAlgorithmException ex) {
                            Logger.getLogger(AddSubuserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            usernameField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addSubuser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException | NoSuchAlgorithmException ex) {
                            Logger.getLogger(AddSubuserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            passwordField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addSubuser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException | NoSuchAlgorithmException ex) {
                            Logger.getLogger(AddSubuserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            passwordRepetitionField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addSubuser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException | NoSuchAlgorithmException ex) {
                            Logger.getLogger(AddSubuserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            phoneField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addSubuser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException | NoSuchAlgorithmException ex) {
                            Logger.getLogger(AddSubuserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            emailField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addSubuser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException | NoSuchAlgorithmException ex) {
                            Logger.getLogger(AddSubuserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            modifyUserButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        addSubuser();
                    } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException | NoSuchAlgorithmException ex) {
                        Logger.getLogger(AddSubuserView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            });
            modifyUserButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            addSubuser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException | NoSuchAlgorithmException ex) {
                            Logger.getLogger(AddSubuserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    controller.cancelAddingASubuser();
                }
            });
            cancelButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.cancelAddingASubuser();
                    }
                }
            });

            updated = true;
        }
    }
    
    private void disableOperation() {
        namesField.setEnabled(false);
        surnamesField.setEnabled(false);
        usernameField.setEnabled(false);
        passwordField.setEnabled(false);
        passwordRepetitionField.setEnabled(false);
        phoneField.setEnabled(false);
        emailField.setEnabled(false);
        
        modifyUserButton.setEnabled(false);
        cancelButton.setEnabled(false);
        
        isOperable = false;
    }
    
    public void enableOperation() {
        namesField.setEnabled(true);
        surnamesField.setEnabled(true);
        usernameField.setEnabled(true);
        passwordField.setEnabled(true);
        passwordRepetitionField.setEnabled(true);
        phoneField.setEnabled(true);
        emailField.setEnabled(true);
        
        modifyUserButton.setEnabled(true);
        cancelButton.setEnabled(true);
        
        isOperable = true;
    }
    
    private void addSubuser() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException, NoSuchAlgorithmException {
        if(isOperable) {
            disableOperation();
            controller.addSubuser();
        }
    }
    
    //get 
    
    public String getNewNames() {
        MainController.authenticate(sessionKey);
        return namesField.getText().
                trim();
    }
    
    public void clearData() {
        namesField.setText("");
        surnamesField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        passwordRepetitionField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    public String getNewSurnames() {
        MainController.authenticate(sessionKey);
        return surnamesField.getText().
                trim();
    }

    public String getNewUsername() {
        MainController.authenticate(sessionKey);
        return usernameField.getText().
                trim();
    }

    public char[] getNewPassword() {
        MainController.authenticate(sessionKey);
        return passwordField.getPassword();
    }

    public char[] getNewPasswordRepetition() {
        MainController.authenticate(sessionKey);
        return passwordRepetitionField.getPassword();
    }

    public String getNewCPNumber() {
        MainController.authenticate(sessionKey);
        return phoneField.getText().
                trim();
    }

    public String getNewEmail() {
        MainController.authenticate(sessionKey);
        return emailField.getText().
                trim();
    }
    
    //set focus

    public void setFocusOnNames() {
        MainController.authenticate(sessionKey);
        setFocus(namesField);
    }

    public void setFocusOnSurnames() {
        MainController.authenticate(sessionKey);
        setFocus(surnamesField);
    }

    public void setFocusOnUsername() {
        MainController.authenticate(sessionKey);
        setFocus(usernameField);
    }

    public void setFocusOnPassword() {
        MainController.authenticate(sessionKey);
        setFocus(passwordField);
    }

    public void setFocusOnPasswordRepetition() {
        MainController.authenticate(sessionKey);
        setFocus(passwordRepetitionField);
    }

    public void setFocusOnPhone() {
        MainController.authenticate(sessionKey);
        setFocus(phoneField);
    }

    public void setFocusOnEmail() {
        MainController.authenticate(sessionKey);
        setFocus(emailField);
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        namesField.requestFocus();
    }

    private void setFocus(JTextField field) {
        MainController.authenticate(sessionKey);
        field.requestFocus();
        field.selectAll();
    }
}
