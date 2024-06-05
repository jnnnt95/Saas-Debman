package view.pop_up_view;

import control.MainController;
import control.ModifyPasswordController;
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
import javax.swing.JPasswordField;

/**
 *
 * @author admin
 */
public class ModifyPasswordView extends javax.swing.JFrame {

    private boolean updated;
    private final ModifyPasswordController controller;
    private final String sessionKey;
    private boolean isOperable;

    /**
     * Creates new form modifyUserView
     * @param controller
     * @param sessionKey
     */
    public ModifyPasswordView(ModifyPasswordController controller, String sessionKey) {
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
        jLabel2 = new javax.swing.JLabel();
        modifyPasswordButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        currentPassword = new javax.swing.JPasswordField();
        newPassword = new javax.swing.JPasswordField();
        newPasswordRepetition = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cambiar contraseña", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("Contraseña actual:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Nueva contraseña:");

        modifyPasswordButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        modifyPasswordButton.setText("Cambiar contraseña");
        modifyPasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Repetir nueva contraseña:");

        cancelButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 153, 153));
        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("<html><strong>Tener en cuenta</strong>:<br><ul><li>La nueva contraseña se establecerá únicamente si se ingresa adecuadamente la contraseña actual.</li><li>La nueva contraseña debe tener al menos una letra y un número, y debe contener al menos 7 caracteres.</li></ul></html>");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newPassword)
                            .addComponent(newPasswordRepetition, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(currentPassword)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 159, Short.MAX_VALUE)
                        .addComponent(modifyPasswordButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(currentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(newPasswordRepetition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modifyPasswordButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
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
            .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPasswordField currentPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JButton modifyPasswordButton;
    private javax.swing.JPasswordField newPassword;
    private javax.swing.JPasswordField newPasswordRepetition;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            currentPassword.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyPassword();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyPasswordView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(ModifyPasswordView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            newPassword.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyPassword();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyPasswordView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(ModifyPasswordView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            newPasswordRepetition.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyPassword();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyPasswordView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(ModifyPasswordView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            modifyPasswordButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        modifyPassword();
                    } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                        Logger.getLogger(ModifyPasswordView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(ModifyPasswordView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            modifyPasswordButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            modifyPassword();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyPasswordView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(ModifyPasswordView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    controller.cancelModifyingPassword();
                }
            });
            cancelButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.cancelModifyingPassword();
                    }
                }
            });

            updated = true;
        }
    }
    
    private void disableOperation() {
        currentPassword.setEnabled(false);
        newPassword.setEnabled(false);
        newPasswordRepetition.setEnabled(false);
        
        modifyPasswordButton.setEnabled(false);
        cancelButton.setEnabled(false);
        
        isOperable = false;
    }
    
    public void enableOperation() {
        currentPassword.setEnabled(true);
        newPassword.setEnabled(true);
        newPasswordRepetition.setEnabled(true);
        
        modifyPasswordButton.setEnabled(true);
        cancelButton.setEnabled(true);
        
        isOperable = true;
    }
    
    private void modifyPassword() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException, NoSuchAlgorithmException {
        if(isOperable) {
            disableOperation();
            controller.modifyPassword();
        }
    }
    
    public void clearData() {
        currentPassword.setText("");
        newPassword.setText("");
        newPasswordRepetition.setText("");
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        currentPassword.requestFocus();
    }

    private void setFocus(JPasswordField field) {
        MainController.authenticate(sessionKey);
        field.requestFocus();
        field.selectAll();
    }
    
    public char[] getCurrentPassword() {
        MainController.authenticate(sessionKey);
        return currentPassword.getPassword();
    }

    public void setFocusOnCurrenPassword() {
        MainController.authenticate(sessionKey);
        setFocus(currentPassword);
    }

    public char[] getNewPassword() {
        MainController.authenticate(sessionKey);
        return newPassword.getPassword();
    }

    public void setFocusOnNewPassword() {
        MainController.authenticate(sessionKey);
        setFocus(newPassword);
    }

    public char[] getNewPasswordRepetition() {
        MainController.authenticate(sessionKey);
        return newPasswordRepetition.getPassword();
    }

    public void setFocusOnNewPasswordRepetition() {
        MainController.authenticate(sessionKey);
        setFocus(newPasswordRepetition);
    }
}
