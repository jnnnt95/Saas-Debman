package view.pop_up_view;

import control.MainController;
import control.ModifyUserController;
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
public class ModifyUserView extends javax.swing.JFrame {

    private boolean updated;
    private final ModifyUserController controller;
    private final String sessionKey;

    /**
     * Creates new form modifyUserView
     * @param controller
     * @param sessionKey
     */
    public ModifyUserView(ModifyUserController controller, String sessionKey) {
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
        jLabel1 = new javax.swing.JLabel();
        namesField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        surnamesField = new javax.swing.JTextField();
        modifyUserButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cambiar información personal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("Nombres:");

        namesField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Apellidos:");

        surnamesField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        modifyUserButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        modifyUserButton.setText("Modificar información");
        modifyUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Teléfono:");

        phoneField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        cancelButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 153, 153));
        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 190, Short.MAX_VALUE)
                        .addComponent(modifyUserButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namesField)
                            .addComponent(surnamesField)
                            .addComponent(phoneField))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(namesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(surnamesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyUserButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JButton modifyUserButton;
    private javax.swing.JTextField namesField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField surnamesField;
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
                            controller.modifyUser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyUserView.class.getName()).
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
                            controller.modifyUser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyUserView.class.getName()).
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
                            controller.modifyUser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyUserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            modifyUserButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        controller.modifyUser();
                    } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                        Logger.getLogger(ModifyUserView.class.getName()).
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
                            controller.modifyUser();
                        } catch (ParseException | ClassNotFoundException | SQLException | IOException | InterruptedException ex) {
                            Logger.getLogger(ModifyUserView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    controller.cancelModifyingAnUser();
                }
            });
            cancelButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.cancelModifyingAnUser();
                    }
                }
            });

            updated = true;
        }
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
    
    public String getNewNames() {
        MainController.authenticate(sessionKey);
        return namesField.getText().
                trim();
    }

    public void setFocusOnNames() {
        MainController.authenticate(sessionKey);
        setFocus(namesField);
    }

    public String getNewSurnames() {
        MainController.authenticate(sessionKey);
        return surnamesField.getText().
                trim();
    }

    public void setFocusOnSurnames() {
        MainController.authenticate(sessionKey);
        setFocus(surnamesField);
    }

    public String getNewCPNumber() {
        MainController.authenticate(sessionKey);
        return phoneField.getText().
                trim();
    }

    public void setFocusOnPhone() {
        MainController.authenticate(sessionKey);
        setFocus(phoneField);
    }
    
    public void setUserNames(String name) {
        MainController.authenticate(sessionKey);
        namesField.setText(name);
    }
    
    public void setUserSurnames(String nick) {
        MainController.authenticate(sessionKey);
        surnamesField.setText(nick);
    }
    
    public void setUserPhone(String cPNumber) {
        MainController.authenticate(sessionKey);
        phoneField.setText(cPNumber);
    }
}
