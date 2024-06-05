package view.pop_up_view;

import control.LogInController;
import control.MainController;
import java.awt.Container;
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
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class LogInView extends javax.swing.JFrame {

    Container contentPane;
    private boolean updated;
    private final LogInController controller;
    private final String sessionKey;

    /**
     * Creates new form LogInView
     *
     * @param controller
     * @param sessionKey
     */
    public LogInView(LogInController controller, String sessionKey) {
        updated = false;
        this.controller = controller;
        this.sessionKey = sessionKey;

        initComponents();
        ImageIcon imgThisImg = new ImageIcon("Images/icon2.png");
        logoLabel.setIcon(imgThisImg);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        logInButton = new javax.swing.JButton();
        passwordTextField = new javax.swing.JPasswordField();
        userTextField = new javax.swing.JTextField();
        nameLabelTitle5 = new javax.swing.JLabel();
        nickLabelTitle5 = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        forgotPasswordButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Iniciar sesión", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel6.setOpaque(false);

        logInButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        logInButton.setText("Iniciar sesión");
        logInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        nameLabelTitle5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nameLabelTitle5.setText("Nombre de usuario:");

        nickLabelTitle5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabelTitle5.setText("Contraseña:");

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        logoLabel.setText(" ");

        forgotPasswordButton.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        forgotPasswordButton.setText("¿Olvidó su contraseña?");
        forgotPasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nameLabelTitle5)
                                    .addComponent(nickLabelTitle5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(logInButton))
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(forgotPasswordButton)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 122, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabelTitle5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nickLabelTitle5)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logInButton)
                        .addGap(74, 74, 74)
                        .addComponent(forgotPasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(logoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton forgotPasswordButton;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton logInButton;
    private javax.swing.JLabel logoLabel;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JLabel nameLabelTitle5;
    private javax.swing.JLabel nickLabelTitle5;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JTextField userTextField;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            userTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.logIn();
                        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
                            Logger.getLogger(LogInController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(LogInView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            passwordTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.logIn();
                        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
                            Logger.getLogger(LogInController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(LogInView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            logInButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        controller.logIn();
                    } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(LogInView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            logInButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.logIn();
                        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
                            Logger.getLogger(LogInController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(LogInView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            forgotPasswordButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    requestPasswordReset();
                }
            });
            updated = true;
        }
    }

    public void requestPasswordReset() {
        this.setEnabled(false);
        String message = ""
                + "Para seguir con el proceso, se enviará un correo electrónico al registrado \ncon su usuario. Usted deberá ingresar a dicho correo y seguir las \ninstrucciones que se indican para continuar con el proceso de \nrecuperación de contraseña.\n\n"
                + "Correo electrónico:\n\n";
        String title = "Ingrese su correo electrónico";
        String email = MainController.inputData(title, message);
        if (email != null) {
            email = email.trim();
            try {
                controller.requestPasswordReset(email);
            } catch (IOException ex) {
                Logger.getLogger(LogInView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.setEnabled(true);
        }
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        userTextField.requestFocus();
    }

    public String getUsername() {
        MainController.authenticate(sessionKey);
        return userTextField.getText().
                trim();
    }

    public char[] getPassword() {
        MainController.authenticate(sessionKey);
        return passwordTextField.getPassword();
    }

    public void clear() {
        MainController.authenticate(sessionKey);
        clearUsername();
        clearPassword();

    }

    private void clearUsername() {
        MainController.authenticate(sessionKey);
        userTextField.setText("");
        jPanel6.remove(userTextField);
        userTextField = null;
    }

    private void clearPassword() {
        MainController.authenticate(sessionKey);
        passwordTextField.setText("");
        jPanel6.remove(passwordTextField);
        passwordTextField = null;
    }

    public void setFocusOnUsername() {
        this.userTextField.selectAll();
        this.userTextField.requestFocus();
    }

    public void setFocusOnPassword() {
        this.passwordTextField.selectAll();
        this.passwordTextField.requestFocus();
    }
}
